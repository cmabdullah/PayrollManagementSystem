package com.abdullah.pms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.abdullah.pms.domain.Attendance;
import com.abdullah.pms.service.AttendanceService;

@Controller
public class AttendanceController {
	
	@Autowired
	AttendanceService attendanceService;
	
	@RequestMapping("/hello")
	public String home(ModelMap model) {
		List<Attendance> attendances = attendanceService.findAll();
		attendances.forEach(n -> System.out.println(n.toString()));
		model.put("attendances",attendances);
		Optional<Attendance> op = attendanceService.findById(2);
		attendanceService.deleteById(2);
		System.out.println(op.get());
		return "list_attendances";
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
