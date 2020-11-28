package com.ams.model.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;





public abstract class _RootDAO extends com.ams.model.base._BaseRootDAO {
	static Log log = LogFactory.getFactory().getInstance("RootDAO");

/*
	If you are using lazy loading, uncomment this
	Somewhere, you should call RootDAO.closeCurrentThreadSessions();
	public void closeSession (Session session) {
		// do nothing here because the session will be closed later
	}
*/

/*
	If you are pulling the SessionFactory from a JNDI tree, uncomment this
	protected SessionFactory getSessionFactory(String configFile) {
		// If you have a single session factory, ignore the configFile parameter
		// Otherwise, you can set a meta attribute under the class node called "config-file" which
		// will be passed in here so you can tell what session factory an individual mapping file
		// belongs to
		return (SessionFactory) new InitialContext().lookup("java:/{SessionFactoryName}");
	}
*/
	
	public static String configFile;   
	   
	public _RootDAO() {}
   
	public Class getReferenceClass() { return java.lang.Object.class; }
   
	public static void startup(String configFileName) throws HibernateException {
      setConfigurationFileName(configFileName);
      
      if (null == configFileName && sessionFactoryMap!=null && sessionFactoryMap.size() > 0) return;
      else if (sessionFactoryMap!=null && null != sessionFactoryMap.get(configFileName)) return;
      else {
         Configuration cfg = new Configuration();
         if (null == configFileName)   {
            cfg.configure();
         } else {
            cfg.configure(configFileName);  //Fails Here!
         }
                           
         setSessionFactory(configFileName, cfg.buildSessionFactory());
         //log.info(" [ CONFIG FILE : "+configFile+" ] ");
         
      }
      
      
   }
   
   
   
   public static void setConfigurationFileName(String configFileName) { configFile = configFileName; }
   public String getConfigurationFileName() { return configFile; } 
	
}