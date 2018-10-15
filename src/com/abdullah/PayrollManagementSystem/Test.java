package com.abdullah.PayrollManagementSystem;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class Test {
	@RequestMapping("/")
	public String asd(Model model) {
		String name= "haider";
		model.addAttribute("one",name);
		return "home";
	}
}
