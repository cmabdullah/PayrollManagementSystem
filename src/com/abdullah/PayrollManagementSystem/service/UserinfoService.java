package com.abdullah.PayrollManagementSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.abdullah.PayrollManagementSystem.dao.Userinfo;
import com.abdullah.PayrollManagementSystem.dao.UserinfoDao;
import com.abdullah.PayrollManagementSystem.dao.UserinfoUpdateableData;

@Service("userinfoService")
public class UserinfoService {
	
	private UserinfoDao userinfoDao;

	@Autowired
	public void setUserinfoDao(UserinfoDao userinfoDao) {
		this.userinfoDao = userinfoDao;
	}
	
	public Userinfo getCurrent(String username){
		return userinfoDao.getUserinfosBasedOnId(username);
	}

	//method level security enabled
	//@Secured({"ROLE_ADMIN", "ROLE_USER"})
	public void create(Userinfo userinfo) {
		userinfoDao.create(userinfo);	
	}
	
	public boolean exists(String username) {
		return userinfoDao.exists(username);
	}
	public boolean existsEmail(String email) {
		return userinfoDao.existsEmail(email);
	}

	public void disableEnable(Userinfo userinfo) {
		userinfoDao.disableEnable(userinfo);
		
	}

	public Userinfo getUserIdFromName(String username) {
		return userinfoDao.getUserIdFromName(username);
		
	}

	public void updateUserEnabledStatus(Userinfo userinfo) {
		userinfoDao.updateUserEnabledStatus(userinfo);
		
	}

	public boolean existsUserId(int userinfo_id) {
		return userinfoDao.existsUserId(userinfo_id);
	}

	public void updateUserInfo(UserinfoUpdateableData userinfoUpdateableData) {
		userinfoDao.updateUserInfo(userinfoUpdateableData);
		
	}

	public UserinfoUpdateableData getCurrentUserinfoUpdateableData(String username) {
		return userinfoDao.getUserinfosBasedOnUsernameUpdatebleData(username);
	}
	
}
