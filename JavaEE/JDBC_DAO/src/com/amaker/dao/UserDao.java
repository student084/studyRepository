package com.amaker.dao;

import com.amaker.bean.User;

public interface UserDao {
	//login
	public User login(String userName, String passWord);
	//register
	public void register(User u);
	//check
	public boolean check(String userName);
}
