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
public class Airline {
	
	/**
	 * 
	 * @author Xabier Jauregi
	 * @author Irati Eraña
	 * @author Mikel Arizmendiarrieta 
	 * @version 1.0
	 * @since   2016-12-13
	 * 
	 * General Entity of Airline class.
	 * Is connected with hibernate to generate the table Airline and the needed relationshihps.
	 * Is used to save and work with the information of a Airline.
	 * 
	 */
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE  )
	private Integer airlineId;
	
	@NotNull
	private String name;
	
	private String description;

	@OneToMany
	@JoinColumn(name="AIRLINE_ID", nullable=false)
	Collection<Airplane> airplaneList = new ArrayList<Airplane>();
	
	
	
	
	
	
	public Integer getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(Integer airlineId) {
		this.airlineId = airlineId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<Airplane> getAirplaneList() {
		return airplaneList;
	}

	public void setAirplaneList(Collection<Airplane> airplaneList) {
		this.airplaneList = airplaneList;
	}


}