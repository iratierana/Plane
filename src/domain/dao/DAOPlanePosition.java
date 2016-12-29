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
	 * Loads the plane position specified by the id
	 * @return The  plane position
	 */
	@SuppressWarnings("unchecked")
	public static PlanePosition loadPlanePosition(int id){
		List<PlanePosition> positionList = null;
		PlanePosition p = new PlanePosition();
		p = null;
		try{
			ConnectHibernate.before();
			session = ConnectHibernate.getSession();
			
			TypedQuery<PlanePosition> query = session.createQuery("from PlanePosition where planePositionId="+id);
			positionList = query.getResultList();
			if(!positionList.isEmpty()){
				p=positionList.get(0);
			}
			
		}catch (Exception e) {
			session.getTransaction().rollback();
			ConnectHibernate.after();
			return null;
		}finally {
			ConnectHibernate.after();
		}
		
		return p;
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
		}finally {
			ConnectHibernate.after();
		}
		

		
		return positionList;
	}

}
