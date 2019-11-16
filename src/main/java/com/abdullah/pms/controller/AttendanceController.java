package com.abdullah.pms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abdullah.pms.domain.Attendance;
import com.abdullah.pms.domain.CUser;
import com.abdullah.pms.domain.UserInfo;
import com.abdullah.pms.service.AttendanceLogService;
import com.abdullah.pms.service.AttendanceService;
import com.abdullah.pms.service.CUserService;
import com.abdullah.pms.service.UserInfoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AttendanceController {

	@Autowired
	AttendanceService attendanceService;
	@Autowired
	AttendanceLogService attendanceLogService;

	@Autowired
	CUserService cUserService;

	@Autowired
	UserInfoService userInfoService;

	@RequestMapping("/hello")
	public String home(ModelMap model) {
		List<Attendance> attendances = attendanceService.findAll();
		attendances.forEach(n -> System.out.println(n.toString()));
		model.put("attendances", attendances);
		Optional<Attendance> op = attendanceService.findById(2);
		log.info("receive objct", op.get());
		return "list_attendances";
	}

	@RequestMapping("/attendance")
	public String attendance() {
		List<CUser> cUsers = new ArrayList<>();
		cUsers.add(new CUser(1, "cmaa546", "1234"));
		cUsers.add(new CUser(2, "cmaa13", "12345"));
		cUsers.add(new CUser(3, "cmaa23", "12346"));

//		CUser cUser = new CUser(3, "cmaa23", "12346");
		cUserService.save(cUsers);
		return "list_attendances";
	}

	@RequestMapping(value = "/give-attendance", method = RequestMethod.POST)
	public String giveAttendance(Model model, @Valid CUser cUser, BindingResult result, HttpServletRequest request) {
		Optional<Attendance> hasLoginEmployee = Optional.empty();

		List<UserInfo> userInfos = userInfoService.findAll();
		Optional<UserInfo> filteredUserInfo = userInfos.stream()
				.filter(userInfo -> (cUser.getUsername().equals(userInfo.getUsername())
						&& cUser.getPassword().equals(userInfo.getPassword())))
				.findFirst();
		userInfos.forEach(n -> System.out.println("Login user Info : " + n.toString()));

		if (filteredUserInfo.isPresent()) {
			// give attendance
			hasLoginEmployee = Optional.ofNullable( attendanceService.hasLogin(filteredUserInfo.get()));
			// return false causes not logd in so we have to login

			if (!hasLoginEmployee.isPresent()) {
				// if you are not login today then log in , //this module is working
				boolean doLogin = attendanceService.doLogin(filteredUserInfo.get(), request.getRemoteAddr());
			} else {
				boolean hasLogout = attendanceLogService.hasLogout(hasLoginEmployee.get());
			}
			System.out.println("Login status : " + hasLoginEmployee.isPresent());
		}

		System.out.println(cUser.toString());
		return "redirect:/give-attendance";
	}

	@RequestMapping(value = "/give-attendance", method = RequestMethod.GET)
	// @ResponseBody
	public String addTodo(ModelMap model) {
		model.addAttribute("cUser", new CUser(0, "", ""));
		return "give-attendance";
	}

//	@RequestMapping(value="/delete-attendance", method = RequestMethod.GET)
//	public String deleteTodo(@RequestParam int id){
//		//exception handle
////		if(id==1)
////			throw new RuntimeException("Something went wrong");
//		
//		//attendanceService.deleteById(id);
//		
//		//in mamory database
//		//todoService.deleteTodo(id);
//		return "redirect:/list-attendances";
//	}
}
