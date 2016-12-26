package dataGenerator;


import java.io.IOException;

import domain.dao.DAOAirplane;
import domain.model.Airplane;

public class PlaneGenerator {

	public static Airplane createAirplane() {
		Airplane airplane = new Airplane();		
		try {
			airplane.setName(DataGenerator.generateRandomPlaneName());
			airplane.setSerialNumb(DataGenerator.generateRandomSerialNumber());
			airplane.setLineNumber(DataGenerator.generateRandomLineNumber());
			airplane.setCurrentRegistration(DataGenerator.generateRandomCurrentRegistration());
			airplane.setOperatorOwner(DataGenerator.generateRandomOperatorOwner());
			airplane.setDeliberyDate(DataGenerator.generateRandomDate());
			airplane.setEngineModel(DataGenerator.generateRandomEngineModel());
			airplane.setStatus(true);
			airplane.setHoursOfFlight(DataGenerator.generateRandomOursOfFlight());
			airplane.setNumberOfFlights(DataGenerator.generateRandomNumberOfFlights());
			airplane.setPlanePosition(DataGenerator.generateInitialPlanePositonFromDatabase());
			airplane.setPhotoList(DataGenerator.generateRandomPhotoList(airplane));
			airplane.setCotrollerList(DataGenerator.generateRandomControllerListFromDatabase());
			airplane.setAirport(DataGenerator.generateAirportForLocationOfThePlane());
			airplane.setAirline(DataGenerator.generateRandomAirlineFromDatabse());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		DAOAirplane.insertAirplane(airplane);
		
		System.out.println("Plane created");

		return airplane;
	}



}
