package debug.thm.persistence.hibernate;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;

import use.thm.persistence.hibernate.HibernateConfigurationProviderTHM;
import basic.zBasic.ExceptionZZZ;
import basic.zBasic.ReflectCodeZZZ;
import basic.zBasic.persistence.hibernate.HibernateContextProviderZZZ;
import basic.zBasic.persistence.interfaces.IHibernateConfigurationProviderZZZ;
import basic.zKernel.IKernelZZZ;
import basic.zKernel.KernelZZZ;
//import debug.thm.persistence.model.association001.AssociationTargetTester;
//import debug.thm.persistence.model.association001.AssociationTargetTesterAutoKey;
//import debug.thm.persistence.model.association001.AssociationTester;
import debug.thm.persistence.model.sequence002.SequenceTester;

/**20171206: Umstellung auf einen HibernateConfigurationProvider */
public class HibernateContextProviderSequenceAssociation002XXX extends HibernateContextProviderZZZ{
	
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
	
	public HibernateContextProviderSequenceAssociation002XXX() throws ExceptionZZZ{
		super();
	}

	public HibernateContextProviderSequenceAssociation002XXX(IKernelZZZ objKernel) throws ExceptionZZZ{
		super(objKernel);
	}
		
	@Override
	//Hier wird dann das spezielle Konfigurationsobjekt, für die Spezielle Konfiguration verwendet.
	//Merke: Wenn z.B. eine spezielle JNDI - Konfiguration verwendet werden soll, dann von dieser aktuellen Klasse erben (Klasse B),
	//        eine andere Konfigurationsklasse erstellen. Und dann in Klasse B untenstehende Methode überschreiben.
	public IHibernateConfigurationProviderZZZ getConfigurationProviderObject() throws ExceptionZZZ {
		IHibernateConfigurationProviderZZZ objReturn = super.getConfigurationProviderObject(); //nutze hier die "Speicherung in der Elternklasse"		
		if(objReturn==null){
			objReturn = new HibernateConfigurationProviderSequenceAssociation002XXX();
			this.setConfigurationProviderObject(objReturn);
		}
		return objReturn;
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
