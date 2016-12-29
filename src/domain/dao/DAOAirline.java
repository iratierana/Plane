package domain.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import configurations.ConnectHibernate;
import domain.model.Airline;

/**
 * 
 * @author Xabier Jauregi
 * @author Irati Eraña
 * @author Mikel Arizmendiarrieta 
 * @version 1.0
 * @since   2016-12-13
 * 
 * Class where are all the needed functions related with Airline, in order to work with the database
 *  
 */

public class DAOAirline {
	
	private static Session session;
			
	/**
	 * 
	 * This function loads all the airlines of the database
	 * 
	 * @return a list of all the airlines of the database
	 * @return null if an error occurs during the load
	 */
	@SuppressWarnings("unchecked")
	public static List<Airline> loadAllAirlines() {
		List<Airline> airlineList = null;
		try {
			ConnectHibernate.before();
			session = ConnectHibernate.getSession();
			TypedQuery<Airline> query = session.createQuery("from Airline");
			airlineList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectHibernate.after();
		}

		
		return airlineList;
	}
	

}
