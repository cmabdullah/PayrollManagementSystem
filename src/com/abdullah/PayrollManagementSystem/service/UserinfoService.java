package com.abdullah.PayrollManagementSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.abdullah.PayrollManagementSystem.dao.Userinfo;
import com.abdullah.PayrollManagementSystem.dao.UserinfoDao;

@Service("userinfoService")
public class UserinfoService {
	
	private UserinfoDao userinfoDao;

	@Autowired
	public void setUserinfoDao(UserinfoDao userinfoDao) {
		this.userinfoDao = userinfoDao;
	}
	
	public List<Userinfo> getCurrent(){
		return userinfoDao.getUserinfos();
	}

	//method level security enabled
	//@Secured({"ROLE_ADMIN", "ROLE_USER"})
	public void create(Userinfo userinfo) {
		userinfoDao.create(userinfo);	
	}
	
	public boolean exists(String username) {
		return userinfoDao.exists(username);
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
	
}
