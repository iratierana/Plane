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
	 * This function insert a plane position in the database
	 * 
	 * @param planePosition the plane position to insert in the database
	 * @return true if the insert is correct
	 * @return false if and error occurs during the insert
	 */
	public static boolean insertPlanePosition(PlanePosition planePosition){
		try {
			
			ConnectHibernate.before();
			session = ConnectHibernate.getSession();
			session.getTransaction().begin();
			session.save(planePosition); //erlazinuak itxen dianian save kendu eta persist ipinibiada eta eralazinuan cascade cascade type.persist
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
	 * This function delete a plane position from the database
	 * 
	 * @param planePosition the plane position to delete from the database
	 * @return true if the delete is correct
	 * @return false if and error occurs during the delete
	 */
	public static boolean deletePlanePosition(PlanePosition planePosition){
		try{
			
			ConnectHibernate.before();
			session = ConnectHibernate.getSession();
			session.delete(planePosition);
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
