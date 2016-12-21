package dataGenerator;


import domain.dao.DAOAirplane;
import domain.model.Airplane;

public class PlaneGenerator {

	public static Airplane createAirplane() {
		Airplane airplane = new Airplane();
		

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
		//TODO airplane.setPlanePosition();
		//TODO airplane.setPhotoList();
		//TODO airplane.setCotrollerList();
		//TODO airplane.setAirport();

		DAOAirplane.insertAirplane(airplane);
		
		System.out.println("Plane created");

		return airplane;
	}



}
