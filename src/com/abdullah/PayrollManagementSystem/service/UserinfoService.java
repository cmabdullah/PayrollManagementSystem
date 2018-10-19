package com.abdullah.PayrollManagementSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	public void create(Userinfo userinfo) {
		userinfoDao.create(userinfo);	
	}
	
}
