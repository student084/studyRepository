package com.amaker.bean;

public class Meal {
	private int id;
	//����ʱ��
	private String createTime;
	//�û�ID
	private int userId;
	//��������ID
	private int mealTypeId;
	//��������
	private int num;
	//����
	private String comment;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getMealTypeId() {
		return mealTypeId;
	}
	public void setMealTypeId(int mealTypeId) {
		this.mealTypeId = mealTypeId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
