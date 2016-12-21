package domain.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Direction {
	
	/**
	 * 
	 * @author Xabier Jauregi
	 * @author Irati Eraña
	 * @author Mikel Arizmendiarrieta 
	 * @version 1.0
	 * @since   2016-12-13
	 * 
	 * General Entity of Direction class.
	 * Is connected with hibernate to generate the table Direction and the needed relationshihps.
	 * Is used to save and work with the direction os all the users in the database.
	 * 
	 */
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE )
	private Integer directionId;
	
	@NotNull
	private String state;
	
	@NotNull
	private String city;
	
	@NotNull
	private String codPost;
	
	@NotNull
	private String address;

	public Integer getDirectionId() {
		return directionId;
	}
	
	
	

	public void setDirectionId(Integer directionId) {
		this.directionId = directionId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCodPost() {
		return codPost;
	}

	public void setCodPost(String codPost) {
		this.codPost = codPost;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	


	

}
