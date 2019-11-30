package com.abdullah.pms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import com.abdullah.pms.domain.Grade;
import com.abdullah.pms.domain.Shift;
import com.abdullah.pms.domain.UserInfo;
import com.abdullah.pms.service.GradeService;
import com.abdullah.pms.service.ShiftService;
import com.abdullah.pms.service.UserInfoService;

//boot
@Controller
public class UserInfoController {

	@Autowired
	ShiftService shiftService;

	@Autowired
	UserInfoService userInfoService;

	@Autowired
	GradeService gradeService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	@GetMapping("/registration")
	public String createRegister(Model model) {

		List<Shift> shifts = shiftService.findAll();
		shifts.forEach(n -> System.out.println(n.toString()));
		model.addAttribute("shifts", shifts);// add attribute into model

		List<Grade> grades = gradeService.findAll();
		model.addAttribute("grades", grades);// add attribute into model

		model.addAttribute("userInfo", new UserInfo());// add attribute into model
		return "registration";
	}

	@PostMapping("/registration")
	public String registerPost(Model model, @Valid UserInfo userInfo, Errors errors) {

		//will work later
//		if (userInfo.getShift() != null) {
//			Optional<Shift> shift = shiftService.findById(userInfo.getShift().getId());
//			if (shift.isPresent())
//				userInfo.setShift(shift.get());
//		}
//
//		if (userInfo.getGrade() != null) {
//			Optional<Grade> grade = gradeService.findById(userInfo.getGrade().getId());
//
//			if (grade.isPresent())
//				userInfo.setGrade(grade.get());
//		}

		if (errors.hasErrors()) {
			System.out.println("Error");
			return "registration";
		}

		// test print username
		System.out.println(userInfo.getUsername());

		Optional<UserInfo> UserNameValid = userInfoService.exists(userInfo.getUsername());
		if (UserNameValid.isPresent()) {
			errors.rejectValue("username", "DuplicateKey.userInfo.username",
					"this username already exist, please choose different username");
			return "registration";
		}

		Optional<UserInfo> UserEmailValid = userInfoService.existsEmail(userInfo.getEmail());
		if (UserEmailValid.isPresent()) {
			errors.rejectValue("email", "DuplicateKey.userInfo.email",
					"this email already exist, please choose different email");
			return "registration";
		}

//		try {
//			// userInfoService.save(userInfo);
//		} catch (DuplicateKeyException e) {
////					result.rejectValue("username", "DuplicateKey.user.username", "this username already exist");
//			return "newaccount";
//		}

		String pwd = userInfo.getPassword();
		String encryptPwd = passwordEncoder.encode(pwd);
		userInfo.setPassword(encryptPwd);
		System.out.println("CM " + userInfo.toString());
		 userInfoService.save(userInfo);
		return "redirect:/registration";
	}

}
