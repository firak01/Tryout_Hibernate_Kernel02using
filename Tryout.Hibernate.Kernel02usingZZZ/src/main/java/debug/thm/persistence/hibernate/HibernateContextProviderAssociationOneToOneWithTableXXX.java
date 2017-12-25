package debug.thm.persistence.hibernate;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;

import basic.zBasic.ExceptionZZZ;
import basic.zBasic.ReflectCodeZZZ;
import basic.zBasic.persistence.hibernate.HibernateContextProviderZZZ;
import basic.zKernel.KernelZZZ;
import debug.thm.persistence.model.association002.AssociationTargetTesterAutoKey;
import debug.thm.persistence.model.association002.AssociationTester;

/**TODO GOON 20171206: Umstellen auf einen HibernateConfigurationProvider */
public class HibernateContextProviderAssociationOneToOneWithTableXXX extends HibernateContextProviderZZZ{
	
	
	
//	//Über die EntityManagerFactory erstellte EntityManager werden in dieser Hashmap verwaltet: hm("Name des Schemas/der Datenbank") = objEntityManager;
//	HashMapExtendedZZZ<String, EntityManager> hmEntityManager = new HashMapExtendedZZZ<String, EntityManager>();
//	public HibernateContextProviderTHM() throws ExceptionZZZ{
//		super();
//		boolean bErg = this.fillConfiguration();
//		if(!bErg){
//			ExceptionZZZ ez = new ExceptionZZZ("Configuration not successfully filled.", iERROR_RUNTIME, this, ReflectCodeZZZ.getMethodCurrentName());
//			throw ez;
//		}
//	}
	
	public HibernateContextProviderAssociationOneToOneWithTableXXX() throws ExceptionZZZ{
		super();
	}
//
//	public HibernateContextProviderTHM(KernelZZZ objKernel) throws ExceptionZZZ{
//		super(objKernel);
//		boolean bErg = this.fillConfiguration();
//		if(!bErg){
//			ExceptionZZZ ez = new ExceptionZZZ("Configuration not successfully filled.", iERROR_RUNTIME, this, ReflectCodeZZZ.getMethodCurrentName());
//			throw ez;
//		}
//	}
	public HibernateContextProviderAssociationOneToOneWithTableXXX(KernelZZZ objKernel) throws ExceptionZZZ{
		super(objKernel);
	}
	
	/**Fülle die Configuration
	 * a) mit globalen Werten, z.B. Datenbankname, Dialekt
	 * b) mit den zu betrachtenden Klassen, entweder annotiert oder per eigener XML Datei.
	 * 
	 * @return
	 * @throws ExceptionZZZ 
	 */
	public boolean fillConfiguration(Configuration cfg) throws ExceptionZZZ{
		boolean bReturn = false;
		
		bReturn = fillConfigurationGlobal(cfg);
		//+++ Die für Hibernate DEBUG Konfigurierten Klassen hinzufügen
		//Merke: Wird eine Klasse ohne @Entity hinzugefügt, gibt es folgende Fehlermeldung: Exception in thread "main" org.hibernate.AnnotationException: No identifier specified for entity: use.thm.client.component.AreaCellTHM
		bReturn = addConfigurationAnnotatedClass(cfg, AssociationTester.class);
		bReturn = addConfigurationAnnotatedClass(cfg, AssociationTargetTesterAutoKey.class);
		
		return bReturn;
	}
	
	/** Fülle globale Werte in das Configuruation Objekt, z.B. der Datenbankname, Dialekt, etc.
	 * 
	 */
	public boolean fillConfigurationGlobal(Configuration cfg){
				//TODO: Die hier verwendeten Werte aus der Kernel-Konfiguration auslesen.
				//Programmatisch das erstellen, das in der hibernate.cfg.xml Datei beschrieben steht.
				//Merke: Irgendwie funktioniert es nicht die Werte in der hibernate.cfg.xml Datei zu überschreiben.
		 		//			Darum muss z.B. hibernate.hbm2ddl.auto in der Konfigurationdatei auskommentiert werden, sonst ziehen hier die Änderungen nicht.
				cfg.setProperty("hiberate.show_sql", "true");
				cfg.setProperty("hiberate.format_sql", "true");
				cfg.setProperty("hibernate.dialect","basic.persistence.hibernate.SQLiteDialect" );
				cfg.setProperty("hibernate.connection.driver_class", "org.sqlite.JDBC");
				cfg.setProperty("hibernate.connection.url", "jdbc:sqlite:c:\\server\\SQLite\\DebugAssociationOneToOneWithTableTester.sqlite");
				cfg.setProperty("hibernate.connection.username", "");
				cfg.setProperty("hibernate.connection.password", "");

				/*
				 * So the list of possible options are,
    validate: validate the schema, makes no changes to the database.
    update: update the schema.
    create: creates the schema, destroying previous data.
    create-drop: drop the schema when the SessionFactory is closed explicitly, typically when the application is stopped.
				 */
				cfg.setProperty("hibernate.hbm2ddl.auto", "create"); //! Damit wird die Datenbank und sogar die Tabellen darin automatisch erstellt, aber: Sie wird am Anwendungsende geleert.
				//cfg.setProperty("hibernate.hbm2ddl.auto", "update");  //! Jetzt erst wird jede Tabelle über den Anwendungsstart hinaus gepseichert.
				cfg.setProperty("cache.provider_class", "org.hiberniate.cache.NoCacheProvider");
				cfg.setProperty("current_session_context_class", "thread");
				
				return true;
	}
	
	/** Wird eine zu persisierende Klasse nicht der Konfiguration übergeben, kommt es z.B. zu folgender Fehlermeldung
	 *  Exception in thread "main" org.hibernate.MappingException: Unknown entity: use.thm.client.component.AreaCellTHM
	 * @param cls
	 * @return
	 * @throws ExceptionZZZ
	 */
	public boolean addConfigurationAnnotatedClass(Configuration cfg, Class cls) throws ExceptionZZZ{
		if(cls==null){
			ExceptionZZZ ez = new ExceptionZZZ("Class-Object not passed.", iERROR_PARAMETER_MISSING, this, ReflectCodeZZZ.getMethodCurrentName());
			throw ez;
		}
		cfg.addAnnotatedClass(cls);
		return true;
	}
		
	@Override
	public Session declareSessionHibernateIntercepted(SessionFactoryImpl sf) {
		// TODO Auto-generated method stub
		return null;
	}	
	
	@Override
	public boolean declareConfigurationHibernateEvent(Configuration cfg) {
		// TODO Auto-generated method stub
		return false;
	}
}
