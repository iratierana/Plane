import domain.model.Airplane;

public class AirPlane implements Runnable {
	
	Distributor distributor;
	Airplane airplane;
//	public static Semaphore planeCreationMtx = new Semaphore(1);
	
	/**
	 * 
	 * Constructor of AirPlane class.
	 * Stablishes the distributor and the planeId.
	 * 
	 * @param distributor To know where to ask permissions.
	 * @param planeId     To know which plane it is.
	 * 
	 */
	
	public AirPlane(Distributor distributor) {
		super();
		this.distributor = distributor;		
	}

	
	/**
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
		/*--------------------------PLANE CREATION-----------------------*/
		/*We need to create the planes in a exclusive way, because in the database we can only do one insert in the same moment*/
		airplane = distributor.createPlaneInExclusiveWay();
		/*------------------------------------------------------------------*/
		
		while ((time = distributor.askForLandingLane(this.airplane.getAirplaneId())) > 0) {
			
			goToBed(time);
		
		}
		
		if (!distributor.askForLandingCurve(this.airplane.getAirplaneId())) System.err.println("Landing Curve");
		
		
		AircraftParking acp = distributor.askForTerminal(this.airplane.getAirplaneId());
		
		
		for (int n = 1; n <= acp.getTerminal(); n++) {
			
			if (!distributor.askForLandingIntermediate(n, this.airplane.getAirplaneId())) System.err.println("Landing Intermediate");
			
			if (n > 1) {
				if (!distributor.releaseLandingIntermediate(n - 1)) System.err.println("Releasing Landing Intermediate");
			}
		}
		
		if (!distributor.askForTermLine(acp.getTerminal(), this.airplane.getAirplaneId())) System.err.println("Terminal line");
		if (!distributor.releaseLandingIntermediate(acp.getTerminal())) System.err.println("Releasing Landing Intermediate");
		
		if (!distributor.releaseTermLine(acp.getTerminal())) System.err.println("Releasing Terminal line");
		
		/**
		 * 
		 * Plane in terminal
		 * 
		 */
		
		System.out.println(String.valueOf(this.airplane.getAirplaneId()) + " is resting...");

		goToBed(20000);
		
		if (!distributor.askForTermLine(acp.getTerminal(), this.airplane.getAirplaneId())) System.err.println("Terminal line");
		if (!distributor.releaseTerminal(acp)) System.err.println("Release Terminal line");
		
		
		for (int n = acp.getTerminal(); n <= 3; n++) {
			
			if (!distributor.askForToIntermediate(n, this.airplane.getAirplaneId())) System.err.println("Landing Intermediate");
			
			if (n > acp.getTerminal()) {
				if (!distributor.releaseToIntermediate(n - 1)) System.err.println("Releasing Landing Intermediate");
			} else {
				if (!distributor.releaseTermLine(acp.getTerminal())) System.err.println("Releasing Terminal line");
			}
		}
		
		//goToBed(1000);
		
		if (!distributor.askForToCurve(this.airplane.getAirplaneId())) System.err.println("Take off curve");
		
		if (!distributor.askForTakeOffLane(this.airplane.getAirplaneId())) System.err.println("Take off line");
		if (!distributor.releaseTakeOffLane(this.airplane.getAirplaneId())) System.err.println("Take off line");
		
		
	}
	


	/**
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
			e.printStackTrace();
		}
	}

}
