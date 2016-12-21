package configurations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConnectHibernate {
	
	private static SessionFactory sessionFactory;
	private static Session session;
	private final static String CONFIG_FILE = "configurations/hibernate.cfg.xml";

	
	public static void before() {
		sessionFactory = new Configuration().configure(CONFIG_FILE).buildSessionFactory();
		session = sessionFactory.openSession();
	}
	
	public static void after() {
		session.close();
		sessionFactory.close();
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void setSessionFactory(SessionFactory sessionFactory) {
		ConnectHibernate.sessionFactory = sessionFactory;
	}
	
	public static Session getSession(){
		return session;
	}
	
	public static void setSession(Session session) {
		ConnectHibernate.session = session;
	}
}
