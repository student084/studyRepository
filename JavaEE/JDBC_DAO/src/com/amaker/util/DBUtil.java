package com.amaker.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		DBUtil util = new DBUtil();
//		Connection conn = util.getConnection();
//		System.out.println(conn);
//		util.closeConnection(conn);
	}
	
	public Connection getConnection(){
		Properties prop = new Properties();
		String driver = null;
		String url = null;
		String username = null;
		String password = null;
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("DBConfig.properties"));
			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			try {
				Class.forName(driver);
				try {
					return DriverManager.getConnection(url, username, password);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void closeConnection(Connection conn){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
