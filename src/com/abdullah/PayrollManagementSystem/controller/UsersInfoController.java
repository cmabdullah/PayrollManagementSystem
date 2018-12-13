package com.abdullah.PayrollManagementSystem.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.abdullah.PayrollManagementSystem.dao.Userinfo;
import com.abdullah.PayrollManagementSystem.dao.UserinfoUpdateableData;
import com.abdullah.PayrollManagementSystem.service.UserinfoService;

@Controller
public class UsersInfoController {
UserinfoService userinfoService;
	
	@Autowired
	public void setUserinfoService(UserinfoService userinfoService) {
		this.userinfoService = userinfoService;
	}

	@RequestMapping("/usersinfo")
	public String asd(Model model, Principal principal) {
		
		int userId = userinfoService.getUserIdFromName(principal.getName()).getId();
		
		//List<Userinfo> usersinfo = userinfoService.getCurrent();
		Userinfo usersinfo = userinfoService.getCurrent(principal.getName());
		System.out.println(usersinfo);
		//first usersinfo is key, last usersinfo is value
		model.addAttribute("usersinfo",usersinfo);
		
		//return usersinfo is webpage url
		return "usersinfo";
	}
	
	@RequestMapping(value = "/usersinfo_profile_update/{id}", method = RequestMethod.GET)
	public String showCurrentUserProfileObject(@PathVariable int id, Model model, Principal principal) {
		
		UserinfoUpdateableData userinfoUpdateableData = userinfoService.getCurrentUserinfoUpdateableData(principal.getName());
		model.addAttribute(userinfoUpdateableData);//add attribute into model
		return "update_users_info";
	}
	
	//@Valid not applicable in this situation
	@RequestMapping(value = "/usersinfo_profile_update_process", method = RequestMethod.POST)
	public String supdateCurrentUserProfileObject(@Valid UserinfoUpdateableData userinfoUpdateableData, Model model, Principal principal, BindingResult result) {
			
		System.out.println(userinfoUpdateableData);
		System.out.println(result.hasErrors());
		if (result.hasErrors()) {
			return "registration";
		}
		
		userinfoService.updateUserInfo(userinfoUpdateableData);
		return "redirect:/usersinfo";
	}
	
	

	
	
	@RequestMapping("/registration")
	public String createRegister(Model model) {
		model.addAttribute(new Userinfo());//add attribute into model
		return "registration";
	}
	
	//notice beans will inject automatically
		@RequestMapping(value = "/docreate", method=RequestMethod.POST)
		public String doCreate(Model model,@Valid Userinfo userinfo , BindingResult result) {//spring magically inject information what comes from registration into userinfo bean 
			System.out.println(userinfo);
			
			
			if (result.hasErrors()) {
				return "registration";
			}
			
			userinfo.setEnabled(true);
			
			if(userinfo.getAuthority().equals("ROLE_ADMIN")) {
				userinfo.setGrade_id(1233);
			} else if (userinfo.getAuthority().equals("ROLE_EMPLOYEE")) {
				userinfo.setGrade_id(1234);
			} else if (userinfo.getAuthority().equals("ROLE_ACCOUNTANT")) {
				userinfo.setGrade_id(1235);
			}
			
			
			
			//test print username
			System.out.println(userinfo.getUsername());
			if (userinfoService.exists(userinfo.getUsername())) {
				result.rejectValue("username", "DuplicateKey.userinfo.username", "this username already exist, please choose different username");
				return "registration";
			}
			System.out.println(userinfo);
			try {
				userinfoService.create(userinfo);//save data into database
			}catch(DuplicateKeyException e) {
				result.rejectValue("username", "DuplicateKey.user.username", "this username already exist");
				return "newaccount";
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
		
		@RequestMapping("/disable_enable_user")
		public String showDAE(Model model) {
			model.addAttribute(new Userinfo());//add attribute into model
			return "disable_enable_user";
		}
		
		
		@RequestMapping(value = "/DEU", method=RequestMethod.GET)
		public String enableDisablee(Model model,Userinfo userinfo, BindingResult result) {//spring magically inject information what comes from registration into userinfo bean 
			System.out.println("Enable disable");
			System.out.println(userinfo);
			//enable disable user
			
			
			
			
			if (userinfoService.exists(userinfo.getUsername())) {
				
				
				userinfoService.disableEnable(userinfo);
				
				return "disable_enable_user_success";
			}else {
				result.rejectValue("username", "DuplicateKey.userinfo.username", "this username not exist exist");
				return "disable_enable_user";
			}
			
		
			
			
	}
}
