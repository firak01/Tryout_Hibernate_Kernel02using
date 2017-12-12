package debug.thm.persistence.hibernate;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import debug.thm.persistence.model.association001.AssociationTargetTester;
import debug.thm.persistence.model.association001.AssociationTargetTesterAutoKey;
import debug.thm.persistence.model.association001.AssociationTester;
import debug.thm.persistence.model.sequence001.SequenceTester;
import use.thm.client.component.AreaCellTHM;
import use.thm.client.component.HexCellTHM;
import use.thm.persistence.model.AreaCell;
import use.thm.persistence.model.AreaCellLand;
import use.thm.persistence.model.AreaCellOcean;
import use.thm.persistence.model.HexCell;
import use.thm.persistence.model.Tile;
import use.thm.persistence.model.Troop;
import use.thm.persistence.model.TroopArmy;
import use.thm.persistence.model.TroopFleet;
import basic.zBasic.ExceptionZZZ;
import basic.zBasic.ObjectZZZ;
import basic.zBasic.ReflectCodeZZZ;
import basic.zBasic.persistence.SQLiteUtilZZZ;
import basic.zBasic.persistence.hibernate.HibernateContextProviderZZZ;
import basic.zBasic.persistence.interfaces.IHibernateConfigurationProviderZZZ;
import basic.zBasic.util.abstractList.HashMapExtendedZZZ;
import basic.zBasic.util.datatype.string.StringZZZ;
import basic.zKernel.KernelUseObjectZZZ;
import basic.zKernel.KernelZZZ;

/**TODO GOON 20171206: Umstellen auf einen HibernateConfigurationProvider */
public class HibernateContextProviderSequenceAssociationXXX extends HibernateContextProviderZZZ{

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
	
	public HibernateContextProviderSequenceAssociationXXX() throws ExceptionZZZ{
		super();
	}

	public HibernateContextProviderSequenceAssociationXXX(KernelZZZ objKernel) throws ExceptionZZZ{
		super(objKernel);
	}
	
	@Override
	//Hier wird dann das spezielle Konfigurationsobjekt, für die Spezielle Konfiguration verwendet.
	//Merke: Wenn z.B. eine spezielle JNDI - Konfiguration verwendet werden soll, dann von dieser aktuellen Klasse erben (Klasse B),
	//        eine andere Konfigurationsklasse erstellen. Und dann in Klasse B untenstehende Methode überschreiben.
	public IHibernateConfigurationProviderZZZ getConfigurationProviderObject() throws ExceptionZZZ {
		IHibernateConfigurationProviderZZZ objReturn = super.getConfigurationProviderObject(); //nutze hier die "Speicherung in der Elternklasse"		
		if(objReturn==null){
			objReturn = new HibernateConfigurationProviderSequenceAssociationXXX();
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
