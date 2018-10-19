package com.abdullah.PayrollManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abdullah.PayrollManagementSystem.dao.Userinfo;
import com.abdullah.PayrollManagementSystem.service.UserinfoService;
@Controller
public class HomeController {
	
	UserinfoService userinfoService;
	
	@Autowired
	public void setUserinfoService(UserinfoService userinfoService) {
		this.userinfoService = userinfoService;
	}


	@RequestMapping("/")
	public String asd(Model model) {
		
		List<Userinfo> usersinfo = userinfoService.getCurrent();
		model.addAttribute("usersinfo",usersinfo);
		return "home";
	}
}
