package domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
/**
 * The Class Airplane.
 */
@Entity
public class Airplane {
	
	/**
	 * The airplane id.
	 *
	 * @author Xabier Jauregi
	 * @author Irati Erana
	 * @author Mikel Arizmendiarrieta
	 * @version 1.0
	 * @since   2016-12-13
	 * 
	 * General Entity of Airplane class.
	 * Is connected with hibernate to generate the table Airplane and the needed relationshihps.
	 * Is used to save and work with the information of a airplane.
	 */
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE )
	private Integer airplaneId;
	
	/** The name. */
	@NotNull
	private String name;
	
	/** The serial numb. */
	@NotNull
	private String serialNumb;
		
	/** The line number. */
	@NotNull
	private Integer lineNumber;
	
	/** The current registration. */
	@NotNull
	private String currentRegistration;
	
	/** The operator owner. */
	@NotNull
	private String operatorOwner;
	
	/** The delibery date. */
	@NotNull
	private Date deliberyDate;
	
	/** The engine model. */
	@NotNull
	private String engineModel;
	
	/** The status. */
	@NotNull
	private Boolean status;
	
	/** The number of flights. */
	@NotNull
	private Integer numberOfFlights;
	
	/** The hours of flight. */
	@NotNull
	private Integer hoursOfFlight;
	
	
//	@OneToMany
//	@JoinColumn(name="AIRPLANE_ID", nullable=false)
//	Collection<Flight> flightList = new ArrayList<Flight>();
	
	/** The airport. */
@OneToOne(cascade=CascadeType.ALL)
	Airport airport;
	
	/** The airline. */
	@OneToOne(cascade=CascadeType.ALL)
	Airline airline;
	
	/** The cotroller list. */
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="ControlAirplane")
	Collection<AirportController> cotrollerList= new ArrayList<AirportController>();
	
	/** The plane position. */
	@OneToOne(cascade=CascadeType.ALL)
	PlanePosition planePosition;

	
	/** The photo list. */
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="AIRPLANE_ID", nullable=false)
	Collection<AirplanePhoto> photoList=new ArrayList<AirplanePhoto>();
	
	
	/**
	 * Gets the airline.
	 *
	 * @return the airline
	 */
	public Airline getAirline() {
		return airline;
	}

	/**
	 * Sets the airline.
	 *
	 * @param airline the new airline
	 */
	public void setAirline(Airline airline) {
		this.airline = airline;
	}
	
	/**
	 * Gets the photo list.
	 *
	 * @return the photo list
	 */
	public Collection<AirplanePhoto> getPhotoList() {
		return photoList;
	}

	/**
	 * Sets the photo list.
	 *
	 * @param photoList the new photo list
	 */
	public void setPhotoList(Collection<AirplanePhoto> photoList) {
		this.photoList = photoList;
	}

	/**
	 * Gets the airplane id.
	 *
	 * @return the airplane id
	 */
	public Integer getAirplaneId() {
		return airplaneId;
	}

	/**
	 * Sets the airplane id.
	 *
	 * @param airplaneId the new airplane id
	 */
	public void setAirplaneId(Integer airplaneId) {
		this.airplaneId = airplaneId;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the serial numb.
	 *
	 * @return the serial numb
	 */
	public String getSerialNumb() {
		return serialNumb;
	}

	/**
	 * Sets the serial numb.
	 *
	 * @param serialNumb the new serial numb
	 */
	public void setSerialNumb(String serialNumb) {
		this.serialNumb = serialNumb;
	}

	
	/**
	 * Gets the line number.
	 *
	 * @return the line number
	 */
	public Integer getLineNumber() {
		return lineNumber;
	}

	/**
	 * Sets the line number.
	 *
	 * @param lineNumber the new line number
	 */
	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}

	/**
	 * Gets the current registration.
	 *
	 * @return the current registration
	 */
	public String getCurrentRegistration() {
		return currentRegistration;
	}

	/**
	 * Sets the current registration.
	 *
	 * @param currentRegistration the new current registration
	 */
	public void setCurrentRegistration(String currentRegistration) {
		this.currentRegistration = currentRegistration;
	}

	/**
	 * Gets the operator owner.
	 *
	 * @return the operator owner
	 */
	public String getOperatorOwner() {
		return operatorOwner;
	}

	/**
	 * Sets the operator owner.
	 *
	 * @param operatorOwner the new operator owner
	 */
	public void setOperatorOwner(String operatorOwner) {
		this.operatorOwner = operatorOwner;
	}

	/**
	 * Gets the delibery date.
	 *
	 * @return the delibery date
	 */
	public Date getDeliberyDate() {
		return deliberyDate;
	}

	/**
	 * Sets the delibery date.
	 *
	 * @param deliberyDate the new delibery date
	 */
	public void setDeliberyDate(Date deliberyDate) {
		this.deliberyDate = deliberyDate;
	}

	/**
	 * Gets the engine model.
	 *
	 * @return the engine model
	 */
	public String getEngineModel() {
		return engineModel;
	}

	/**
	 * Sets the engine model.
	 *
	 * @param engineModel the new engine model
	 */
	public void setEngineModel(String engineModel) {
		this.engineModel = engineModel;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
	 * Gets the number of flights.
	 *
	 * @return the number of flights
	 */
	public Integer getNumberOfFlights() {
		return numberOfFlights;
	}

	/**
	 * Sets the number of flights.
	 *
	 * @param numberOfFlights the new number of flights
	 */
	public void setNumberOfFlights(Integer numberOfFlights) {
		this.numberOfFlights = numberOfFlights;
	}

	/**
	 * Gets the hours of flight.
	 *
	 * @return the hours of flight
	 */
	public Integer getHoursOfFlight() {
		return hoursOfFlight;
	}

	/**
	 * Sets the hours of flight.
	 *
	 * @param hoursOfFlight the new hours of flight
	 */
	public void setHoursOfFlight(Integer hoursOfFlight) {
		this.hoursOfFlight = hoursOfFlight;
	}

//	public Collection<Flight> getFlightList() {
//		return flightList;
//	}
//
//	public void setFlightList(Collection<Flight> flightList) {
//		this.flightList = flightList;
//	}

	/**
 * Gets the airport.
 *
 * @return the airport
 */
public Airport getAirport() {
		return airport;
	}

	/**
	 * Sets the airport.
	 *
	 * @param airport the new airport
	 */
	public void setAirport(Airport airport) {
		this.airport = airport;
	}

	/**
	 * Gets the cotroller list.
	 *
	 * @return the cotroller list
	 */
	public Collection<AirportController> getCotrollerList() {
		return cotrollerList;
	}

	/**
	 * Sets the cotroller list.
	 *
	 * @param cotrollerList the new cotroller list
	 */
	public void setCotrollerList(Collection<AirportController> cotrollerList) {
		this.cotrollerList = cotrollerList;
	}

	/**
	 * Gets the plane position.
	 *
	 * @return the plane position
	 */
	public PlanePosition getPlanePosition() {
		return planePosition;
	}

	/**
	 * Sets the plane position.
	 *
	 * @param planePosition the new plane position
	 */
	public void setPlanePosition(PlanePosition planePosition) {
		this.planePosition = planePosition;
	}
}
