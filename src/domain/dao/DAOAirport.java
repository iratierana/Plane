package domain.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import configurations.ConnectHibernate;
import domain.model.Airport;
import domain.model.PlanePosition;

/**
 * 
 * @author Xabier Jauregi
 * @author Irati Eraña
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
			ConnectHibernate.after();
			
		}catch (Exception e) {
			session.getTransaction().rollback();
			ConnectHibernate.after();
			return null;
		}
		
		ConnectHibernate.after();
		return a;
	}
}
