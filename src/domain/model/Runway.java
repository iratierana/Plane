package domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * The Class Runway.
 */
@Entity
public class Runway {
	
	/**
	 * The runway id.
	 *
	 * @author Xabier Jauregi
	 * @author Irati Erana
	 * @author Mikel Arizmendiarrieta
	 * @version 1.0
	 * @since   2016-12-13
	 * 
	 * General Entity of Runway class.
	 * Is connected with hibernate to generate the table Runway and the needed relationshihps.
	 */
	
	@Id@GeneratedValue(strategy=GenerationType.SEQUENCE )
	private Integer runwayId;
	
	/** The tipo. */
	@NotNull
	private String tipo;
	
	/** The state. */
	@NotNull
	private boolean state;
	
	

	/**
	 * Gets the runway id.
	 *
	 * @return the runway id
	 */
	public Integer getRunwayId() {
		return runwayId;
	}

	/**
	 * Sets the runway id.
	 *
	 * @param runwayId the new runway id
	 */
	public void setRunwayId(Integer runwayId) {
		this.runwayId = runwayId;
	}

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo the new tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Checks if is state.
	 *
	 * @return true, if is state
	 */
	public boolean isState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(boolean state) {
		this.state = state;
	}

}
