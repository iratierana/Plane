package domain.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import configurations.ConnectHibernate;
import domain.model.Airplane;
import domain.model.PlanePosition;


// TODO: Auto-generated Javadoc
/**
 * The Class DAOAirplane.
 *
 * @author Xabier Jauregi
 * @author Irati Erana
 * @author Mikel Arizmendiarrieta
 * @version 1.0
 * @since   2016-12-13
 * 
 * Class where are all the needed functions related with Airplane, in order to work with the database
 */
public class DAOAirplane {
	
	/** The session. */
	private static Session session;
	
	/**
	 * This function insert aan airplane in the database.
	 *
	 * @param airplane the airplane to insert in the database
	 * @return true if the insert is correct
	 * false if and error occurs during the insert
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
		}finally {
			ConnectHibernate.after();
		}
	}

	/**
	 * Load airplane.
	 *
	 * @param id the id
	 * @return the airplane
	 */
	public static Airplane loadAirplane (int id){
		List<Airplane> airplaneList = null;
		Airplane a = new Airplane();
		a = null;
		try{
			ConnectHibernate.before();
			session = ConnectHibernate.getSession();
			
			@SuppressWarnings("unchecked")
			TypedQuery<Airplane> query = session.createQuery("from Airplane where airplaneId="+id);
			airplaneList = query.getResultList();
			if(!airplaneList.isEmpty()){
				a=airplaneList.get(0);
			}
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			return null;
		}finally {
			ConnectHibernate.after();
		}
		
		return a;
	}
	
	/**
	 * Update airplane position.
	 *
	 * @param planeId the plane id
	 * @param newPlanePositionId the new plane position id
	 * @return true, if successful
	 */
	public static boolean updateAirplanePosition(int planeId, int newPlanePositionId){
		try{
			PlanePosition newPlanePosition = DAOPlanePosition.loadPlanePosition(newPlanePositionId);
			Airplane airplane = DAOAirplane.loadAirplane(planeId);
			airplane.setPlanePosition(newPlanePosition);
			ConnectHibernate.before();			
			session = ConnectHibernate.getSession();
			session.getTransaction().begin();
			session.saveOrUpdate(airplane);
			session.getTransaction().commit();	
			ConnectHibernate.after();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			ConnectHibernate.after();
			return false;
		}finally {
			ConnectHibernate.after();
		}
	}

}
