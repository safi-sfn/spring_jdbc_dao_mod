package factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	private static String url = "jdbc:mysql://localhost:3306/MyDB";
    private static String username = "root";
    private static String password = "MySQL@2025!";
    
    private static Connection connection;
    
	static {
		try {
			// 1. Load and register the driver (optional in JDBC 4.0+)
			Class.forName("com.mysql.cj.jdbc.Driver");
			 System.out.println("Attempting to connect to MySQL database :" + username);

			// 2. Establish connection
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Successfully connected to MySQL database!");
		} catch (Exception e) {
			throw new RuntimeException("Could not connect to database", e);
		}
	}

	public static Connection getConnection() {
		return connection;
	}

}
