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
	private Integer position;
	
	@NotNull
	private Date hour;

	public Integer getPlanePositionId() {
		return planePositionId;
	}

	public void setPlanePositionId(Integer planePositionId) {
		this.planePositionId = planePositionId;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Date getHour() {
		return hour;
	}

	public void setHour(Date hour) {
		this.hour = hour;
	}
	
	
	
	
	
}
