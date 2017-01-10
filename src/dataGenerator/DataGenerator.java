package dataGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import domain.dao.DAOAirline;
import domain.dao.DAOAirport;
import domain.dao.DAOAirportController;
import domain.dao.DAOPlanePosition;
import domain.model.Airline;
import domain.model.Airplane;
import domain.model.AirplanePhoto;
import domain.model.Airport;
import domain.model.AirportController;
import domain.model.PlanePosition;

/**
 * The Class DataGenerator.
 */
public class DataGenerator {

	/**
	 * Generate random plane name.
	 *
	 * @return the string
	 */
	public static String generateRandomPlaneName() {
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

		return airplaneName[randInt(0, 8)];
	}

	/**
	 * Generate random serial number.
	 *
	 * @return the string
	 */
	public static String generateRandomSerialNumber() {
		int[] serialNumber = { 27090, 1236, 380, 2456, 12345, 2356, 4569, 89652, 58324 };
		return String.valueOf(serialNumber[randInt(0, serialNumber.length - 1)]);
	}

	/**
	 * Generate random line number.
	 *
	 * @return the int
	 */
	public static int generateRandomLineNumber() {
		int[] lineNumber = { 959, 468, 213, 156, 9843, 456, 789, 321, 852 };
		return lineNumber[randInt(0, lineNumber.length - 1)];
	}

	/**
	 * Generate random current registration.
	 *
	 * @return the string
	 */
	public static String generateRandomCurrentRegistration() {
		String[] currentRegistration = { "G-BNLY", "G-EUPK", "G-EUYI", "D-AIQE", "N276AY" };
		return currentRegistration[randInt(0, currentRegistration.length - 1)];

	}

	/**
	 * Generate random operator owner.
	 *
	 * @return the string
	 */
	public static String generateRandomOperatorOwner() {
		String[] operatorOwner = { "Xabier Jauregi", "Irati Erana", "Mikel Airzmendiarrieta", "Asier Sampietro",
				"Joanes Plazaola", "Ane Alameda" };
		return operatorOwner[randInt(0, operatorOwner.length - 1)];

	}

	/**
	 * Generate random date.
	 *
	 * @return the date
	 */
	public static Date generateRandomDate() {
		Date date = null;
		String[] year = { "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019" };
		String[] day = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
				"18", "19", "20", "21", "22", "23", "24", "25", "26" };
		String[] month = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
		String expectedPattern = "MM/dd/yyyy";
		SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
		try {
			String userInput = month[randInt(0, month.length - 1)] + "/" + day[randInt(0, day.length - 1)] + "/"
					+ year[randInt(0, year.length - 1)];
			date = formatter.parse(userInput);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return date;
		}

	}

	/**
	 * Generate random engine model.
	 *
	 * @return the string
	 */
	public static String generateRandomEngineModel() {
		String[] engineModel = { "RB211-524H2", "V2522-A5", "PW4060", "CFM56-5B4/3", "GP7270" };
		return engineModel[randInt(0, engineModel.length - 1)];
	}

	/**
	 * Generate random ours of flight.
	 *
	 * @return the int
	 */
	public static int generateRandomOursOfFlight() {
		return randInt(0, 10000);
	}

	/**
	 * Generate random number of flights.
	 *
	 * @return the int
	 */
	public static int generateRandomNumberOfFlights() {
		return randInt(0, 500);
	}

	/**
	 * Rand int.
	 *
	 * @param min the min
	 * @param max the max
	 * @return the int
	 */
	public static int randInt(int min, int max) {

		Random rand = new Random();

		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	/**
	 * Generate initial plane positon from database.
	 *
	 * @return the plane position
	 */
	public static PlanePosition generateInitialPlanePositonFromDatabase() {
		return DAOPlanePosition.loadPlanePosition(1);
	}

	/**
	 * Generate random photo list.
	 *
	 * @param airplane the airplane
	 * @return the array list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static ArrayList<AirplanePhoto> generateRandomPhotoList(Airplane airplane) throws IOException {

		ArrayList<AirplanePhoto> photoList = new ArrayList<AirplanePhoto>();
		AirplanePhoto airPhoto = new AirplanePhoto();
		File file;
		byte[] bFile;
		FileInputStream fileInputStream;

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
			file = new File("D:/Uni3/1Semester/POPBL5/plane.jpg");			
			break;
		case "Airbus A319":
			file = new File("D:/Uni3/1Semester/POPBL5/plane.jpg");
			break;
		case "Airbus  A330":
			file = new File("D:/Uni3/1Semester/POPBL5/plane.jpg");
			break;
		case "Boeing 767":
			file = new File("D:/Uni3/1Semester/POPBL5/plane.jpg");
			break;
		case "Airbus A320":
			file = new File("D:/Uni3/1Semester/POPBL5/plane.jpg");
			break;
		case "Airbur A350":
			file = new File("D:/Uni3/1Semester/POPBL5/plane.jpg");
			break;
		case "Airbus C380":
			file = new File("D:/Uni3/1Semester/POPBL5/plane.jpg");
			break;
		case "Boeing 777":
			file = new File("D:/Uni3/1Semester/POPBL5/plane.jpg");
			break;
		case "Boeing 737":
			file = new File("D:/Uni3/1Semester/POPBL5/plane.jpg");
			break;
		default:
			file = new File("D:/Uni3/1Semester/POPBL5/plane.jpg");
			break;
		}
		bFile = new byte[(int) file.length()];

		fileInputStream = new FileInputStream(file);
		// convert file into array of bytes
		fileInputStream.read(bFile);
		fileInputStream.close();
		
		airPhoto.setPhoto(bFile);		
		airPhoto.setPhotoDate(generateRandomDate());
		airPhoto.setPhotographer(photographerName[randInt(0, photographerName.length - 1)]);

		photoList.add(airPhoto);

		return photoList;
	}

	/**
	 * Generate random controller list from database.
	 *
	 * @return the array list
	 */
	public static ArrayList<AirportController> generateRandomControllerListFromDatabase() {
		ArrayList<AirportController> controllerList = new ArrayList<AirportController>();
		List<AirportController> allControllerList = DAOAirportController.loadAllAirportControllers();
		for (int kont = 0; kont < randInt(1, 4); kont++) {
			controllerList.add(allControllerList.get(allControllerList.size() - 1));
		}
		return controllerList;
	}

	/**
	 * Generate airport for location of the plane.
	 *
	 * @return the airport
	 */
	public static Airport generateAirportForLocationOfThePlane() {
		return DAOAirport.loadAirport("Heatrhow");
	}

	/**
	 * Generate random airline from databse.
	 *
	 * @return the airline
	 */
	public static Airline generateRandomAirlineFromDatabse() {
		List<Airline> airlineList = DAOAirline.loadAllAirlines();
		return airlineList.get(randInt(0, airlineList.size()-1));
	}

}
