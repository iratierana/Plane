
public class Main {

	public static void main(String[] args) {
		
		Distributor distributor = new Distributor(1);
		
		for (int i = 0; i < 1500; i++) {
			Thread thread = new Thread(new AirPlane(distributor, "plane" + i));
			thread.start();
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
