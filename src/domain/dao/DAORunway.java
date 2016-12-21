package domain.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import configurations.ConnectHibernate;
import domain.model.Runway;


/**
 * 
 * @author Xabier Jauregi
 * @author Irati Eraña
 * @author Mikel Arizmendiarrieta 
 * @version 1.0
 * @since   2016-12-13
 * 
 * Class where are all the needed functions related with Runway, in order to work with the database
 *  
 */
public class DAORunway {
	
	private static Session session;
	
	
	/**
	 * 
	 * This function insert a runway in the database
	 * 
	 * @param r the runway to insert in the database
	 * @return true if the insert is correct
	 * @return false if and error occurs during the insert
	 */
	public static boolean insertRunway(Runway r){
		try {
			
			ConnectHibernate.before();
			session = ConnectHibernate.getSession();
			session.getTransaction().begin();
			session.save(r); //erlazinuak itxen dianian save kendu eta persist ipinibiada eta eralazinuan cascade cascade type.persist
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
	 * This function delete a runway from the database
	 * 
	 * @param r the runway to delete from the database
	 * @return true if the delete is correct
	 * @return false if and error occurs during the delete
	 */
	public static boolean deleteRunway(Runway r){
		try{
			
			ConnectHibernate.before();
			session = ConnectHibernate.getSession();
			session.delete(r);
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
	 * This function load all the runways of the database
	 * 
	 * @return the list of runways if the load is correct
	 * @return null if an error occurs during the load
	 */
	public static List<Runway> loadAllRunways() {
		List<Runway> runwaytList = null;
		try {
			ConnectHibernate.before();
			session = ConnectHibernate.getSession();
			@SuppressWarnings("unchecked")
			TypedQuery<Runway> query = session.createQuery("from Runway");
			runwaytList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnectHibernate.after();

		
		return runwaytList;
	}

}
