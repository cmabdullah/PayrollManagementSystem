package com.abdullah.pms.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.abdullah.pms.domain.CUser;
import com.abdullah.pms.domain.UserInfo;


//boot
@Controller
public class UserInfoController {
	@GetMapping("/registration")
	public String createRegister(Model model) {
		model.addAttribute("userInfo",new UserInfo());// add attribute into model
		return "registration";
	}
	
	@PostMapping("/registration")
	public String registerPost(Model model,@Valid UserInfo userInfo) {
		System.out.println(userInfo.toString());
		
		return "redirect:/registration";
	}

}
