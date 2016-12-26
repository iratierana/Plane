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

@Entity
public class Airplane {
	
	/**
	 * 
	 * @author Xabier Jauregi
	 * @author Irati Eraña
	 * @author Mikel Arizmendiarrieta 
	 * @version 1.0
	 * @since   2016-12-13
	 * 
	 * General Entity of Airplane class.
	 * Is connected with hibernate to generate the table Airplane and the needed relationshihps.
	 * Is used to save and work with the information of a airplane.
	 * 
	 */
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE )
	private Integer airplaneId;
	
	@NotNull
	private String name;
	
	@NotNull
	private String serialNumb;
		
	@NotNull
	private Integer lineNumber;
	
	@NotNull
	private String currentRegistration;
	
	@NotNull
	private String operatorOwner;
	
	@NotNull
	private Date deliberyDate;
	
	@NotNull
	private String engineModel;
	
	@NotNull
	private Boolean status;
	
	@NotNull
	private Integer numberOfFlights;
	
	@NotNull
	private Integer hoursOfFlight;
	
	
//	@OneToMany
//	@JoinColumn(name="AIRPLANE_ID", nullable=false)
//	Collection<Flight> flightList = new ArrayList<Flight>();
	
	@OneToOne(cascade=CascadeType.ALL)
	Airport airport;
	
	@OneToOne(cascade=CascadeType.ALL)
	Airline airline;
	
	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="ControlAirplane")
	Collection<AirportController> cotrollerList= new ArrayList<AirportController>();
	
	@OneToOne(cascade=CascadeType.ALL)
	PlanePosition planePosition;

	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="AIRPLANE_ID", nullable=false)
	Collection<AirplanePhoto> photoList=new ArrayList<AirplanePhoto>();
	
	
	
	
	public Collection<AirplanePhoto> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(Collection<AirplanePhoto> photoList) {
		this.photoList = photoList;
	}

	public Integer getAirplaneId() {
		return airplaneId;
	}

	public void setAirplaneId(Integer airplaneId) {
		this.airplaneId = airplaneId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSerialNumb() {
		return serialNumb;
	}

	public void setSerialNumb(String serialNumb) {
		this.serialNumb = serialNumb;
	}

	
	public Integer getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getCurrentRegistration() {
		return currentRegistration;
	}

	public void setCurrentRegistration(String currentRegistration) {
		this.currentRegistration = currentRegistration;
	}

	public String getOperatorOwner() {
		return operatorOwner;
	}

	public void setOperatorOwner(String operatorOwner) {
		this.operatorOwner = operatorOwner;
	}

	public Date getDeliberyDate() {
		return deliberyDate;
	}

	public void setDeliberyDate(Date deliberyDate) {
		this.deliberyDate = deliberyDate;
	}

	public String getEngineModel() {
		return engineModel;
	}

	public void setEngineModel(String engineModel) {
		this.engineModel = engineModel;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getNumberOfFlights() {
		return numberOfFlights;
	}

	public void setNumberOfFlights(Integer numberOfFlights) {
		this.numberOfFlights = numberOfFlights;
	}

	public Integer getHoursOfFlight() {
		return hoursOfFlight;
	}

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

	public Airport getAirport() {
		return airport;
	}

	public void setAirport(Airport airport) {
		this.airport = airport;
	}

	public Collection<AirportController> getCotrollerList() {
		return cotrollerList;
	}

	public void setCotrollerList(Collection<AirportController> cotrollerList) {
		this.cotrollerList = cotrollerList;
	}

	public PlanePosition getPlanePosition() {
		return planePosition;
	}

	public void setPlanePosition(PlanePosition planePosition) {
		this.planePosition = planePosition;
	}


	
	
	
}
