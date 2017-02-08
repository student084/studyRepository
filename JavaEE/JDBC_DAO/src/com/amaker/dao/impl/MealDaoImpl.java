package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.amaker.bean.Meal;
import com.amaker.bean.MealBean;
import com.amaker.dao.MealDao;
import com.amaker.util.DBUtil;

public class MealDaoImpl implements MealDao {

	public void add(Meal m) {
		DBUtil util = new DBUtil();
		Connection conn = util.getConnection();
		String sql = "insert into MealTbl(id, createTime, userId, mealTypeId, num, comment) " + "values(?, ?, ?, ?, ?, ?,)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, m.getId());
			pstmt.setString(2, m.getCreateTime());
			pstmt.setInt(3, m.getUserId());
			
			pstmt.setInt(4, m.getMealTypeId());
			pstmt.setInt(5, m.getNum());
			pstmt.setString(6, m.getComment());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.closeConnection(conn);
		}
	}
	public List list() {	
		DBUtil util = new DBUtil();
		Connection conn = util.getConnection();
		String sql = "select mt.id, mt.createTime, u.username, mtt.name, mtt.price, mt.num, mtt.price * mt.num as total " + 
					"from mealtbl as mt " + 
					"left join usertable as u on mt.userId = u.id " + 
					"left join mealtypetbl as mtt on mt.mealTypeId = mtt.id";
		
		try {
			Statement stms = conn.createStatement();
			
			ResultSet rs = stms.executeQuery(sql);
			List list = new ArrayList();
			while(rs.next()){
				int id = rs.getInt(1);
				String createTime = rs.getString(2);
				String userName = rs.getString(3);
				
				String mealName = rs.getString(4);
				int price = rs.getInt(5);
				int total = rs.getInt(6);
				
				MealBean mb = new MealBean();
				mb.setId(id);
				mb.setCreateTime(createTime);
				mb.setUserName(userName);
				mb.setName(mealName);
				mb.setPrice(price);
				mb.setTotal(total);
				
				list.add(mb);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			util.closeConnection(conn);
		}
		return null;
	}

	public void cancle(int id) {	
	}

}
