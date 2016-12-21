package dataGenerator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import domain.model.AirplanePhoto;
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
		String[] operatorOwner = { "British Airways", "American Airlines", "Austrian Airlines", "Iberia", "US Airways", "Lufthansa" };
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
			String userInput = month[randInt(0, month.length-1)]+"/"+day[randInt(0, day.length-1)]+"/"+year[randInt(0, year.length)];
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
		//TODO Datubasera koñexiñua in eta PlanePosition taulatik hasierako posiziñua hartu
		return null;
	}
	
	public static ArrayList<AirplanePhoto> generatePhotoListFromDatabase(){
		//TODO Datubasera koñexiñua in,AirplanePhoto taulatik datuak atera eta hauekin lista bat sortu
		return null;
	}
	
	public static ArrayList<AirportController> generateControllerListFromDatabase(){
		//TODO Datubasera konektau eta Airport Controller taulatik hainbat(random) controller kargau eta hauek listan kargau
		return null;
	}

}
