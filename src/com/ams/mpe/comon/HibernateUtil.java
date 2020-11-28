package com.ams.mpe.comon;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil implements PlugIn {
	
	private static SessionFactory sessionFactory;
	
/*	static {
		try {
			sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();			
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}*/
	
	public static Session getSession() {
		// entity intercepter
		return getSessionFactory().openSession(new ModelInterceptor());
	}
	
	public static SessionFactory getSessionFactory() {
		// Alternatively, you could look up in JNDI here
		return sessionFactory;
	}
	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}

	@Override
	public void destroy() {
		getSessionFactory().close();
	}

	@Override
	public void init(ActionServlet arg0, ModuleConfig arg1)
			throws ServletException {
		try {
			//sessionFactory = new AnnotationConfiguration().configure("jpa.cfg.xml").buildSessionFactory();			
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
		
	}

}
