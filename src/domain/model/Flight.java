package domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * The Class Flight.
 */
@Entity
public class Flight {
	
	/**
	 * The flight id.
	 *
	 * @author Xabier Jauregi
	 * @author Irati Erana
	 * @author Mikel Arizmendiarrieta
	 * @version 1.0
	 * @since   2016-12-13
	 * 
	 * General Entity of Flight class.
	 * Is connected with hibernate to generate the table Flight and the needed relationshihps.
	 * Is used to save and work with the information of a flight.
	 */
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE )
	private Integer flightId;
	
	/** The departure date. */
	@NotNull
	private Date departureDate;
	
	/** The arrival date. */
	@NotNull
	private Date arrivalDate;
	
	/** The departure gate. */
	@NotNull
	private Integer departureGate;
	
	/** The arrival gate. */
	@NotNull
	private Integer arrivalGate;
	
	/** The arrival terminal. */
	@NotNull
	private Integer arrivalTerminal;
	
	/** The departure terminal. */
	@NotNull
	private Integer departureTerminal;
	
	
	
	/** The cortroller list. */
	@ManyToMany (cascade = CascadeType.ALL)
	@JoinTable(name="ControlFlight")
	Collection<AirportController> cortrollerList=new ArrayList<AirportController>();
	
	/** The depart airport. */
	@OneToOne (cascade = CascadeType.ALL)
	Airport departAirport;
	
	/** The arrive airport. */
	@OneToOne (cascade = CascadeType.ALL)
	Airport arriveAirport;
	
	/** The airplane. */
	@OneToOne (cascade = CascadeType.ALL)
	Airplane airplane;
	
	/** The passanger list. */
	@ManyToMany(fetch=FetchType.EAGER, mappedBy="flightList")
	Collection<Passanger> passangerList = new ArrayList<Passanger>();

	
	
	
	/**
	 * Gets the flight id.
	 *
	 * @return the flight id
	 */
	public Integer getFlightId() {
		return flightId;
	}

	/**
	 * Sets the flight id.
	 *
	 * @param flightId the new flight id
	 */
	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}

	/**
	 * Gets the departure date.
	 *
	 * @return the departure date
	 */
	public Date getDepartureDate() {
		return departureDate;
	}

	/**
	 * Sets the departure date.
	 *
	 * @param departureDate the new departure date
	 */
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	/**
	 * Gets the arrival date.
	 *
	 * @return the arrival date
	 */
	public Date getArrivalDate() {
		return arrivalDate;
	}

	/**
	 * Sets the arrival date.
	 *
	 * @param arrivalDate the new arrival date
	 */
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	/**
	 * Gets the departure gate.
	 *
	 * @return the departure gate
	 */
	public Integer getDepartureGate() {
		return departureGate;
	}

	/**
	 * Sets the departure gate.
	 *
	 * @param departureGate the new departure gate
	 */
	public void setDepartureGate(Integer departureGate) {
		this.departureGate = departureGate;
	}

	/**
	 * Gets the arrival gate.
	 *
	 * @return the arrival gate
	 */
	public Integer getArrivalGate() {
		return arrivalGate;
	}

	/**
	 * Sets the arrival gate.
	 *
	 * @param arrivalGate the new arrival gate
	 */
	public void setArrivalGate(Integer arrivalGate) {
		this.arrivalGate = arrivalGate;
	}

	/**
	 * Gets the arrival terminal.
	 *
	 * @return the arrival terminal
	 */
	public Integer getArrivalTerminal() {
		return arrivalTerminal;
	}

	/**
	 * Sets the arrival terminal.
	 *
	 * @param arrivalTerminal the new arrival terminal
	 */
	public void setArrivalTerminal(Integer arrivalTerminal) {
		this.arrivalTerminal = arrivalTerminal;
	}

	/**
	 * Gets the departure terminal.
	 *
	 * @return the departure terminal
	 */
	public Integer getDepartureTerminal() {
		return departureTerminal;
	}

	/**
	 * Sets the departure terminal.
	 *
	 * @param departureTerminal the new departure terminal
	 */
	public void setDepartureTerminal(Integer departureTerminal) {
		this.departureTerminal = departureTerminal;
	}

	/**
	 * Gets the cortroller list.
	 *
	 * @return the cortroller list
	 */
	public Collection<AirportController> getCortrollerList() {
		return cortrollerList;
	}

	/**
	 * Sets the cortroller list.
	 *
	 * @param cortrollerList the new cortroller list
	 */
	public void setCortrollerList(Collection<AirportController> cortrollerList) {
		this.cortrollerList = cortrollerList;
	}

	/**
	 * Gets the depart airport.
	 *
	 * @return the depart airport
	 */
	public Airport getDepartAirport() {
		return departAirport;
	}

	/**
	 * Sets the depart airport.
	 *
	 * @param departAirport the new depart airport
	 */
	public void setDepartAirport(Airport departAirport) {
		this.departAirport = departAirport;
	}

	/**
	 * Gets the arrive airport.
	 *
	 * @return the arrive airport
	 */
	public Airport getArriveAirport() {
		return arriveAirport;
	}

	/**
	 * Sets the arrive airport.
	 *
	 * @param arriveAirport the new arrive airport
	 */
	public void setArriveAirport(Airport arriveAirport) {
		this.arriveAirport = arriveAirport;
	}

	/**
	 * Gets the passanger list.
	 *
	 * @return the passanger list
	 */
	public Collection<Passanger> getPassangerList() {
		return passangerList;
	}

	/**
	 * Sets the passanger list.
	 *
	 * @param passangerList the new passanger list
	 */
	public void setPassangerList(Collection<Passanger> passangerList) {
		this.passangerList = passangerList;
	}

	public Airplane getAirplane() {
		return airplane;
	}

	public void setAirplane(Airplane airplane) {
		this.airplane = airplane;
	}
	
	
	
}
