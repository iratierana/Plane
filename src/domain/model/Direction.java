package domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


/**
 * The Class Direction.
 */
@Entity
public class Direction {
	
	/**
	 * The direction id.
	 *
	 * @author Xabier Jauregi
	 * @author Irati Erana
	 * @author Mikel Arizmendiarrieta
	 * @version 1.0
	 * @since   2016-12-13
	 * 
	 * General Entity of Direction class.
	 * Is connected with hibernate to generate the table Direction and the needed relationshihps.
	 * Is used to save and work with the direction os all the users in the database.
	 */
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE )
	private Integer directionId;
	
	/** The state. */
	@NotNull
	private String state;
	
	/** The city. */
	@NotNull
	private String city;
	
	/** The cod post. */
	@NotNull
	private String codPost;
	
	/** The address. */
	@NotNull
	private String address;

	/**
	 * Gets the direction id.
	 *
	 * @return the direction id
	 */
	public Integer getDirectionId() {
		return directionId;
	}
	
	
	

	/**
	 * Sets the direction id.
	 *
	 * @param directionId the new direction id
	 */
	public void setDirectionId(Integer directionId) {
		this.directionId = directionId;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the cod post.
	 *
	 * @return the cod post
	 */
	public String getCodPost() {
		return codPost;
	}

	/**
	 * Sets the cod post.
	 *
	 * @param codPost the new cod post
	 */
	public void setCodPost(String codPost) {
		this.codPost = codPost;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
}
