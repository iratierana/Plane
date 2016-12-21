package dataGenerator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
		//TODO airplane.setFlightList();    //Usteot hau ez dala bihar
		//TODO airplane.setAirport();

		DAOAirplane.insertAirplane(airplane);
		
		System.out.println("Plane created");

		return airplane;
	}



}
