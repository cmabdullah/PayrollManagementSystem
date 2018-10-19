package com.abdullah.PayrollManagementSystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.abdullah.PayrollManagementSystem.dao.Userinfo;
import com.abdullah.PayrollManagementSystem.service.UserinfoService;
@Controller
public class UsersInfoController {
UserinfoService userinfoService;
	
	@Autowired
	public void setUserinfoService(UserinfoService userinfoService) {
		this.userinfoService = userinfoService;
	}

	@RequestMapping("/usersinfo")
	public String asd(Model model) {
		
		List<Userinfo> usersinfo = userinfoService.getCurrent();
		
		//first usersinfo is key, last usersinfo is value
		model.addAttribute("usersinfo",usersinfo);
		
		//return usersinfo is webpage url
		return "usersinfo";
	}
	
	
	@RequestMapping("/registration")
	public String createNotice() {
		return "registration";
	}
	
	//notice beans will inject automatically
		@RequestMapping(value = "/docreate", method=RequestMethod.POST)
	public String doCreate(Model model,@Valid Userinfo userinfo , BindingResult result) {//spring magically inject information what comes from registration into userinfo bean 
			System.out.println(userinfo);
			
			//Check form validation
			if (result.hasErrors()) {
				List<ObjectError> errors = result.getAllErrors();
				for (ObjectError error: errors) {
					System.out.println(error.getDefaultMessage());
				}
			}else {
				System.out.println("Form is validet");
			}
			
			return "registrationsuccess";
	}
	
		//http://localhost:8081/PayrollManagementSystem/test?id=2018
		//Getting URL Parameters
		@RequestMapping("/test")
		public String showTest(Model model , @RequestParam("id")String id) {
			System.out.println("ID is : "+id);
			return "home";
		}
}
