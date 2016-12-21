package domain.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import configurations.ConnectHibernate;
import domain.model.PlanePosition;

/**
 * 
 * @author Xabier Jauregi
 * @author Irati Eraña
 * @author Mikel Arizmendiarrieta 
 * @version 1.0
 * @since   2016-12-13
 * 
 * Class where are all the needed functions related with PlanePosition, in order to work with the database
 *  
 */
public class DAOPlanePosition {
	
	private static Session session;

	
	
	/**
	 * 
	 * This function load all the plane positions of the database
	 * 
	 * @return the list of plane positions if the load is correct
	 * @return null if an error occurs during the load
	 */
	public static List<PlanePosition> loadAllPlanePositions() {
		List<PlanePosition> positionList = null;
		try {
			ConnectHibernate.before();
			session = ConnectHibernate.getSession();
			@SuppressWarnings("unchecked")
			TypedQuery<PlanePosition> query = session.createQuery("from PlanePosition");
			positionList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnectHibernate.after();

		
		return positionList;
	}

}
