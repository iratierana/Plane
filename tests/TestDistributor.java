import static org.junit.Assert.*;

import org.junit.Test;

public class TestDistributor {

	
	@Test
	public void testIfTheSemaphoresAreInitilicedWithSpecificTokens(){
		Distributor d;
		
		d = new Distributor(3);
		
		assertNotNull("The distributor was not created correctly", d);
	}
	
	@Test
	public void testAskForLandingLaneIfblocksTheLandingLane(){
		Distributor d = new Distributor(1);
		
		d.askForLandingLane(1);
		
		assertFalse("The landing lane was not blocked", d.landingLane.tryAcquire());
	}
	
	@Test
	public void testAskForLandingLaneIfTheLineIsFree(){
		Distributor d = new Distributor(1);
		
		d.askForLandingLane(1);
		d.landingLane.release();
		assertTrue("The landing lane was not blocked", d.landingLane.tryAcquire());
	}
	
	@Test
	public void testAskForLandingLaneIfTheLineIsPreviouslyOcuuped(){
		Distributor d = new Distributor(1);
		d.landingWaitingTime = (long) 1100; 
		d.landingLane.tryAcquire();
		d.askForLandingLane(1);
		
		assertFalse("The landing lane was not blocked ok", d.landingLane.tryAcquire());
	}
	
	@Test
	public void testAskForLandingCurveWithASpaceInThe(){
		Distributor d = new Distributor(1);
		d.askForLandingCurve(1);
		assertTrue("The curve as not reserved", d.landingLane.tryAcquire());
	}
	
	@Test
	public void testAskForTerminalWithACorrectPlaneAndFreeSpace(){
		Distributor d = new Distributor(1);
		
		d.askForTerminal(1);
		String situation = d.aircraftParkings.get(1).getSituation();
		
		assertEquals("The terminal was not locked", "EMPTY", situation);
	}
	
	@Test
	public void testReleaseTerminal(){
		Distributor d = new Distributor(1);
		AircraftParking aP = new AircraftParking(1, 5);
		aP.setSituation("FULL");
		assertTrue("The terminal was not released", d.releaseTerminal(aP));		
	}
	
	@Test
	public void testAskForLandingIntermediateLane(){
		Distributor d = new Distributor(1);
				
		assertTrue("Landing intermediate line can not be reserved", d.askForLandingIntermediate(1,1));
	}
	
	@Test
	public void testAskForLandingIntermediateLaneWithSecondIntermediateNumb(){
		Distributor d = new Distributor(1);
				
		assertTrue("Second landing intermediate line can not be reserved", d.askForLandingIntermediate(2,1));
	}
	
	@Test
	public void testReleaseLandingIntermediate(){
		Distributor d = new Distributor(1);
		d.askForLandingIntermediate(1,1);
		assertTrue("Landin intermediate line can not be released", d.releaseLandingIntermediate(1));
	}
	
	@Test
	public void testAskForTermLine(){
		Distributor d = new Distributor(1);
		assertTrue("Terminal line can not be reserved",d.askForTermLine(1,1));
	}
	
	@Test
	public void testReleaseTermLine(){
		Distributor d = new Distributor(1);
		d.askForTermLine(1,1);
		assertTrue("Terminal can not be released", d.releaseTermLine(1));
	}
	
	@Test
	public void testAskForToIntermediate(){
		Distributor d = new Distributor(1);
		assertTrue("The take off intermediate line can not be reserved", d.askForToIntermediate(1, 1));
	}
	
	@Test
	public void testReleaseToIntermediate(){
		Distributor d = new Distributor(1);
		d.askForToIntermediate(1, 1);
		assertTrue("Error trying to release take off intermediate line", d.releaseToIntermediate(1));
	}
	
	@Test
	public void testAskForToCurve(){
		Distributor d = new Distributor(1);
		assertTrue("Error while reserving the take off curve", d.askForToCurve(1));
	}
	
	@Test
	public void testAskForTakeOffLane(){
		Distributor d = new Distributor(1);
		assertTrue("Error while reserving the take off line", d.askForTakeOffLane(1));
	}
	
	@Test
	public void testReleaseTakeOffLane(){
		Distributor d = new Distributor(1);
		d.askForTakeOffLane(1);
		assertTrue("Error while trying to release the take off line", d.releaseTakeOffLane(1));
	}
	
	@Test
	public void testUpdateParkingPositionInDatabaseTestForAllPositions(){
		Distributor d = new Distributor(1);
		AircraftParking aP;
		aP = new AircraftParking(1, 1);
		assertTrue("Error updating to terminal 1", d.updateParkingPositionInDatabase(1, aP));
		aP = new AircraftParking(1, 2);
		assertTrue("Error updating to terminal 1", d.updateParkingPositionInDatabase(1, aP));	
		aP = new AircraftParking(1, 3);
		assertTrue("Error updating to terminal 1", d.updateParkingPositionInDatabase(1, aP));
		aP = new AircraftParking(1, 4);
		assertTrue("Error updating to terminal 1", d.updateParkingPositionInDatabase(1, aP));
		aP = new AircraftParking(1, 5);
		assertTrue("Error updating to terminal 1", d.updateParkingPositionInDatabase(1, aP));
		aP = new AircraftParking(1, 6);
		assertTrue("Error updating to terminal 1", d.updateParkingPositionInDatabase(1, aP));
		
		aP = new AircraftParking(2, 1);
		assertTrue("Error updating to terminal 1", d.updateParkingPositionInDatabase(1, aP));
		aP = new AircraftParking(2, 2);
		assertTrue("Error updating to terminal 1", d.updateParkingPositionInDatabase(1, aP));	
		aP = new AircraftParking(2, 3);
		assertTrue("Error updating to terminal 1", d.updateParkingPositionInDatabase(1, aP));
		aP = new AircraftParking(2, 4);
		assertTrue("Error updating to terminal 1", d.updateParkingPositionInDatabase(1, aP));
		aP = new AircraftParking(2, 5);
		assertTrue("Error updating to terminal 1", d.updateParkingPositionInDatabase(1, aP));
		aP = new AircraftParking(2, 6);
		assertTrue("Error updating to terminal 1", d.updateParkingPositionInDatabase(1, aP));
		
		aP = new AircraftParking(3, 1);
		assertTrue("Error updating to terminal 1", d.updateParkingPositionInDatabase(1, aP));
		aP = new AircraftParking(3, 2);
		assertTrue("Error updating to terminal 1", d.updateParkingPositionInDatabase(1, aP));	
		aP = new AircraftParking(3, 3);
		assertTrue("Error updating to terminal 1", d.updateParkingPositionInDatabase(1, aP));
		aP = new AircraftParking(3, 4);
		assertTrue("Error updating to terminal 1", d.updateParkingPositionInDatabase(1, aP));
		aP = new AircraftParking(3, 5);
		assertTrue("Error updating to terminal 1", d.updateParkingPositionInDatabase(1, aP));
		aP = new AircraftParking(3, 6);
		assertTrue("Error updating to terminal 1", d.updateParkingPositionInDatabase(1, aP));
		
		aP = new AircraftParking(4, 1);
		assertTrue("Error updating to terminal 1", d.updateParkingPositionInDatabase(1, aP));
		aP = new AircraftParking(4, 2);
		assertTrue("Error updating to terminal 1", d.updateParkingPositionInDatabase(1, aP));	
		aP = new AircraftParking(4, 3);
		assertTrue("Error updating to terminal 1", d.updateParkingPositionInDatabase(1, aP));
		aP = new AircraftParking(4, 4);
		assertTrue("Error updating to terminal 1", d.updateParkingPositionInDatabase(1, aP));
		aP = new AircraftParking(4, 5);
		assertTrue("Error updating to terminal 1", d.updateParkingPositionInDatabase(1, aP));
		aP = new AircraftParking(4, 6);
		assertTrue("Error updating to terminal 1", d.updateParkingPositionInDatabase(1, aP));
	}
	
	@Test
	public void testCreatePlaneInExclusiveWay(){
		Distributor d = new Distributor(1);
		assertNotNull("The airplne was no created", d.createPlaneInExclusiveWay());
	}
}
