package com.amaker.dao;

import java.util.List;

import com.amaker.bean.Meal;

public interface MealDao {
	//��Ӷ���
	public void add(Meal m);
	//��ѯ����
	public List list();
	//ȡ������
	public void cancle(int id);
}
