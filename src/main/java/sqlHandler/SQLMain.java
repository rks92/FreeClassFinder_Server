package sqlHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLMain {
	SQLMain(){
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:/Volumes/Data/Databases/ClassFinder/test.db");
//			c = DriverManager.getConnection("/Volumes/Data/Databases/ClassFinder/jdbc:sqlite:test.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	//Function to fill the tables
	
	//Querying function
}
