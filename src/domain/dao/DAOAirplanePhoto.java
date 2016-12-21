package domain.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import configurations.ConnectHibernate;
import domain.model.AirplanePhoto;
/**
 * 
 * @author Xabier Jauregi
 * @author Irati Era�a
 * @author Mikel Arizmendiarrieta 
 * @version 1.0
 * @since   2016-12-13
 * 
 * Class where are all the needed functions related with AirplanePhoto, in order to work with the database
 *  
 */
public class DAOAirplanePhoto {


	private static Session session;

	
	/**
	 * 
	 * This function load all the photos of the database
	 * 
	 * @return the list of photos if the load is correct
	 * @return null if an error occurs during the load
	 */
	public static List<AirplanePhoto> loadAllAirplanePhotos() {
		List<AirplanePhoto> photoList = null;
		try {
			ConnectHibernate.before();
			session = ConnectHibernate.getSession();
			@SuppressWarnings("unchecked")
			TypedQuery<AirplanePhoto> query = session.createQuery("from AirplanePhoto");
			photoList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnectHibernate.after();

		
		return photoList;
	}

}
