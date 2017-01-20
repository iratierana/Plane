package dataGenerator;

import java.util.ArrayList;

import domain.dao.DAOAirport;
import domain.model.Airplane;
import domain.model.Flight;
import domain.model.Passanger;

public class FlightGenerator {
	
	
	public static void createFlight(Airplane airplane){
		
		Flight flight = new Flight();
		try{
			flight.setDepartureDate(DataGenerator.generateRandomDateToFlight(1, null));
			flight.setArrivalDate(DataGenerator.generateRandomDateToFlight(2, flight.getDepartureDate()));
			flight.setArrivalGate(DataGenerator.randInt(1, 6));
			flight.setArrivalTerminal(DataGenerator.randInt(1, 4));
			flight.setArriveAirport(DataGenerator.generateAirportForLocationOfThePlane());
			flight.setCortrollerList(DataGenerator.generateRandomControllerListFromDatabase());
			flight.setDepartAirport(DataGenerator.loadRandomAirport());			
			flight.setDepartureGate(DataGenerator.randInt(1, 6));
			flight.setDepartureTerminal(DataGenerator.randInt(1, 4));
//			flight.setFlightId(flightId);
			flight.setPassangerList(new ArrayList<Passanger>());
			flight.setAirplane(airplane);
			
			DAOAirport.insertFlightInDatabase(flight);
			System.out.println("Flight created");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	

}
