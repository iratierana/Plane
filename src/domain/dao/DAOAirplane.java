package domain.dao;

import javax.persistence.Query;

import org.hibernate.Session;

import configurations.ConnectHibernate;
import domain.model.Airplane;
import domain.model.PlanePosition;


/**
 * 
 * @author Xabier Jauregi
 * @author Irati Era�a
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

	
	public static boolean updateAirplanePosition(int planeId, PlanePosition newPlanePosition){
		try{
			ConnectHibernate.before();			
			session = ConnectHibernate.getSession();
			session.getTransaction().begin();
			String hql="UPDATE Airplane"
					+ " SET planePosition = "+newPlanePosition
					+ " WHERE airplaneId = "+planeId;
			Query query = session.createQuery(hql);
			query.executeUpdate();
			session.getTransaction().commit();	
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
