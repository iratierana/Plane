import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

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
	Airplane airplane = new Airplane();
	
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
		
		createAirplane();
		
		
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
	
	private ArrayList<AirportController> createControllerList(){
		
		ArrayList<AirportController> listController = new ArrayList<AirportController>();
		
		listController.add(createAirportController());
		
		return listController;
				
	}

	private PlanePosition createPlanePosition() {
		
		PlanePosition position = new PlanePosition();
		
		position.setPosition(1);
		
		Date date = Calendar.getInstance().getTime();
		position.setHour(date);
		
		return position;
	}

	private ArrayList<AirplanePhoto> createAirplanePhoto() {
		int aleatorio = 0;
		ArrayList<Date> dateL = new ArrayList<>();
		dateL.add(new Date(2016, 10, 3));
		dateL.add(new Date(2016, 10, 23));
		dateL.add(new Date(2016, 9, 28));
		dateL.add(new Date(2015, 05, 28));
		dateL.add(new Date(2014, 07, 8));
		
		String[ ] photographer  = {"Piotr Persona", "Stig Rokkones", "SMillar", "Chris Andrew Barker" , "Christopher Desantis", "Terry Wade"};
		
		ArrayList<AirplanePhoto> listPhoto = new ArrayList<AirplanePhoto>();
		
		
		AirplanePhoto airplanePhoto = new AirplanePhoto();
		
		airplanePhoto.setPhoto("../Irati");
		
		aleatorio =  (int)Math.floor(Math.random()*(dateL.size()));
		airplanePhoto.setPhotoDate(dateL.get(aleatorio));
		
		aleatorio =  (int) Math.floor(Math.random()*(photographer.length));
		airplanePhoto.setPhotographer(photographer[aleatorio]);
		
		listPhoto.add(airplanePhoto);
		
		return listPhoto;
	}
	
	private ArrayList<Airplane> createAirplaneList(){
		
		ArrayList<Airplane> listAirplane = new ArrayList<>();
		
		listAirplane.add(airplane);
		
		return listAirplane;
	}
	
	private Airport createAirport(){
		
		Airport airport = new Airport();
		
		airport.setName("Bilbao");
		airport.setRunwayList(createRunwayList());
		
		return airport;
	}
	
	private ArrayList<Runway> createRunwayList() {
		
		ArrayList<Runway> listRunway = new ArrayList<>();
		
		Runway runway = new Runway();
		
		runway.setState(true);
		runway.setTipo("curve");
		
		listRunway.add(runway);
		
		return listRunway;
	}


	private AirportController createAirportController(){
		
		AirportController controller = new AirportController();
						
		controller.setFirstName("Juan");
		controller.setLastName1("Rodriguez");
		controller.setLastName2("Fernandez");
		controller.setDni_passport("72826099W");
		controller.setHomeTlf("943534087");
		controller.setMovileTlf("697904109");
		controller.setEmail("juanrodriguezfernandez@gmail.com");
		Date date = new GregorianCalendar(1996, Calendar.FEBRUARY, 11).getTime();
		controller.setBirthDate(date);
		controller.setUsername("juanrf");
		controller.setPassword("juan");
		controller.setAirplaneList(createAirplaneList());
		controller.setAirport(createAirport());
		controller.setDirection(createDirection());
		controller.setFlightList(createFlightList());
		
		return controller;
	}
	
	

	private ArrayList<Flight> createFlightList() {
	
		ArrayList<Flight> listFlight = new ArrayList<>();
		
		Flight flight = new Flight();
		
		Date date = new GregorianCalendar(1996, Calendar.FEBRUARY, 11).getTime();
		flight.setArrivalDate(date);
		flight.setArrivalGate(4);
		flight.setArrivalTerminal(1);
		flight.setArriveAirport(createAirport());//holan?
		flight.setCortrollerList(createControllerList());//holan?
		flight.setDepartAirport(createAirport());
		flight.setDepartureDate(date);
		flight.setDepartureGate(5);
		flight.setDepartureTerminal(1);
		flight.setPassangerList(createPassangerList());
		
		return listFlight;
	}


	private ArrayList<Passanger> createPassangerList() {
		
		ArrayList<Passanger> listPassenger = new ArrayList<>();
		
		Passanger passanger = new Passanger();
		
		Date date = new GregorianCalendar(1996, Calendar.FEBRUARY, 11).getTime();
		passanger.setBirthDate(date);
		passanger.setDirection(createDirection());
		passanger.setDni_passport("72826098W");
		passanger.setEmail("guau@guau.com");
		passanger.setFirstName("Irati");
		passanger.setFlightList(createFlightList());
		passanger.setHomeTlf("943534087");
		passanger.setLastName1("Eraña");
		passanger.setLastName2("Robles");
		passanger.setMovileTlf("697904109");
		
		listPassenger.add(passanger);
		
		return listPassenger;
	}


	private Direction createDirection() {
		
		Direction direction = new Direction();
		
		direction.setAddress("P Shermal Calle Walaby 42");
		direction.setCity("Sydney");
		direction.setCodPost("2060");
		direction.setState("New South Wales");
		
		
		return direction;
	}


	private void createAirplane() {
		int aleatorio = 0;
		int number = 0;
		int number2 = 0;
		
		String[ ] airplaneName = new String[9];
		airplaneName[0] = "Boeing 747";
		airplaneName[1] = "Airbus A319";
		airplaneName[2] = "Airbus  A330";
		airplaneName[3] = "Boeing 767";
		airplaneName[4] = "Airbus A320";
		airplaneName[5] = "Airbur A350";
		airplaneName[6] = "Airbus C380";
		airplaneName[7] = "Boeing 777";
		airplaneName[8] = "Boeing 737";
		
		int[ ] serialNumber = {27090, 1236, 380, 2456, 12345, 2356, 4569, 89652, 58324};
		int[ ] lineNumber = {959, 468, 213, 156, 9843, 456, 789, 321, 852};
		String[ ] currentRegistration = {"G-BNLY", "G-EUPK", "G-EUYI", "D-AIQE", "N276AY"};
		String[ ] operatorOwner  = {"British Airways", "American Airlines", "Austrian Airlines", "Iberia" , "US Airways", "Lufthansa"};
	        
		ArrayList<Date> dateL = new ArrayList<>();
		
		dateL.add(new Date(2016, 10, 3));
		dateL.add(new Date(2016, 10, 23));
		dateL.add(new Date(2016, 9, 28));
		dateL.add(new Date(2015, 05, 28));
		dateL.add(new Date(2014, 07, 8));
		
		
		String[ ] engineModel = {"RB211-524H2", "V2522-A5", "PW4060", "CFM56-5B4/3", "GP7270"};
		
		

		aleatorio = (int) Math.floor(Math.random()*(airplaneName.length));
		airplane.setName(airplaneName[aleatorio]);
		System.out.println("Name:"+airplaneName[aleatorio]);
		
		aleatorio = (int) Math.floor(Math.random()*(serialNumber.length));
		number=serialNumber[aleatorio];
		airplane.setSerialNumb("number");
		System.out.println("Serial number:"+number);
		
		aleatorio = (int) Math.floor(Math.random()*(lineNumber.length));
		number2=lineNumber[aleatorio];
		airplane.setLineNumber(number2);
		System.out.println("Line Number"+number2);
		
		aleatorio =  (int) Math.floor(Math.random()*(currentRegistration.length));
		airplane.setCurrentRegistration(currentRegistration[aleatorio]);
		System.out.println("Current registration:"+currentRegistration[aleatorio]);
		
		aleatorio =  (int) Math.floor(Math.random()*(operatorOwner.length));
		airplane.setOperatorOwner(operatorOwner[aleatorio]);
		System.out.println("Operator owner:"+operatorOwner[aleatorio]);
		
		aleatorio =  (int)Math.floor(Math.random()*(dateL.size()));
		airplane.setDeliberyDate(dateL.get(aleatorio));
		System.out.println("Date:"+dateL.get(aleatorio));
					
		aleatorio =  (int) Math.floor(Math.random()*(engineModel.length));
		airplane.setEngineModel(engineModel[aleatorio]);
		System.out.println("Engine model:"+engineModel[aleatorio]);
		
		airplane.setStatus(true);
		airplane.setHoursOfFlight(0);
		airplane.setNumberOfFlights(0);
		
		airplane.setPlanePosition(createPlanePosition());
		airplane.setPhotoList(createAirplanePhoto());
		airplane.setCotrollerList(createControllerList());
		
		DAOAirplane.insertAirplane(airplane);
		System.out.println("Plane created");
		
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
