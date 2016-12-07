import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Distributor {

	int termMax = 4;
	int aircraftParkingMax = 6;

	Semaphore landingLane, takeOffLane;
	Semaphore landingCurve, takeOffCurve;

	ArrayList<Semaphore> landInt;
	ArrayList<Semaphore> toInt;
	ArrayList<Semaphore> termLine;
	

	Lock aircraftParkingLock;
	Condition aircraftQueue;
	ArrayList<AircraftParking> aircraftParkings;

	Long landingWaitingTime = (long) 0;

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

	long askForLandingLane(String planeId) {

		if (!landingLane.tryAcquire()) {
			landingWaitingTime = landingWaitingTime + 1000;
			System.out.println(planeId + " cant land, will try in " + landingWaitingTime + "ms");
			return landingWaitingTime;
		}

		if (landingWaitingTime > 1000)
			landingWaitingTime = landingWaitingTime - 1000;

		System.out.println(planeId + " is landing...");

		return 0;

	}

	boolean askForLandingCurve(String planeId) {

		try {
			landingCurve.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		landingLane.release();

		System.out.println(planeId + " is in landing curve...");

		return true;

	}

	AircraftParking askForTerminal (String planeId) {
		
		int num = -1;
		
		aircraftParkingLock.lock();
		
		while (num == -1) {
		
			for (int n = 0; n < aircraftParkings.size(); n++) {
				if (aircraftParkings.get(n).getSituation().equalsIgnoreCase("EMPTY")) {
					
					aircraftParkings.get(n).setSituation("FULL");
					
					System.out.println(planeId + " is going to " + aircraftParkings.get(n).toString());
					
					num = n;
					
					break;
				}
			}
			
			if (num == -1) {
			
				try {
					aircraftQueue.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		
		aircraftParkingLock.unlock();
		
		return aircraftParkings.get(num);
		
	}

	boolean releaseTerminal (AircraftParking acp) {
		
		int t = acp.getTerminal();
		int p = acp.getPosition();
		
		for (int n = 0; n < aircraftParkings.size(); n++) {
			if (aircraftParkings.get(n).getTerminal() == t) {
				if (aircraftParkings.get(n).getPosition() == p) {
					aircraftParkings.get(n).setSituation("EMPTY");
					break;
				}
			}
		}
		
		return true;
	}
	
	boolean askForLandingIntermediate(int intermediateNum, String planeId) {
		try {
			landInt.get(intermediateNum - 1).acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (intermediateNum == 1) {
			landingCurve.release();
		}
		
		System.out.println(planeId + " is in landing intermediate lane nº " + intermediateNum);
				
		return true;
	}
	
	boolean releaseLandingIntermediate (int intermediateNum) {
		
		landInt.get(intermediateNum - 1).release();
		
		return true;
	}

	boolean askForTermLine (int termNum, String planeId) {
		try {
			termLine.get(termNum - 1).acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(planeId + " is in terminal line " + termNum);

		return true;
	}

	boolean releaseTermLine (int termNum) {
		termLine.get(termNum - 1).release();
		
		return true;
	}
	
	boolean askForToIntermediate(int intermediateNum, String planeId) {
		try {
			toInt.get(intermediateNum - 1).acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(planeId + " is in take off intermediate lane nº " + intermediateNum);
				
		return true;
	}
	
	boolean releaseToIntermediate (int intermediateNum) {
		
		toInt.get(intermediateNum - 1).release();
		
		return true;
	}

	boolean askForToCurve (String planeId) {
		try {
			takeOffCurve.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		releaseToIntermediate(termMax - 1);

		System.out.println(planeId + " is in take off curve...");

		return true;
	}

	boolean askForTakeOffLane (String planeId) {
		try {
			takeOffLane.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		takeOffCurve.release();
		
		System.out.println(planeId + " is in take off line...");

		return true;
	}
	
	boolean releaseTakeOffLane () {
		
		takeOffLane.release();
		
		return true;
		
	}
}
