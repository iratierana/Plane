package domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * The Class AirportController.
 */
@Entity
public class AirportController {
	
	/**
	 * The airport controller id.
	 *
	 * @author Xabier Jauregi
	 * @author Irati Eraña
	 * @author Mikel Arizmendiarrieta
	 * @version 1.0
	 * @since   2016-12-13
	 * 
	 * General Entity of Airport Controller class.
	 * Is connected with hibernate to generate the table Airport Controller and the needed relationshihps.
	 * Is used to save and work with the information of a the airport controller user.
	 */
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE )
	private Integer airportControllerId;
	
	/** The first name. */
	@NotNull
	private String firstName;
	
	/** The last name 1. */
	@NotNull
	private String lastName1;
	
	/** The last name 2. */
	@NotNull
	private String lastName2;
	
	/** The dni passport. */
	@NotNull
	private String dni_passport;
	
	/** The home tlf. */
	@NotNull
	private String homeTlf;
	
	/** The movile tlf. */
	@NotNull
	private String movileTlf;
	
	/** The email. */
	@NotNull
	private String email;
	
	/** The birth date. */
	@NotNull
	private Date birthDate;
	
	/** The username. */
	@NotNull
	private String username;
	
	/** The password. */
	@NotNull
	private String password;
	
	
	/** The airplane list. */
	@ManyToMany(mappedBy="cotrollerList")
	Collection<Airplane> airplaneList = new ArrayList<Airplane>();
	
	/** The airport. */
	@OneToOne
	Airport airport;
	
	/** The flight list. */
	@ManyToMany(mappedBy="cortrollerList")
	Collection<Flight> flightList  = new ArrayList<Flight>();
	
	/** The direction. */
	@OneToOne
	Direction direction;

	/**
	 * Gets the airport controller id.
	 *
	 * @return the airport controller id
	 */
	public Integer getAirportControllerId() {
		return airportControllerId;
	}

	/**
	 * Sets the airport controller id.
	 *
	 * @param airportControllerId the new airport controller id
	 */
	public void setAirportControllerId(Integer airportControllerId) {
		this.airportControllerId = airportControllerId;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name 1.
	 *
	 * @return the last name 1
	 */
	public String getLastName1() {
		return lastName1;
	}

	/**
	 * Sets the last name 1.
	 *
	 * @param lastName1 the new last name 1
	 */
	public void setLastName1(String lastName1) {
		this.lastName1 = lastName1;
	}

	/**
	 * Gets the last name 2.
	 *
	 * @return the last name 2
	 */
	public String getLastName2() {
		return lastName2;
	}

	/**
	 * Sets the last name 2.
	 *
	 * @param lastName2 the new last name 2
	 */
	public void setLastName2(String lastName2) {
		this.lastName2 = lastName2;
	}

	/**
	 * Gets the dni passport.
	 *
	 * @return the dni passport
	 */
	public String getDni_passport() {
		return dni_passport;
	}

	/**
	 * Sets the dni passport.
	 *
	 * @param dni_passport the new dni passport
	 */
	public void setDni_passport(String dni_passport) {
		this.dni_passport = dni_passport;
	}

	/**
	 * Gets the home tlf.
	 *
	 * @return the home tlf
	 */
	public String getHomeTlf() {
		return homeTlf;
	}

	/**
	 * Sets the home tlf.
	 *
	 * @param homeTlf the new home tlf
	 */
	public void setHomeTlf(String homeTlf) {
		this.homeTlf = homeTlf;
	}

	/**
	 * Gets the movile tlf.
	 *
	 * @return the movile tlf
	 */
	public String getMovileTlf() {
		return movileTlf;
	}

	/**
	 * Sets the movile tlf.
	 *
	 * @param movileTlf the new movile tlf
	 */
	public void setMovileTlf(String movileTlf) {
		this.movileTlf = movileTlf;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the birth date.
	 *
	 * @return the birth date
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * Sets the birth date.
	 *
	 * @param birthDate the new birth date
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the airplane list.
	 *
	 * @return the airplane list
	 */
	public Collection<Airplane> getAirplaneList() {
		return airplaneList;
	}

	/**
	 * Sets the airplane list.
	 *
	 * @param airplaneList the new airplane list
	 */
	public void setAirplaneList(Collection<Airplane> airplaneList) {
		this.airplaneList = airplaneList;
	}

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
	 * Gets the flight list.
	 *
	 * @return the flight list
	 */
	public Collection<Flight> getFlightList() {
		return flightList;
	}

	/**
	 * Sets the flight list.
	 *
	 * @param flightList the new flight list
	 */
	public void setFlightList(Collection<Flight> flightList) {
		this.flightList = flightList;
	}

	/**
	 * Gets the direction.
	 *
	 * @return the direction
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * Sets the direction.
	 *
	 * @param direction the new direction
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	
	
	
}
