package com.amaker.dao.impl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.amaker.bean.User;
import com.amaker.dao.UserDao;

public class UserDaoImplTest {
	UserDao dao;
	//自动调用
	@Before
	public void setUp() throws Exception {
		dao = new UserDaoImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRegister() {
		User u  = new User();
		u.setId(4);
		u.setUserName("user004");
		u.setPassWord("password004");
		
		dao.register(u);
	}

	@Test
	public void testCheck() {
		boolean b = dao.check("user004");
		System.out.println(b);
	}

	@Test
	public void testLogin() {
		User u = dao.login("user004", "password004");
		System.out.println(u.getId());
	}
}
