package sqlHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLMainWrapper {
	
	public static class SQLMain{
		private static Connection c;
		public SQLMain(){
			Connection c = null;
			try {
				Class.forName("org.sqlite.JDBC");
//				c = DriverManager.getConnection("jdbc:sqlite:O:/Databases/ClassFinder/test.db");
				c = DriverManager.getConnection("jdbc:sqlite:test.db");
				System.out.println("here");

				SQLMain.c = c;
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
		}	
		//Function to create the tables
		
		//Function to fill the tables
		
		//Function to read the tables
		
		//Querying function
	}
}
