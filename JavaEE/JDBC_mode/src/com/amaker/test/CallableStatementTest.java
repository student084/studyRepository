package com.amaker.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.amaker.util.DBUtil;

public class CallableStatementTest {
	public static void main(String[] args){
		testCallableStatement();
	}
	
	static void testCallableStatement(){
		DBUtil util = new DBUtil();
		Connection conn = util.getConnection();
		String sql = "{call all_user()}";
		try {
			CallableStatement cstmt = conn.prepareCall(sql);
			ResultSet rs = cstmt.executeQuery();
			while(rs.next()){
				String name = rs.getString("username");
				System.out.println(name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
