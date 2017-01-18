package com.amaker.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.amaker.util.DBUtil;

public class Test {
	public static void main(String[] args){
		Test test = new Test();
		test.list();
	}
	public void list(){
		//Connect the dataBase
		DBUtil util = new DBUtil();
		Connection conn = util.getConnection();
		String sql = "select * from usertable";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				System.out.println(id + ":" + username + ":" + password);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.closeConnection(conn);
		}
	}
}
