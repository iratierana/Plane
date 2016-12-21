package domain.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class PlanePosition {
	
	/**
	 * 
	 * @author Xabier Jauregi
	 * @author Irati Eraña
	 * @author Mikel Arizmendiarrieta 
	 * @version 1.0
	 * @since   2016-12-13
	 * 
	 * General Entity of Plane Position class.
	 * Is connected with hibernate to generate the table Plane Position and the needed relationshihps.
	 * Used to know the position of the plane in the airport.
	 * 
	 */
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE )
	private Integer planePositionId;
	
	@NotNull
	private String description;
	
	
	public Integer getPlanePositionId() {
		return planePositionId;
	}

	public void setPlanePositionId(Integer planePositionId) {
		this.planePositionId = planePositionId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	
	
	
	
}
