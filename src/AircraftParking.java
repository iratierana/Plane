
public class AircraftParking {

	int terminal;
	int position;
	String situation;

	/*
	 * 
	 * Constructor of AircraftParking class.
	 * Initialize the terminal, position and the situation of the parking.
	 * 
	 * @param position To know the position of the parking.
	 * @param terminal To know in which terminal is the parking.
	 * 
	 */
	
	public AircraftParking(int terminal, int position) {
		super();
		this.terminal = terminal;
		this.position = position;
		situation = "EMPTY";
	}
	
	/*
	 * 
	 * To get the terminal in which the airplane is going to park.
	 * 
	 * @return It returns the terminal number.
	 * 
	 */

	public int getTerminal() {
		return terminal;
	}

	/*
	 * 
	 * To get the position in which the airplane is going to park.
	 * 
	 * @return It returns the position.
	 * 
	 */
	
	public int getPosition() {
		return position;
	}

	/*
	 * 
	 * To get the situation of the parking(if it is empty or not).
	 * 
	 * @return It returns the situation of the parking.
	 * 
	 */
	
	public String getSituation() {
		return situation;
	}

	/*
	 * 
	 * To set the situation of the parking(if it is empty or not).
	 * 
	 * @return It returns the situation of the parking.
	 * 
	 */
	
	public void setSituation(String situation) {
		this.situation = situation;
	}

	/*
	 * 
	 * To give format to the information of the aircraft parking.
	 * 
	 * @return It returns format of the information.
	 * 
	 */
	
	@Override
	public String toString() {
		return "Aircraft Parking in terminal " + terminal + " in position " + position;
	}
	
	
	
}
