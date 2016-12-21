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

@Entity
public class Airport {
	
	/**
	 * 
	 * @author Xabier Jauregi
	 * @author Irati Eraña
	 * @author Mikel Arizmendiarrieta 
	 * @version 1.0
	 * @since   2016-12-13
	 * 
	 * General Entity of Airport class.
	 * Is connected with hibernate to generate the table Airport and the needed relationshihps.
	 * Is used to save and work with the information of a airport.
	 * 
	 */

	@Id@GeneratedValue(strategy=GenerationType.SEQUENCE )
	private Integer airportId;
	
	@NotNull
	private String name;
	
	
	@OneToMany
	@JoinColumn(name="AIRPORT_ID", nullable=false)
	Collection<Runway> runwayList =  new ArrayList<Runway>();


	public Integer getAirportId() {
		return airportId;
	}


	public void setAirportId(Integer airportId) {
		this.airportId = airportId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Collection<Runway> getRunwayList() {
		return runwayList;
	}


	public void setRunwayList(Collection<Runway> runwayList) {
		this.runwayList = runwayList;
	}
	

	
	
}
