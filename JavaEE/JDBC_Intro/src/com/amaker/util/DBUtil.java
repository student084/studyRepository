package com.amaker.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	public static void main(String[] args){
		DBUtil util = new DBUtil();
		Connection conn = util.getConnection();
		System.out.println(conn);
	}
	public Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				return DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_db", "root", "19950814Jian");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
