package HelperFunctions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlCaller {
	public static BufferedReader getUrl(String originalUrl) {
		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		
		try{
			url = new URL(originalUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));			

			return reader;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
