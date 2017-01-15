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
	// Connect the dateBases
	public Connection getConnection(){
		try {
			//加载驱动,其中driver是类的名称
			Class.forName("com.mysql.jdbc.Driver");
			try {
				//获得链接
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
	//Close a connection
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
