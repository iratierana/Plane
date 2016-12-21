package domain.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import configurations.ConnectHibernate;
import domain.model.Airplane;


/**
 * 
 * @author Xabier Jauregi
 * @author Irati Eraña
 * @author Mikel Arizmendiarrieta 
 * @version 1.0
 * @since   2016-12-13
 * 
 * Class where are all the needed functions related with Airplane, in order to work with the database
 *  
 */
public class DAOAirplane {
	
private static Session session;
	
/**
 * 
 * This function insert aan airplane in the database
 * 
 * @param airplane the airplane to insert in the database
 * @return true if the insert is correct
 * @return false if and error occurs during the insert
 */
	public static boolean insertAirplane(Airplane airplane){
		try {
			
			ConnectHibernate.before();
			session = ConnectHibernate.getSession();
			session.getTransaction().begin();
			session.save(airplane); //erlazinuak itxen dianian save kendu eta persist ipinibiada eta eralazinuan cascade cascade type.persist
			session.getTransaction().commit();
			ConnectHibernate.after();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			ConnectHibernate.after();
			return false;		
		}
	}

	/**
	 * 
	 * This function delete an airplane from the database
	 * 
	 * @param airplane the airplane to delete from the database
	 * @return true if the delete is correct
	 * @return false if and error occurs during the delete
	 */
	public static boolean deleteAirplane(Airplane airplane){
		try{
			
			ConnectHibernate.before();
			session = ConnectHibernate.getSession();
			session.delete(airplane);
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
	 * This function load all the Airplanes of the database
	 * 
	 * @return the list if the load is correct
	 * @return null if and error occurs during the load
	 */
	public static List<Airplane> loadAllAirplanes() {
		List<Airplane> airplaneList = null;
		try {
			ConnectHibernate.before();
			session = ConnectHibernate.getSession();
			@SuppressWarnings("unchecked")
			TypedQuery<Airplane> query = session.createQuery("from Airplane");
			airplaneList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnectHibernate.after();

		
		return airplaneList;
	}

}
