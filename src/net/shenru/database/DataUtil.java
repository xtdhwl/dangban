package net.shenru.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DataUtil {
	// private static Connection conn;
	// static {
	// try {
	// InputStream resourceAsStream =
	// DataUtil.class.getClassLoader().getResourceAsStream(
	// "data.properties");
	// Properties prop = new Properties();
	// prop.load(resourceAsStream);
	//
	// String className = prop.getProperty("driverClassName");
	// String url = prop.getProperty("url");
	// String user = prop.getProperty("username");
	// String password = prop.getProperty("password");
	//
	// Class.forName(className);
	// conn = DriverManager.getConnection(url, user, password);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// }

	public static Connection getConn() {
		String url;
		String user;
		String password;
		try {
			InputStream resourceAsStream = DataUtil.class.getClassLoader().getResourceAsStream(
					"data.properties");
			Properties prop = new Properties();
			prop.load(resourceAsStream);

			String className = prop.getProperty("driverClassName");
			url = prop.getProperty("url");
			user = prop.getProperty("username");
			password = prop.getProperty("password");

			Class.forName(className);
			return DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		// return conn;
	}

	public static void release(ResultSet res, PreparedStatement sta, Connection conn) {
		if (res != null) {
			try {
				res.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (sta != null) {
			try {
				sta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
