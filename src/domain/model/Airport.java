package domain.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * The Class Airport.
 */
@Entity
public class Airport {
	
	/**
	 * The airport id.
	 *
	 * @author Xabier Jauregi
	 * @author Irati Erana
	 * @author Mikel Arizmendiarrieta
	 * @version 1.0
	 * @since   2016-12-13
	 * 
	 * General Entity of Airport class.
	 * Is connected with hibernate to generate the table Airport and the needed relationshihps.
	 * Is used to save and work with the information of a airport.
	 */

	@Id@GeneratedValue(strategy=GenerationType.SEQUENCE )
	private Integer airportId;
	
	/** The name. */
	@NotNull
	private String name;
	
	
	/** The runway list. */
	@OneToMany
	@JoinColumn(name="AIRPORT_ID", nullable=false)
	Collection<Runway> runwayList =  new ArrayList<Runway>();


	/**
	 * Gets the airport id.
	 *
	 * @return the airport id
	 */
	public Integer getAirportId() {
		return airportId;
	}


	/**
	 * Sets the airport id.
	 *
	 * @param airportId the new airport id
	 */
	public void setAirportId(Integer airportId) {
		this.airportId = airportId;
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
	 * Gets the runway list.
	 *
	 * @return the runway list
	 */
	public Collection<Runway> getRunwayList() {
		return runwayList;
	}


	/**
	 * Sets the runway list.
	 *
	 * @param runwayList the new runway list
	 */
	public void setRunwayList(Collection<Runway> runwayList) {
		this.runwayList = runwayList;
	}
	

	
	
}
