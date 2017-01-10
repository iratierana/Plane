package domain.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * The Class AirplanePhoto.
 */
@Entity
public class AirplanePhoto {
	
	/**
	 * The photo id.
	 *
	 * @author Xabier Jauregi
	 * @author Irati Erana
	 * @author Mikel Arizmendiarrieta
	 * @version 1.0
	 * @since   2016-12-13
	 * 
	 * General Entity of photo class.
	 * Is connected with hibernate to generate the table airplanephoto and the needed relationshihps.
	 * Is used to save the photos of the airplanes.
	 */
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE )
	private Integer photoId;
	
	/** The photo. */
	@NotNull
	private byte[] photo;
	
	/** The photo date. */
	@NotNull
	private Date photoDate;
	
	/** The photographer. */
	@NotNull
	private String photographer;

	/**
	 * Gets the photo id.
	 *
	 * @return the photo id
	 */
	public Integer getPhotoId() {
		return photoId;
	}

	/**
	 * Sets the photo id.
	 *
	 * @param photoId the new photo id
	 */
	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
	}

	/**
	 * Gets the photo.
	 *
	 * @return the photo
	 */
	public byte[] getPhoto() {
		return photo;
	}

	/**
	 * Sets the photo.
	 *
	 * @param photo the new photo
	 */
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	/**
	 * Gets the photo date.
	 *
	 * @return the photo date
	 */
	public Date getPhotoDate() {
		return photoDate;
	}

	/**
	 * Sets the photo date.
	 *
	 * @param photoDate the new photo date
	 */
	public void setPhotoDate(Date photoDate) {
		this.photoDate = photoDate;
	}

	/**
	 * Gets the photographer.
	 *
	 * @return the photographer
	 */
	public String getPhotographer() {
		return photographer;
	}

	/**
	 * Sets the photographer.
	 *
	 * @param photographer the new photographer
	 */
	public void setPhotographer(String photographer) {
		this.photographer = photographer;
	}	
	
}
