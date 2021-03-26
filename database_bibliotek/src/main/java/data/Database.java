package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {

	private static final String URL;
	private static final String USER;
	private static final String PASS;
	
	static {
		Properties properties = new Properties();
		try(FileInputStream inputStream = new FileInputStream("db.properties")){
			
			properties.load(inputStream);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		URL = properties.getProperty("URL");
		USER = properties.getProperty("USER");
		PASS = properties.getProperty("PASS");
		
	}
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(URL, USER, PASS);
	}
}
