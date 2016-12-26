package dataGenerator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import domain.dao.DAOAirport;
import domain.dao.DAOAirportController;
import domain.dao.DAOPlanePosition;
import domain.model.Airline;
import domain.model.Airplane;
import domain.model.AirplanePhoto;
import domain.model.Airport;
import domain.model.AirportController;
import domain.model.PlanePosition;

public class DataGenerator {
	
	public static String generateRandomPlaneName(){
		String[] airplaneName = new String[9];
		airplaneName[0] = "Boeing 747";
		airplaneName[1] = "Airbus A319";
		airplaneName[2] = "Airbus  A330";
		airplaneName[3] = "Boeing 767";
		airplaneName[4] = "Airbus A320";
		airplaneName[5] = "Airbur A350";
		airplaneName[6] = "Airbus C380";
		airplaneName[7] = "Boeing 777";
		airplaneName[8] = "Boeing 737";
		
		return airplaneName[randInt(0,8)];
	}
	
	public static String generateRandomSerialNumber(){
		int[] serialNumber = { 27090, 1236, 380, 2456, 12345, 2356, 4569, 89652, 58324 };
		return String.valueOf(serialNumber[randInt(0, serialNumber.length-1)]);
	}
	
	public static int generateRandomLineNumber(){
		int[] lineNumber = { 959, 468, 213, 156, 9843, 456, 789, 321, 852 };
		return lineNumber[randInt(0, lineNumber.length-1)];
	}
	
	public static String generateRandomCurrentRegistration(){
		String[] currentRegistration = { "G-BNLY", "G-EUPK", "G-EUYI", "D-AIQE", "N276AY" };
		return currentRegistration[randInt(0, currentRegistration.length-1)];

	}
	
	public static String generateRandomOperatorOwner(){
		String[] operatorOwner = { "Xabier Jauregi", "Irati Erana", "Mikel Airzmendiarrieta", "Asier Sampietro", "Joanes Plazaola", "Ane Alameda" };
		return operatorOwner[randInt(0, operatorOwner.length-1)];

	}

	public static Date generateRandomDate(){
		Date date = null;
		String[] year={"2010","2011","2012","2013","2014","2015","2016","2017","2018","2019"};
		String[] day={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26"};
		String[] month={"1","2","3","4","5","6","7","8","9","10","11","12"};
		String expectedPattern = "MM/dd/yyyy";
		SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
		try {
			String userInput = month[randInt(0, month.length-1)]+"/"+day[randInt(0, day.length-1)]+"/"+year[randInt(0, year.length-1)];
			date = formatter.parse(userInput);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return date;
		}
		
	}
	
	public static String generateRandomEngineModel(){
		String[] engineModel = { "RB211-524H2", "V2522-A5", "PW4060", "CFM56-5B4/3", "GP7270" };	
		return engineModel[randInt(0, engineModel.length-1)];
	}
	
	public static int generateRandomOursOfFlight(){
		return randInt(0, 10000);
	}
	public static int generateRandomNumberOfFlights(){
		return randInt(0, 500);
	}
	
	public static int randInt(int min, int max) {

	    
	    Random rand = new Random();

	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public static PlanePosition generateInitialPlanePositonFromDatabase(){
		return DAOPlanePosition.loadPlanePosition(1);
	}
	
	public static ArrayList<AirplanePhoto> generateRandomPhotoList(Airplane airplane){
		
		ArrayList<AirplanePhoto> photoList = new ArrayList<AirplanePhoto>();
		AirplanePhoto airPhoto = new AirplanePhoto();
		
		String[] photographerName = new String[9];
		photographerName[0] = "Annie Leibovitz";
		photographerName[1] = "Sebastiao Salgado";
		photographerName[2] = "Steve McCurry";
		photographerName[3] = "James Nachtwey";
		photographerName[4] = "David LaChapelle";
		photographerName[5] = "Jill Greenberg";
		photographerName[6] = "David Doubillet";
		photographerName[7] = "Carolyn Drake";
		photographerName[8] = "Loretta Lux";
		
		switch (airplane.getName()) {
			case "Boeing 747":
				airPhoto.setPhoto("Boeing 747.png");
				break;
			case "Airbus A319":
				airPhoto.setPhoto("Airbus A319.png");		
				break;
			case "Airbus  A330":
				airPhoto.setPhoto("Airbus  A330.png");		
				break;
			case "Boeing 767":
				airPhoto.setPhoto("Boeing 767.png");
				break;
			case "Airbus A320":
				airPhoto.setPhoto("Airbus A320.png");
				break;
			case "Airbur A350":
				airPhoto.setPhoto("Airbur A350.png");
				break;
			case "Airbus C380":
				airPhoto.setPhoto("Airbus C380.png");
				break;			
			case "Boeing 777":
				airPhoto.setPhoto("Boeing 777.png");
				break;
			case "Boeing 737":
				airPhoto.setPhoto("Boeing 737.png");
				break;
			default:
				airPhoto.setPhoto("UNDEFINED");
				break;
		}
				
		airPhoto.setPhotoDate(generateRandomDate());
		airPhoto.setPhotographer(photographerName[randInt(0, photographerName.length-1)]);
		
		photoList.add(airPhoto);
		
		return photoList;
	}
	
	public static ArrayList<AirportController> generateRandomControllerListFromDatabase(){
		ArrayList<AirportController> controllerList = new ArrayList<AirportController>();
		List<AirportController> allControllerList = DAOAirportController.loadAllAirportControllers();
		for(int kont=0; kont<randInt(1, 4); kont++){
			controllerList.add(allControllerList.get(allControllerList.size()-1));
		}
		return controllerList;
	}
	
	public static Airport generateAirportForLocationOfThePlane(){
		return DAOAirport.loadAirport("Heatrhow");
	}
	
	@SuppressWarnings("null")
	public static Airline generateRandomAirline(){
		Airline airline = new Airline();
		String[] airlineNameList = { "British Airways", "American Airlines", "Austrian Airlines", "Iberia", "US Airways", "Lufthansa" };
		airline.setDescription("This is a airline description");
		airline.setName(airlineNameList[randInt(0, airlineNameList.length-1)]);
		return airline;
	}

}
