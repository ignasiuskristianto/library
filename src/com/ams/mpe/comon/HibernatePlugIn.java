/*
 * Created on Oct 6, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.ams.mpe.comon;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ams.model.dao._RootDAO;



/**
 * Initialize the Hibernate SessionFactory for this project
 * as an application scope object.
 *
 * @author Ted Husted
 * @version $Revision: 1.3 $ $Date: 2003/03/14 21:59:41 $
 */
public class HibernatePlugIn implements PlugIn {
	Log log = LogFactory.getFactory().getInstance(this.getClass());

	/**
	 * A field to store the reference to our SessionFactory.
	 * Can close and dispose if not null.
	 */
	private SessionFactory sf;

	/**
	 * A public identifer for the persistence session,
	 * kept in servlet session ("client") scope
	 * ["HIBERNATE_SESSION"].
	 */
	public static String SESSION = "HIBERNATE_SESSION";

	/**
	 * A public identifer for the session factory,
	 * kept in application ("global") scope
	 * ["HIBERNATE_SESSION_FACTORY"].
	 */
	public static String SESSION_FACTORY = "HIBERNATE_SESSION_FACTORY";

	/**
	 * the path to the xml configuration file.  the path should start with a
	 * '/' character and be relative to the root of the class path.
	 * (DEFAULT:  "/hibernate.cfg.xml")
	 */
	String _configFilePath1 = "/hibernate.cfg.xml";
	/*String _configFilePath2 = "/report.cfg.xml";
	String _configFilePath3 = "/data_file.cfg.xml";*/


	/**
	 * Fetch the SessionFactory from application scope.
	 * @param request The requeste we are servicing
	 * @return The SessionFactory for this application session
	 * @throws HibernateException
	 */
	public static SessionFactory sessionFactory(HttpServletRequest request)	throws HibernateException {
		Object sf = request.getSession().getServletContext().getAttribute(SESSION_FACTORY);
		if (null == sf) {
			throw new HibernateException(SESSION_FACTORY);
		}
		return (SessionFactory) sf;
	}


	/**
	 * Open a new session with the application-scope SessionFactory.
	 * Session is not retained, only returned.
	 *
	 * @param request The requeset we are servicing
	 * @return An open session
	 * @throws HibernateException
	 */
	public static Session open(HttpServletRequest request) throws HibernateException {
		return sessionFactory(request).openSession();
	}

	/**
	 * Open a new Session and cache it in the HttpSession or
	 * fetch the existing Session.
	 *
	 * @param request The requeset we are servicing
	 * @return An open session
	 * @throws net.sf.hibernate.HibernateException if session cannot be instantiated
	 */
	public static Session reconnect(HttpServletRequest request)	throws HibernateException {
		Session s = (Session) request.getSession(true).getAttribute(SESSION);
		if (null != s) {
			s.reconnect(s.connection());
		} else {
			s = open(request);
			request.getSession().setAttribute(SESSION, s);
		}
		return s;
	}

	/**
	 * Expire the Session, to ensure fresh data or to switch approaches.
	 *
	 * @param request The requeset we are servicing
	 * @return An open session
	 * @throws net.sf.hibernate.HibernateException if session cannot be instantiated
	 */
	public static void expire(HttpServletRequest request)	throws HibernateException {
		HttpSession httpSession = request.getSession();
		if (null!=httpSession) {
			Session s = (Session) httpSession.getAttribute(SESSION);
			if (null != s) {
				s.close();
				httpSession.removeAttribute(SESSION);
			}
		}
	}

	/**
	 * The classes with mappings to add to the Configuration are enumerated here.
	 * There should be a "${class}.hbm.xml" mapping file for each class
	 * stored with each compiled class file.
	 * <p>
	 * To complete the Hibernate setup, there must be a valid "hiberate.properties"
	 * file under the "classes" folder (root of the classpath),
	 * which specifies the details of the database hookup.
	 * <p>
	 * The mapping documents and properties file is all that Hibernate requires.
	 * <p>
	 * A JDBC Driver is not included in this distribution and *must* be
	 * available on your server's or container's classpath
	 * (e.g., the Tomcat common/lib directory).
	 *
	 * @return A Configuration object
	 * @throws net.sf.hibernate.MappingException if any the mapping documents can be rendered.
	 */


	public void init(ActionServlet servlet, ModuleConfig config)throws ServletException {
		SessionFactory exists = (SessionFactory) servlet.getServletContext().getAttribute(SESSION_FACTORY);
		if (null != exists) return; // already got one

		try {
			//URL configFileURL = HibernatePlugIn.class.getResource(_configFilePath2);
			//ServletContext servletContext = servlet.getServletContext();
			//Configuration configuration = (new Configuration()).configure(configFileURL);
			//sf = (new Configuration()).configure().buildSessionFactory();
			//_RootDAO.initialize(configFileURL);
			//log.info("T : "+configFileURL.getPath()+" "+configFileURL.getFile()+" "+configFileURL.toURI().getPath());
			//_RootDAO.initialize("/hibernate.cfg.xml");
			//_RootDAO.initialize();
			_RootDAO.startup(_configFilePath1);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			servlet.log(e.toString());
			throw new ServletException(e);
		}

		//servlet.getServletContext().setAttribute(SESSION_FACTORY, sf);
	}
	
    /**
     * Setter for property configFilePath.
     * @param configFilePath New value of property configFilePath.
     */
    public void setConfigFilePath1(String configFilePath1) {
        if ((configFilePath1 == null) || (configFilePath1.trim().length() == 0)) {
            throw new IllegalArgumentException(
                    "configFilePath cannot be blank or null.");
        }
        
        _configFilePath1 = configFilePath1;
    }
    
    /**
     * Setter for property configFilePath.
     * @param configFilePath New value of property configFilePath.
     */
   /* public void setConfigFilePath2(String configFilePath2) {
        if ((configFilePath2 == null) || (configFilePath2.trim().length() == 0)) {
            throw new IllegalArgumentException(
                    "configFilePath cannot be blank or null.");
        }
        
        _configFilePath2 = configFilePath2;
    }
    
    *//**
     * Setter for property configFilePath.
     * @param configFilePath New value of property configFilePath.
     *//*
    public void setConfigFilePath3(String configFilePath3) {
        if ((configFilePath3 == null) || (configFilePath3.trim().length() == 0)) {
            throw new IllegalArgumentException(
                    "configFilePath cannot be blank or null.");
        }
        
        _configFilePath3 = configFilePath3;
    }*/
    
	public void destroy() {
		if (null != sf) {
			try {
				sf.close();
			} catch (HibernateException e) {
				// too late now
			}
		}
		sf = null;
		try {
			_RootDAO.closeCurrentThreadSessions();
		} catch(Exception ex) {}
	}

}

