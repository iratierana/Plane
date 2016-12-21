package domain.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import configurations.ConnectHibernate;
import domain.model.Airplane;
import domain.model.AirplanePhoto;
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
public class DAOAirplanePhoto {


	private static Session session;

	
	/**
	 * 
	 * This function load all the photos of the database
	 * 
	 * @return the list of photos if the load is correct
	 * @return null if an error occurs during the load
	 */
	@SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
	public static List<AirplanePhoto> loadAllAirplanePhotosOfOneAirplane(int airplaneId) {
		List<AirplanePhoto> photoList = null;
		String sql="SELECT photo.*"
				+ " FROM airplane air join airplanepoto photo on air.airplaneid=photo.airplane_id"
				+ " WHERE air.airplaneid="+airplaneId;
		try {
			ConnectHibernate.before();
			session = ConnectHibernate.getSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Airplane.class);
			photoList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnectHibernate.after();

		
		return photoList;
	}

}
