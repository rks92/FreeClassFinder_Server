package sqlHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLMainWrapper {
	
	public static class SQLMain{
		private static Connection c;
		public SQLMain(){
			try {
				System.out.println("* HERE *");
				String url = "jdbc:postgresql://ec2-54-197-251-18.compute-1.amazonaws.com:5432/d8klq3too02ocd?user=dlgvbzmdiskear&password=OBvkdpAsV1d8I8xGju4DzBHXHd&ssl=true";
				Connection conn = DriverManager.getConnection(url);
				System.out.println("************** here ********** ");

				SQLMain.c = conn;
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
