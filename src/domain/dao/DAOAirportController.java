package domain.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import configurations.ConnectHibernate;
import domain.model.AirportController;


/**
 * 
 * @author Xabier Jauregi
 * @author Irati Eraña
 * @author Mikel Arizmendiarrieta 
 * @version 1.0
 * @since   2016-12-13
 * 
 * Class where are all the needed functions related with AirportController, in order to work with the database
 *  
 */
public class DAOAirportController {
	
	private static Session session;
	
	
	/**
	 * 
	 * This function load all the airport controllers of the database
	 * 
	 * @return the list of airport controllers if the load is correct
	 * @return null if an error occurs during the load
	 */
	public static List<AirportController> loadAllAirportControllers() {
		List<AirportController> cotrollerList = null;
		try {
			ConnectHibernate.before();
			session = ConnectHibernate.getSession();
			@SuppressWarnings("unchecked")
			TypedQuery<AirportController> query = session.createQuery("from AirportController");
			cotrollerList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectHibernate.after();
		}

		
		return cotrollerList;
	}
	
}
