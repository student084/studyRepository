package com.amaker.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.amaker.util.DBUtil;

public class CallableStatementTest {
	public static void main(String[] args){
		testCallableStatement();
		textCallaleStatement2();
	}
	static void textCallaleStatement2() {
		// TODO Auto-generated method stub
		DBUtil util = new DBUtil();
		Connection conn = util.getConnection();
		try {
			CallableStatement cst = conn.prepareCall("{call insert_user(?, ?, ?)}");
			cst.setString(1, "21");
			cst.setString(2, "student0");
			cst.setString(3, "nopasswd");
			cst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//调用有参数的存储过程
	//调用存储过程(无参的存储过程)
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
