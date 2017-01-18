package com.amaker.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.amaker.util.DBUtil;

public class PrepatedstatementTest {
	public static void main(String[] args){
		getPreparedStatement(3);
	}
	
	public static void getPreparedStatement(int id){
		DBUtil util = new DBUtil();
		Connection conn = util.getConnection();
		String sql = "select * from usertable where id = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			System.out.println(rs.getString("password"));
			System.out.println(pstmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
