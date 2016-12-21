
public class Main {
	/*
	 * This main is the simulator for testing Heathrow Airport distribution synchronization.
	 * First of all, initialize the Distributor of planes.
	 * Then, creates and starts the threads to simulate the planes.
	 * 
	 * @param args For input information at the start.
	 * 
	 */
	public static void main(String[] args) {
		
		Distributor distributor = new Distributor(1);
		
		for (int i = 0; i < 1; i++) {
			Thread thread = new Thread(new AirPlane(distributor, "plane" + i));
			thread.start();
		}
	}

}
