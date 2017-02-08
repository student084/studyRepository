package com.amaker.bean;

public class Meal {
	private int id;
	//订餐时间
	private String createTime;
	//用户ID
	private int userId;
	//订餐类型ID
	private int mealTypeId;
	//订餐数量
	private int num;
	//评论
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
