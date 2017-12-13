package debug.thm.client.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import basic.zBasic.ExceptionZZZ;
import basic.zBasic.persistence.SQLiteUtilZZZ;
import use.thm.persistence.hibernate.HibernateContextProviderSingletonTHM;
import use.thm.persistence.model.AreaCell;
import use.thm.persistence.model.AreaCellType;
import use.thm.persistence.model.CellId;

public class DebugJpaQueryHexCellMain003 {

	public static void main(String[] args) {
		HibernateContextProviderSingletonTHM objContextHibernate;
		try {		
			objContextHibernate = HibernateContextProviderSingletonTHM.getInstance();
			objContextHibernate.getConfiguration().setProperty("hibernate.connection.url", "jdbc:sqlite:c:\\server\\SQLite\\DebugJpaQuery_TileHexMap03.sqlite");  //! Ich will mit einer Kopie der Datenbank arbeiten, bei einer Fehlkonfiguration ist sonst das Original ggfs. verändert.
			boolean bDbExists = SQLiteUtilZZZ.databaseFileExists(objContextHibernate);											
			if(bDbExists){
				System.out.println("Datenbank existiert als Datei.");
				objContextHibernate.getConfiguration().setProperty("hibernate.hbm2ddl.auto", "update");  //! Jetzt erst wird jede Tabelle über den Anwendungsstart hinaus gepseichert.				
			}else{
				//Fall: Datenbank existiert noch nicht
				System.out.println("Datenbank existiert nicht als Datei");
				objContextHibernate.getConfiguration().setProperty("hibernate.hbm2ddl.auto", "create");  //! Damit wird die Datenbank und sogar die Tabellen darin automatisch erstellt, aber: Sie wird am Anwendungsende geleert.
			}//end if bDbExists
			
		//TEST: SQL ABFRAGE...
		//Erzeuge den Entity Manager als Ausgangspunkt für die Abfragen. !!! Damit Hibernate mit JPA funktioniert, braucht man die Datei META-INF\persistence.xml. Darin wird die persistence-unit angegeben.		
		EntityManager em = objContextHibernate.getEntityManager("TileHexMap03");
		//Query objQuery = em.createQuery("SELECT MAX(c.id.sMapX) FROM HexCell c"); ///Liefert 9 als String zurück... 
		Query objQuery = em.createQuery("SELECT MAX(c.mapX) FROM HexCell c");//Voraussetzung, das so überhaupt ein Resultset zurückgeliefert wird, ist, das AreaType als BLOB gespeichert wird.
		//NEIN, keine Property Query objQuery = em.createQuery("SELECT c.getMapX FROM HexCell c");
		//Query objQuery = em.createQuery("SELECT c.id FROM HexCell c");  //Kein Fehler aber nur mit BLOB bei AreaType Enumeration, gibt es Ergebnis
		//Query objQuery = em.createQuery("SELECT c FROM HexCell c");

		List objResult = objQuery.getResultList();
		int max = -1;
		for(Object obj : objResult){
			System.out.println("obj.class: " + obj.getClass().getName());			
			System.out.println("Max(x) : " + obj);
		}
		
		} catch (ExceptionZZZ e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//end main

}//end class
