package domain.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import configurations.ConnectHibernate;
import domain.model.Airport;
import domain.model.Flight;

/**
 * 
 * @author Xabier Jauregi
 * @author Irati Erana
 * @author Mikel Arizmendiarrieta 
 * @version 1.0
 * @since   2016-12-13
 * 
 * Class where are all the needed functions related with Airport, in order to work with the database
 *  
 */
public class DAOAirport {

	private static Session session;
	
	/**
	 * Loads an specific airport
	 * @param airportName The name of the airport to load
	 * @return Selected airport
	 * @return null if the airport does not exists
	 */
	@SuppressWarnings("unchecked")
	public static Airport loadAirport(String airportName){
		List<Airport> airportList = null;
		Airport a = new Airport();
		a = null;
		try{
			ConnectHibernate.before();
			session = ConnectHibernate.getSession();
			
			TypedQuery<Airport> query = session.createQuery("from Airport where name='"+airportName+"'");
			airportList = query.getResultList();
			if(!airportList.isEmpty()){
				a=airportList.get(0);
			}
			
		}catch (Exception e) {
			session.getTransaction().rollback();
			return null;
		}finally {
			ConnectHibernate.after();
		}
		
		return a;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Airport> loadAllAirports(){
		
		List<Airport> airportList = null;
		try {
			ConnectHibernate.before();
			session = ConnectHibernate.getSession();
			TypedQuery<Airport> query = session.createQuery("from Airport");
			airportList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectHibernate.after();
		}

		
		return airportList;
		
	}
	
	public static boolean insertFlightInDatabase(Flight flight){
		
		try {
					
					ConnectHibernate.before();
					session = ConnectHibernate.getSession();
					session.getTransaction().begin();
					session.save(flight); 
					session.getTransaction().commit();
					ConnectHibernate.after();
					return true;
					
				} catch (Exception e) {
					e.printStackTrace();
					session.getTransaction().rollback();
					ConnectHibernate.after();
					return false;		
				}finally {
					ConnectHibernate.after();
				}
				
			}
}
