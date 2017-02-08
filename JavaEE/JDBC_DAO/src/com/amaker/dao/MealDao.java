package com.amaker.dao;

import java.util.List;

import com.amaker.bean.Meal;

public interface MealDao {
	//添加订单
	public void add(Meal m);
	//查询订单
	public List list();
	//取消订单
	public void cancle(int id);
}
