package domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Runway {
	
	/**
	 * 
	 * @author Xabier Jauregi
	 * @author Irati Eraña
	 * @author Mikel Arizmendiarrieta 
	 * @version 1.0
	 * @since   2016-12-13
	 * 
	 * General Entity of Runway class.
	 * Is connected with hibernate to generate the table Runway and the needed relationshihps.
	 * 
	 */
	
	@Id@GeneratedValue(strategy=GenerationType.SEQUENCE )
	private Integer runwayId;
	
	@NotNull
	private String tipo;
	
	@NotNull
	private boolean state;
	
	

	public Integer getRunwayId() {
		return runwayId;
	}

	public void setRunwayId(Integer runwayId) {
		this.runwayId = runwayId;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

}
