package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class dataBase {
	public static Connection connectDb() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/pharmacyDB","root", "");
			return connect;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
