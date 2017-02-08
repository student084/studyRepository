package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.amaker.bean.User;
import com.amaker.dao.UserDao;
import com.amaker.util.DBUtil;

public class UserDaoImpl implements UserDao{

	public User login(String userName, String passWord) {
		DBUtil util = new DBUtil();
		Connection conn = util.getConnection();
		String sql = "select id, userName, passWord from usertable where username = ? and password = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, userName);
			pstmt.setString(2, passWord);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				int id = rs.getInt(1);
				
				User u = new User();
				u.setId(id);
				u.setPassWord(passWord);
				u.setUserName(userName);
				
				return u;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.closeConnection(conn);
		}
		return null;
	}

	public void register(User u) {
		DBUtil util = new DBUtil();
		Connection conn = util.getConnection();
		String sql = "insert into usertable(id, userName, password) values(?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, u.getId());
			pstmt.setString(2, u.getUserName());
			pstmt.setString(3, u.getPassWord());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.closeConnection(conn);
		}
	}

	public boolean check(String userName) {
		DBUtil util = new DBUtil();
		Connection conn = util.getConnection();
		String sql = "select id, userName, passWord from usertable where userName = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, userName);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.closeConnection(conn);
		}
		return false;
	}

}
