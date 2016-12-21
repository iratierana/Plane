package domain.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import configurations.ConnectHibernate;
import domain.model.Flight;

/**
 * 
 * @author Xabier Jauregi
 * @author Irati Eraña
 * @author Mikel Arizmendiarrieta 
 * @version 1.0
 * @since   2016-12-13
 * 
 * Class where are all the needed functions related with flight, in order to work with the database
 *  
 */
public class DAOFlight {
	
private static Session session;
	
	/**
	 * 
	 * This function insert aflight in the database
	 * 
	 * @param flight the flight to insert in the database
	 * @return true if the insert is correct
	 * @return false if and error occurs during the insert
	 */
	public static boolean insertFlight(Flight flight){
		try {
			
			ConnectHibernate.before();
			session = ConnectHibernate.getSession();
			session.getTransaction().begin();
			session.persist(flight);
			session.getTransaction().commit();
			ConnectHibernate.after();
			return true;
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			ConnectHibernate.after();
			return false;		
		}
	}

	/**
	 * 
	 * This function delete a flight from the database
	 * 
	 * @param flight the flight to delete from the database
	 * @return true if the delete is correct
	 * @return false if and error occurs during the delete
	 */
	public static boolean deleteFlight(Flight flight){
		try{
			
			ConnectHibernate.before();
			session = ConnectHibernate.getSession();
			session.delete(flight);
			ConnectHibernate.after();
			return true;
			
		}catch(Exception e){
			session.getTransaction().rollback();
			ConnectHibernate.after();
			return false;
		}
	}
	
	/**
	 * 
	 * This function load all the flights of the database
	 * 
	 * @return the list of flights if the load is correct
	 * @return null if an error occurs during the load
	 */
	public static List<Flight> loadAllFlights() {
		List<Flight> flightList = null;
		try {
			ConnectHibernate.before();
			session = ConnectHibernate.getSession();
			@SuppressWarnings("unchecked")
			TypedQuery<Flight> query = session.createQuery("from Flight");
			flightList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnectHibernate.after();

		
		return flightList;
	}

}
