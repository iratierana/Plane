import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import dataGenerator.PlaneGenerator;
import domain.dao.DAOAirplane;
import domain.model.Airplane;
import domain.model.AirplanePhoto;
import domain.model.Airport;
import domain.model.AirportController;
import domain.model.Direction;
import domain.model.Flight;
import domain.model.Passanger;
import domain.model.PlanePosition;
import domain.model.Runway;

public class AirPlane implements Runnable {

	Distributor distributor;
	String planeId;	
	Airplane airplane;
	/*
	 * 
	 * Constructor of AirPlane class.
	 * Stablishes the distributor and the planeId.
	 * 
	 * @param distributor To know where to ask permissions.
	 * @param planeId     To know which plane it is.
	 * 
	 */
	
	public AirPlane(Distributor distributor, String planeId) {
		super();
		this.distributor = distributor;
		this.planeId = planeId;
	}

	
	/*
	 * 
	 * To run the airplane execution simulation.
	 * Step 1. Ask for landing lane.
	 * Step 2. Ask for landing curve.
	 * Step 3. Ask for terminal.
	 * Step 4. Ask for and releases the needed intermediate lines.
	 * Step 5. Releases terminal.
	 * Step 6. Ask for and releases the needed intermediate lines.
	 * Step 7. Ask for take off curve.
	 * Step 8. Ask for take off lane.
	 * Step 9. Releases take off lane.
	 * 
	 */

	
	@Override
	public void run() {
		
		long time = 0;
		
		airplane = PlaneGenerator.createAirplane();
		
		
		while ((time = distributor.askForLandingLane(planeId)) > 0) {
			
			goToBed(time);
		
		}
		
		if (!distributor.askForLandingCurve(planeId)) System.err.println("Landing Curve");
		
		
		AircraftParking acp = distributor.askForTerminal(planeId);
		
		
		for (int n = 1; n <= acp.getTerminal(); n++) {
			
			if (!distributor.askForLandingIntermediate(n, planeId)) System.err.println("Landing Intermediate");
			
			if (n > 1) {
				if (!distributor.releaseLandingIntermediate(n - 1)) System.err.println("Releasing Landing Intermediate");
			}
		}
		
		if (!distributor.askForTermLine(acp.getTerminal(), planeId)) System.err.println("Terminal line");
		if (!distributor.releaseLandingIntermediate(acp.getTerminal())) System.err.println("Releasing Landing Intermediate");
		
		if (!distributor.releaseTermLine(acp.getTerminal())) System.err.println("Releasing Terminal line");
		
		/*
		 * 
		 * Plane in terminal
		 * 
		 */
		
		System.out.println(planeId + " is resting...");

		goToBed(20000);
		
		if (!distributor.askForTermLine(acp.getTerminal(), planeId)) System.err.println("Terminal line");
		if (!distributor.releaseTerminal(acp)) System.err.println("Release Terminal line");
		
		
		for (int n = acp.getTerminal(); n <= 3; n++) {
			
			if (!distributor.askForToIntermediate(n, planeId)) System.err.println("Landing Intermediate");
			
			if (n > acp.getTerminal()) {
				if (!distributor.releaseToIntermediate(n - 1)) System.err.println("Releasing Landing Intermediate");
			} else {
				if (!distributor.releaseTermLine(acp.getTerminal())) System.err.println("Releasing Terminal line");
			}
		}
		
		//goToBed(1000);
		
		if (!distributor.askForToCurve(planeId)) System.err.println("Take off curve");
		
		if (!distributor.askForTakeOffLane(planeId)) System.err.println("Take off line");
		if (!distributor.releaseTakeOffLane()) System.err.println("Take off line");
		
		
	}
	


	/*
	 * 
	 * To stop execution to simulate waitings.
	 * 
	 * @param time	Stop time period in ms.
	 * 
	 */
	
	private void goToBed (long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
