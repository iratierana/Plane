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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Passanger {
	
	/**
	 * 
	 * @author Xabier Jauregi
	 * @author Irati Eraña
	 * @author Mikel Arizmendiarrieta 
	 * @version 1.0
	 * @since   2016-12-13
	 * 
	 * General Entity of Passanger class.
	 * Is connected with hibernate to generate the table Passanger and the needed relationshihps.
	 * Is needed in order to save and work with the data of a passanger
	 * 
	 */

	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE )
	private Integer passangerId;	
	 	
	@NotNull
 	private String firstName;
 	
	@NotNull
 	private String lastName1;
 	
	@NotNull
 	private String lastName2;
 	
	@NotNull
 	private String dni_passport;
 	
	@NotNull
 	private String homeTlf;
 	
	@NotNull
 	private String movileTlf;
 	
	@NotNull
	private String email;
	
	@NotNull @Temporal(TemporalType.DATE)
	private Date birthDate;
	
	@NotNull
	private String username;
	
	@NotNull
	private String password;

	
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name="booking", joinColumns={
			@JoinColumn(name="passengerId", nullable=false, updatable=false)},
			inverseJoinColumns={@JoinColumn(name="flightId", nullable=false, updatable=false)}) 
	Collection<Flight> flightList = new ArrayList<Flight>();
	
	@OneToOne(cascade = CascadeType.ALL)
	Direction direction;

	
	
	public Integer getPassangerId() {
		return passangerId;
	}

	public void setPassangerId(Integer passangerId) {
		this.passangerId = passangerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName1() {
		return lastName1;
	}

	public void setLastName1(String lastName1) {
		this.lastName1 = lastName1;
	}

	public String getLastName2() {
		return lastName2;
	}

	public void setLastName2(String lastName2) {
		this.lastName2 = lastName2;
	}

	public String getDni_passport() {
		return dni_passport;
	}

	public void setDni_passport(String dni_passport) {
		this.dni_passport = dni_passport;
	}

	public String getHomeTlf() {
		return homeTlf;
	}

	public void setHomeTlf(String homeTlf) {
		this.homeTlf = homeTlf;
	}

	public String getMovileTlf() {
		return movileTlf;
	}

	public void setMovileTlf(String movileTlf) {
		this.movileTlf = movileTlf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Flight> getFlightList() {
		return flightList;
	}

	public void setFlightList(Collection<Flight> flightList) {
		this.flightList = flightList;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
}
