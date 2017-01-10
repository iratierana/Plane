/*
 * 
 */
import java.util.concurrent.Semaphore;

/**
 * This main is the simulator for testing Heathrow Airport distribution synchronization.
 * First of all, initialize the Distributor of planes.
 * Then, creates and starts the threads to simulate the planes.
 */
public class Main {
	
	/** The airplanes number in airplane. */
	public static Semaphore airplanesNumberInAirplane = new Semaphore(2);
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		
		Distributor distributor = new Distributor(1);
		Thread thread=null;
		
		while(true) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(Main.airplanesNumberInAirplane.tryAcquire()){
				thread = new Thread(new AirPlane(distributor));
				thread.start();
			}
		}
	}

}
