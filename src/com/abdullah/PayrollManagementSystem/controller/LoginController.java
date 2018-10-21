package com.abdullah.PayrollManagementSystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abdullah.PayrollManagementSystem.dao.User;
import com.abdullah.PayrollManagementSystem.service.UsersService;

@Controller 
public class LoginController {
	
	UsersService usersService;
	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}
	
	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@RequestMapping("/admin")
	public String showAdmin(Model model) {
		List<User> users = usersService.getAllUsers();
		model.addAttribute("users", users);
		return "admin";
	}
	
	
	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		model.addAttribute("user",new User());
		return "newaccount";
	}

	@RequestMapping(value = "/createaccount", method=RequestMethod.POST)
	public String doCreate(@Valid User user, BindingResult result) {
		System.out.println(user);
		if (result.hasErrors()) {
			return "newaccount";
		}
		user.setAuthority("user");
		user.setEnabled(true);
		
		//test print username
		System.out.println(user.getUsername());
		if (usersService.exists(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username", "this username already exist, please choose different username");
			return "newaccount";
		}
		
		try {
			usersService.create(user);
		}catch(DuplicateKeyException e) {
			result.rejectValue("username", "DuplicateKey.user.username", "this username already exist");
			return "newaccount";
		}

		return "accountcreated";
	}
	
	@RequestMapping("/loggedout")
	public String showLoggedout() {
		return "loggedout";
	}
	
}
