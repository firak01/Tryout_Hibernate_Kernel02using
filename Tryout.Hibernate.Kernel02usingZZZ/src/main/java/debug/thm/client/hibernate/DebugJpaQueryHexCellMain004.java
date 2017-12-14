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
import use.thm.persistence.model.HexCell;

/**Merke 20171214: Diese Klasse hat keine eingene HibernateConfigurationProvider, sondern nutzt den von TileHexMap und modifiziert die Datenbankposition, da sie mit einer Kopie arbeitet.*/
public class DebugJpaQueryHexCellMain004 {

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
		//Desweiteren müssen für die Verwendung des EntityManagers alle Konfiurtionen in der Datei hibernate.cfg.xml hinterlegt werden.
		EntityManager em = objContextHibernate.getEntityManager("TileHexMap03");
		//Query objQuery = em.createQuery("SELECT MAX(c.id.mapX) FROM HexCell c"); //ABER: DAS LIEFERT DAS MAX DER ZEICHENKETTE (also 9) zurück//Voraussetzung, das so überhaupt ein Resultset zurückgeliefert wird, ist, das AreaType als BLOB gespeichert wird.
		Query objQuery = em.createQuery("SELECT MAX(c.mapX) FROM HexCell c");
		//NEIN, keine Property Query objQuery = em.createQuery("SELECT c.getMapX FROM HexCell c");
		//Query objQuery = em.createQuery("SELECT c.id FROM HexCell c");  //Kein Fehler aber nur mit BLOB bei AreaType Enumeration, gibt es Ergebnis
		//Query objQuery = em.createQuery("SELECT c FROM HexCell c");

		List objResult = objQuery.getResultList();
		if(objResult==null){
			System.out.println("Kein Resultobjekt nach der Query vorhanden");
		}else{
			int max = -1;
			for(Object obj : objResult){
				if(obj==null){
					System.out.println("Kein Objekt im Resultobjekt nach der Query vorhanden");
				}else{
					System.out.println("obj.class: " + obj.getClass().getName());					
					System.out.println("Max(x) : " + obj);
				}
			}
		}
		
		//Buch Seite 61: .find() anwenden auf einen zusammengesetzten Schlüssel
		CellId objId = new CellId("EINS", "29","9");
		Object objResultFind = em.find(HexCell.class, objId);
		if(objResultFind==null){
			System.out.println("Kein Objekt an der Stelle gefunden.");
		}else{
			System.out.println("objFound.class: " +  objResultFind.getClass().getName());
			AreaCell objCellFound = (AreaCell) objResultFind;
			System.out.println("Der Typ dieser Zelle ist: " + objCellFound.getAreaTypeObject().name());
			System.out.println("Die Beschreibung für den Zelltyp ist: " + objCellFound.getAreaType().toString());
		}
		} catch (ExceptionZZZ e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//end main

}//end class
