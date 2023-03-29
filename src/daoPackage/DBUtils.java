package daoPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtils {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResourceBundle rb=ResourceBundle.getBundle("db");
		return DriverManager.getConnection(rb.getString("url"),rb.getString("user"),rb.getString("pass"));
		
	}
	public static void closeConnection(Connection c) throws SQLException {
		c.close();
	}
	public static boolean isResultEmpty(ResultSet rs) throws SQLException {
		if(!rs.isBeforeFirst()&&rs.getRow()==0) {
			return true;
		}
		return false;
	}
	public static boolean isNumberic(String str) {
		if(str==null) {
			return false;
		}
		try {
			int val=Integer.parseInt(str);
		}catch(NumberFormatException nfe){
			return false;
		}
		return true;
	}
}
