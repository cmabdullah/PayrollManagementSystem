package com.abdullah.PayrollManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdullah.PayrollManagementSystem.dao.User;
import com.abdullah.PayrollManagementSystem.dao.UserDao;


@Service("usersService")
public class UsersService {
	private UserDao userDao;
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void create(User user) {
		userDao.create(user);	
	}
	
	public boolean exists(String username) {
		return userDao.exists(username);
	}
}