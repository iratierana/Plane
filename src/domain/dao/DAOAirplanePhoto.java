package domain.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import configurations.ConnectHibernate;
import domain.model.AirplanePhoto;

public class DAOAirplanePhoto {

	/**
	 * 
	 * @author Xabier Jauregi
	 * @author Irati Eraña
	 * @author Mikel Arizmendiarrieta 
	 * @version 1.0
	 * @since   2016-12-13
	 * 
	 * Class where are all the needed functions related with AirplanePhoto, in order to work with the database
	 *  
	 */
private static Session session;
	
/**
 * 
 * This function insert a photo in the database
 * 
 * @param photo the photo to insert in the database
 * @return true if the insert is correct
 * @return false if and error occurs during the insert
 */
	public static boolean insertAirplanePhoto(AirplanePhoto photo){
		try {
			
			ConnectHibernate.before();
			session = ConnectHibernate.getSession();
			session.getTransaction().begin();
			session.save(photo); //erlazinuak itxen dianian save kendu eta persist ipinibiada eta eralazinuan cascade cascade type.persist
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
	 * This function delete a photo from the database
	 * 
	 * @param photo the photo to delete from the database
	 * @return true if the delete is correct
	 * @return false if and error occurs during the delete
	 */
	public static boolean deleteAirplanePhoto(AirplanePhoto photo){
		try{
			
			ConnectHibernate.before();
			session = ConnectHibernate.getSession();
			session.delete(photo);
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
	 * This function load all the photos of the database
	 * 
	 * @return the list of photos if the load is correct
	 * @return null if an error occurs during the load
	 */
	public static List<AirplanePhoto> loadAllAirplanePhotos() {
		List<AirplanePhoto> photoList = null;
		try {
			ConnectHibernate.before();
			session = ConnectHibernate.getSession();
			@SuppressWarnings("unchecked")
			TypedQuery<AirplanePhoto> query = session.createQuery("from AirplanePhoto");
			photoList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnectHibernate.after();

		
		return photoList;
	}

}
