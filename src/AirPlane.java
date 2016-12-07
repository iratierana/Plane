
public class AirPlane implements Runnable {

	Distributor distributor;
	String planeId;
	
	public AirPlane(Distributor distributor, String planeId) {
		super();
		this.distributor = distributor;
		this.planeId = planeId;
	}

	@Override
	public void run() {
		
		long time = 0;
		
		
		while ((time = distributor.askForLandingLane(planeId)) > 0) {
			
			//goToBed(time);
		
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

		//goToBed(200);
		
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
	
	private void goToBed (long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
