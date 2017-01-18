package com.amaker.test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.amaker.util.DBUtil;

public class Test {
	public static void main(String[] args){
		Test test = new Test();
		System.out.println(test.list());
	}
	public List list(){
		//Connect the dataBase
		DBUtil util = new DBUtil();
		Connection conn = util.getConnection();
		try {
			DatabaseMetaData dbmd = conn.getMetaData();
			String dbName = dbmd.getDatabaseProductName();
			String dbVersion = dbmd.getDriverVersion();
			System.out.println("the database name is :" + dbName + ", it's version is : " + dbVersion);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = "select * from usertable";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println(rs);
			List list = new ArrayList();
			while(rs.next()){
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				User u = new User();
				u.setId(id);
				u.setUserName(username);
				u.setPassword(password);
				list.add(u);
				//System.out.println(id + ":" + username + ":" + password);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.closeConnection(conn);
		}
		return null;
	}
}

class User{
	private int id;
	private String userName;
	private String password;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
