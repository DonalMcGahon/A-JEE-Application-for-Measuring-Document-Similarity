package ie.gmit.sw;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import ie.gmit.sw.Configuration;

public class DBConnection {
	 
	 private DBConnection() {  
		  
	 }  
	  
	private static final ObjectContainer OBJECT_CONTAINER = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration() , Configuration.DB_PATH); 
	
	/**
	 * create a database connection with Db4oEmbedded database
	 * @return ObjectContainer database connection
	 */
	 public static ObjectContainer getInstance() {  
		  return OBJECT_CONTAINER;  
		 } 

}
