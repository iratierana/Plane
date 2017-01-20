package domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * The Class PlanePosition.
 */
@Entity
public class PlanePosition {
	
	/**
	 * The plane position id.
	 *
	 * @author Xabier Jauregi
	 * @author Irati Erana
	 * @author Mikel Arizmendiarrieta
	 * @version 1.0
	 * @since   2016-12-13
	 * 
	 * General Entity of Plane Position class.
	 * Is connected with hibernate to generate the table Plane Position and the needed relationshihps.
	 * Used to know the position of the plane in the airport.
	 */
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE )
	private Integer planePositionId;
	
	/** The description. */
	@NotNull
	private String description;
	
	
	/**
	 * Gets the plane position id.
	 *
	 * @return the plane position id
	 */
	public Integer getPlanePositionId() {
		return planePositionId;
	}

	/**
	 * Sets the plane position id.
	 *
	 * @param planePositionId the new plane position id
	 */
	public void setPlanePositionId(Integer planePositionId) {
		this.planePositionId = planePositionId;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
