import static org.junit.Assert.*;

import org.junit.Test;

public class TestAircraftParking {

	
	@Test
	public void testIfTheConstructorIsWorkingFine(){
		AircraftParking a;
		
		a = new AircraftParking(1, 5);
		
		assertNotNull("The aircraft parking was NOT created", a);
	}
}
