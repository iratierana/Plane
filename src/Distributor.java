import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import dataGenerator.PlaneGenerator;
import domain.dao.DAOAirplane;
import domain.model.Airplane;

/**
 * The Class Distributor.
 */
public class Distributor {

	/** The term max. */
	int termMax = 4;
	
	/** The aircraft parking max. */
	int aircraftParkingMax = 6;

	/** The database update mtx. */
	Semaphore databaseUpdateMtx = new Semaphore(1);
	
	/** The take off lane. */
	Semaphore landingLane, takeOffLane;
	
	/** The take off curve. */
	Semaphore landingCurve, takeOffCurve;

	/** The land int. */
	ArrayList<Semaphore> landInt;
	
	/** The to int. */
	ArrayList<Semaphore> toInt;
	
	/** The term line. */
	ArrayList<Semaphore> termLine;
	

	/** The aircraft parking lock. */
	Lock aircraftParkingLock;
	
	/** The aircraft queue. */
	Condition aircraftQueue;
	
	/** The aircraft parkings. */
	ArrayList<AircraftParking> aircraftParkings;

	/** The landing waiting time. */
	Long landingWaitingTime = (long) 0;

	/**
	 * 
	 * Constructor of Distributor class.
	 * Initialize all the synchronization elements.
	 * 
	 * @param N To stablish number of planes in all sections apart from landing and take off lane.
	 * 
	 */
	
	public Distributor(int N) {

		landingLane = new Semaphore(1);
		takeOffLane = new Semaphore(1);

		landingCurve = new Semaphore(N);
		takeOffCurve = new Semaphore(N);

		landInt = new ArrayList<Semaphore>();
		toInt = new ArrayList<Semaphore>();
		termLine = new ArrayList<Semaphore>();

		for (int n = 0; n < termMax; n++) {
			landInt.add(new Semaphore(N));
		}

		for (int n = 0; n < termMax - 1; n++) {
			toInt.add(new Semaphore(N));
		}
		
		for (int n = 0; n < termMax; n++) {
			termLine.add(new Semaphore(N));
		}

		aircraftParkingLock = new ReentrantLock();
		aircraftQueue = aircraftParkingLock.newCondition();
		aircraftParkings = new ArrayList<AircraftParking>();

		for (int terminal = 1; terminal <= termMax; terminal++) {
			for (int acp = 1; acp <= aircraftParkingMax; acp++) {

				aircraftParkings.add(new AircraftParking(terminal, acp));

			}
		}

	}

	/**
	 * 
	 * Function to ask permission to introduce to the landing lane.
	 * The plane simulator thread will call this function.
	 * 
	 * @param planeId To know which plane is asking to land.
	 * @return 		  In case there isn't place to land, the return value will be a waiting period in ms.
	 * @see			  Plane status
	 */
	
	long askForLandingLane(int planeId) {

		if (!landingLane.tryAcquire()) { //intenta coger, y si no es posible se va sin coger
			landingWaitingTime = landingWaitingTime + 1000;
			System.out.println(planeId + " cant land, will try in " + landingWaitingTime + "ms");
			return landingWaitingTime;
		}

		if (landingWaitingTime > 1000)
			landingWaitingTime = landingWaitingTime - 1000;
		
		/*--------------UPDATE POSITION IN DATABSE----------------*/
		updatePlanePosition(planeId, 2);
		/*--------------------------------------------------------*/
		System.out.println(planeId + " is landing...");

		return 0;

	}

	/**
	 * 
	 * Function to ask permission to introduce to the landing curve.
	 * The plane simulator thread will call this function.
	 * 
	 * @param planeId To know which plane is asking to land.
	 * @return 		  In case something goes wrong, it returns false. 
	 * @see 		  Plane status
	 * 
	 */
	
	boolean askForLandingCurve(int planeId) {

		try {
			landingCurve.acquire(); //intentas coger y hasta que no cojes no para
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}

		landingLane.release();
		/*--------------UPDATE POSITION IN DATABSE----------------*/
		updatePlanePosition(planeId, 3);
		/*--------------------------------------------------------*/
		System.out.println(planeId + " is in landing curve...");

		return true;

	}

	/**
	 * 
	 * Function to ask for a gate.
	 * The plane simulator thread will call this function.
	 * 
	 * @param planeId To know which plane is asking to land.
	 * @return 		  Gate number where it has to park. 
	 * @see 		  Plane status
	 * 
	 */
	
	AircraftParking askForTerminal (int planeId) {
		
		int num = -1;
		
		aircraftParkingLock.lock();
		
		while (num == -1) {
		
			for (int n = 0; n < aircraftParkings.size(); n++) {
				if (aircraftParkings.get(n).getSituation().equalsIgnoreCase("EMPTY")) {
					
					aircraftParkings.get(n).setSituation("FULL");
					
					/*--------------UPDATE POSITION IN DATABSE----------------*/
					updateParkingPositionInDatabase(planeId, aircraftParkings.get(n));
					/*--------------------------------------------------------*/
					System.out.println(planeId + " is going to " + aircraftParkings.get(n).toString());
					
					num = n;
					
					break;
				}
			}
			
			if (num == -1) {
			
				try {
					aircraftQueue.await();
					System.out.println("Parkins are full");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
		aircraftParkingLock.unlock();
		
		return aircraftParkings.get(num);
		
	}
	
	/**
	 * 
	 * Function to notify that the plane is exiting the gate.
	 * The plane simulator thread will call this function.
	 * 
	 * @param acp To know which gate is getting free.
	 * @return 	  In case something goes wrong, it returns false.
	 * 
	 */

	boolean releaseTerminal (AircraftParking acp) {
		
		int t = acp.getTerminal();
		int p = acp.getPosition();
		
		aircraftParkingLock.lock();
		
		for (int n = 0; n < aircraftParkings.size(); n++) {
			if (aircraftParkings.get(n).getTerminal() == t) {
				if (aircraftParkings.get(n).getPosition() == p) {
					aircraftParkings.get(n).setSituation("EMPTY");
					aircraftQueue.signalAll();
					break;
				}
			}
		}
		
		aircraftParkingLock.unlock();
		
		return true;
	}
	
	/**
	 * Function to ask permission to introduce to the landing intermediate line.
	 * The plane simulator thread will call this function.
	 *
	 * @param intermediateNum To know in which intermediate lane is asking permission for.
	 * @param planeId 		  To know which plane is asking to land.
	 * @return 		 		  In case something goes wrong, it returns false.
	 * @see 		  		  Plane status
	 */
	
	boolean askForLandingIntermediate(int intermediateNum, int planeId) {
		try {
			landInt.get(intermediateNum - 1).acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
		
		if (intermediateNum == 1) {
			landingCurve.release();
		}
		/*--------------UPDATE POSITION IN DATABSE----------------*/
		updateLandingIntermediateLinePositions(planeId, intermediateNum);
		/*--------------------------------------------------------*/		
		System.out.println(planeId + " is in landing intermediate lane nº " + intermediateNum);
				
		return true;
	}
	
	/**
	 * 
	 * To release the landing intermediate.
	 * The plane simulator thread will call this function.
	 * 
	 * @param intermediateNum To know in which intermediate lane is asking permission for. 
	 * @return 		 		  If everything goes ok returns true.
	 * 
	 */
	
	boolean releaseLandingIntermediate (int intermediateNum) {
		
		landInt.get(intermediateNum - 1).release();
		
		return true;
	}

	/**
	 * Function to ask permission to introduce to the terminal line.
	 * The plane simulator thread will call this function.
	 *
	 * @param termNum 	  To know in which terminal is asking permission for.
	 * @param planeId 		  To know which plane is asking to land.
	 * @return 		 		  In case something goes wrong, it returns false.
	 * @see 		  		  Plane status
	 */
	
	boolean askForTermLine (int termNum, int planeId) {
		try {
			termLine.get(termNum - 1).acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		/*--------------UPDATE POSITION IN DATABSE----------------*/
		updateTerminalLinePosition(planeId, termNum);
		/*--------------------------------------------------------*/	
		System.out.println(planeId + " is in terminal line " + termNum);

		return true;
	}

	/**
	 * 
	 * Function to release the terminal line.
	 * The plane simulator thread will call this function.
	 * 
	 * @param termNum		  To know which Terminal is realesing. 
	 * @return 		 		  If everything goes ok returns true..
	 * 
	 */
	
	boolean releaseTermLine (int termNum) {
		termLine.get(termNum - 1).release();
		
		return true;
	}
	
	/**
	 * Function to ask permission to introduce to the intermediate line.
	 * The plane simulator thread will call this function.
	 *
	 * @param intermediateNum To know which intermediate line is asking permission for.
	 * @param planeId 		  To know which plane is asking to land.
	 * @return 		 		  In case something goes wrong, it returns false.
	 * @see 		  		  Plane status
	 */
	
	boolean askForToIntermediate(int intermediateNum, int planeId) {
		try {
			toInt.get(intermediateNum - 1).acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/*--------------UPDATE POSITION IN DATABSE----------------*/
		updateTakeOffIntermediateLinePositions(planeId, intermediateNum);
		/*--------------------------------------------------------*/
		System.out.println(planeId + " is in take off intermediate lane nº " + intermediateNum);
				
		return true;
	}
	
	/**
	 * 
	 * Function to release the intermediate line.
	 * The plane simulator thread will call this function.
	 * 
	 * @param intermediateNum To know which intermediate line is releasing. 
	 * @return 		 		  If everything goes ok returns true.
	 * 
	 */
	
	boolean releaseToIntermediate (int intermediateNum) {
		
		toInt.get(intermediateNum - 1).release();
		
		return true;
	}

	/**
	 * 
	 * Function to ask permission to introduce to the take off curve.
	 * The plane simulator thread will call this function.
	 * 
	 * @param planeId 		  To know which plane is asking to land.
	 * @return 		 		  In case something goes wrong, it returns false.
	 * @see 		  		  Plane status
	 * 
	 */
	
	boolean askForToCurve (int planeId) {
		try {
			takeOffCurve.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
		
		releaseToIntermediate(termMax - 1);
		/*--------------UPDATE POSITION IN DATABSE----------------*/
		updatePlanePosition(planeId, 39);
		/*--------------------------------------------------------*/
		System.out.println(planeId + " is in take off curve...");

		return true;
	}

	/**
	 * 
	 * Function to ask permission to introduce to the take off lane.
	 * The plane simulator thread will call this function.
	 * 
	 * @param planeId 		  To know which plane is asking to land.
	 * @return 		 		  In case something goes wrong, it returns false.
	 * @see 		  		  Plane status
	 * 
	 */
	
	boolean askForTakeOffLane (int planeId) {
		try {
			takeOffLane.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}

		takeOffCurve.release();
		/*--------------UPDATE POSITION IN DATABSE----------------*/
		updatePlanePosition(planeId, 40);
		/*--------------------------------------------------------*/
		System.out.println(planeId + " is in take off line...");

		return true;
	}
	
	/**
	 * Function to release the take off lane.
	 * The plane simulator thread will call this function.
	 *  
	 *
	 * @param planeId the plane id
	 * @return 		 		  If everything goes ok returns true.
	 */
	
	boolean releaseTakeOffLane (int planeId) {
		
		takeOffLane.release();
		updatePlanePosition(planeId, 41);
		
		return true;
		
	}
	/**
	 * This function updates the plane position of any type plane.
	 * @param planeId The id the of the plane to update.
	 * @param planePosition The new position of the plane.
	 * @return true if the update is ok, else false
	 */
	boolean updatePlanePosition(int planeId, int planePosition){
		try {
			databaseUpdateMtx.acquire(1);
			DAOAirplane.updateAirplanePosition(planeId, planePosition);
			databaseUpdateMtx.release(1);
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * This function updates the intermediate landing position in the database, in a exclusive way.
	 * @param planeId The id of the plane to update.
	 * @param intermediateLine The number of the intermediate line.
	 * @return true if the update is ok, else will return false.
	 */
	boolean updateLandingIntermediateLinePositions(int planeId, int intermediateLine){
		boolean ok=false;
		switch (intermediateLine) {
		case 1 :	
			updatePlanePosition(planeId, 4);
			ok=true;
			break;
		case 2:
			updatePlanePosition(planeId, 12);
			ok=true;
			break;
		case 3:
			updatePlanePosition(planeId, 20);
			ok=true;
			break;
		case 4:
			updatePlanePosition(planeId, 28);
			ok=true;;
			break;
		}
		return ok;
	}
	
	/**
	 * This function updates the intermediate taking off positions in the database, in a exclusive way.
	 * @param planeId The id of the plane to update.
	 * @param intermediateLine The number of the intermediate line.
	 * @return true if the update is ok, else will return false.
	 */
	boolean updateTakeOffIntermediateLinePositions(int planeId, int intermediateLine){
		boolean ok=false;
		switch (intermediateLine) {
		case 1 :	
			updatePlanePosition(planeId, 36);
			ok=true;
			break;
		case 2:
			updatePlanePosition(planeId, 37);
			ok=true;
			break;
		case 3:
			updatePlanePosition(planeId, 38);
			ok=true;
			break;
		}
		return ok;
	}
	
	/**
	 * This function updates the terminal line position in the database.
	 * @param planeId The id of the plane to update the position.
	 * @param termNumb The new terminal line position.
	 * @return true if the update is OK, else false.
	 */
	boolean updateTerminalLinePosition(int planeId, int termNumb ){
		boolean ok = false;
		switch (termNumb) {
		case 1:		
			updatePlanePosition(planeId, 5);
			ok = true;
			break;
		case 2:		
			updatePlanePosition(planeId, 13);
			ok = true;
			break;
		case 3:		
			updatePlanePosition(planeId, 21);
			ok = true;
			break;	
		case 4:		
			updatePlanePosition(planeId, 29);
			ok = true;
			break;
		}
		return ok;
	}
	
	
	/**
	 * This function updates the position of the parking and terminal in the database.
	 *
	 * @param planeId The id of the plane to update.
	 * @param parking The parking where the plane is going to be parked.
	 * @return true if the update is OK, else false.
	 */
	boolean updateParkingPositionInDatabase(int planeId, AircraftParking parking){
		boolean ok=false;
		switch (parking.getTerminal()) {
		case 1:	
			switch (parking.getPosition()) {
			case 1:		
				updatePlanePosition(planeId, 6);
				break;
			case 2:		
				updatePlanePosition(planeId, 7);
				break;
			case 3:	
				updatePlanePosition(planeId, 8);
				break;
			case 4:	
				updatePlanePosition(planeId, 9);
				break;
			case 5:	
				updatePlanePosition(planeId, 10);
				break;
			case 6:	
				updatePlanePosition(planeId, 11);
				break;
			}
			ok=true;
			break;
		case 2:
			switch (parking.getPosition()) {
			case 1:		
				updatePlanePosition(planeId, 14);
				break;
			case 2:		
				updatePlanePosition(planeId, 15);
				break;
			case 3:	
				updatePlanePosition(planeId, 16);
				break;
			case 4:	
				updatePlanePosition(planeId, 17);
				break;
			case 5:
				updatePlanePosition(planeId, 18);
				break;
			case 6:	
				updatePlanePosition(planeId, 19);
				break;
			}
			ok=true;
			break;
		case 3:
			switch (parking.getPosition()) {
			case 1:	
				updatePlanePosition(planeId, 22);
				break;
			case 2:			
				updatePlanePosition(planeId, 23);
				break;
			case 3:	
				updatePlanePosition(planeId, 24);
				break;
			case 4:
				updatePlanePosition(planeId, 25);
				break;
			case 5:	
				updatePlanePosition(planeId, 26);
				break;
			case 6:		
				updatePlanePosition(planeId, 27);
				break;
			}
			ok=true;
			break;
		case 4:
			switch (parking.getPosition()) {
			case 1:	
				updatePlanePosition(planeId, 30);
				break;
			case 2:		
				updatePlanePosition(planeId, 31);
				break;
			case 3:	
				updatePlanePosition(planeId, 32);
				break;
			case 4:	
				updatePlanePosition(planeId, 33);
				break;
			case 5:		
				updatePlanePosition(planeId, 34);
				break;
			case 6:		
				updatePlanePosition(planeId, 35);
				break;
			}
			ok=true;
			break;
		}
		return ok;
	}
	
	/**
	 * This function make a exclusive connection to the database and creates a new airplane.
	 * @return The airplane created in the database
	 */
	Airplane createPlaneInExclusiveWay(){
		Airplane a;
		try {
			databaseUpdateMtx.acquire(1);
			a = PlaneGenerator.createAirplane();
			databaseUpdateMtx.release(1);
			return a;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}	
	}
}
