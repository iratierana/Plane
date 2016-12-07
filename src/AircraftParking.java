
public class AircraftParking {

	int terminal;
	int position;
	String situation;

	public AircraftParking(int terminal, int position) {
		super();
		this.terminal = terminal;
		this.position = position;
		situation = "EMPTY";
	}

	public int getTerminal() {
		return terminal;
	}

	public int getPosition() {
		return position;
	}

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	@Override
	public String toString() {
		return "Aircraft Parking in terminal " + terminal + " in position " + position;
	}
	
	
	
}
