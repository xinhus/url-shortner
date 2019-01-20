package url.repository.mysql;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

class MysqlConnection {
	public static Connection getConnection() {
		try {
			DriverManager.registerDriver(
			        (Driver)Class.forName( "com.mysql.jdbc.Driver" ).newInstance()
			);
	        DriverManager.setLoginTimeout(10);
	        return DriverManager.getConnection("jdbc:mysql://localhost:3306/url" , "root", "password");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
