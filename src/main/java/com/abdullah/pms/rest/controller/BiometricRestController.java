package com.abdullah.pms.rest.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.abdullah.pms.domain.Attendance;
import com.abdullah.pms.domain.CUser;
import com.abdullah.pms.domain.UserInfo;
import com.abdullah.pms.service.AttendanceLogService;
import com.abdullah.pms.service.AttendanceService;
import com.abdullah.pms.service.BiomatricRestService;
import com.abdullah.pms.service.UserInfoService;
import com.machinezoo.sourceafis.FingerprintImage;
import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;

import lombok.extern.slf4j.Slf4j;
//https://www.roytuts.com/file-upload-example-using-spring-rest-controller/
@Slf4j
@RestController
public class BiometricRestController {


	@Autowired
	UserInfoService userInfoService;

	@Autowired
	AttendanceLogService attendanceLogService;
	
	@Autowired
	BiomatricRestService biomatricRestService;
	
	@Autowired
	AttendanceService attendanceService;
	
	
	

	@PostMapping( "/biometric-att-rest")
	public ResponseEntity<String> bioAdd(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		
		String message = "";
		if (file == null) {
			message = "No file exist!";
			return new ResponseEntity<String>(message, HttpStatus.OK);
		}
		
		Optional<Path> userValidation = Optional.ofNullable(biomatricRestService.checkValidation(file));
		int decodeUserId = Integer.MIN_VALUE;
		if (userValidation.isPresent()) {
			decodeUserId = biomatricRestService.decodeUserIdFromPath(userValidation.get());
		}
		
		Optional<UserInfo> filteredUserInfo = userInfoService.findById(decodeUserId);
		Optional<Attendance> hasLoginEmployee = Optional.empty();
		if (filteredUserInfo.isPresent()) {
			// give attendance
			hasLoginEmployee =  attendanceService.hasLogin(filteredUserInfo.get());
			// return false causes not logd in so we have to login

			//this line not executed
			if (!hasLoginEmployee.isPresent()) {
				boolean doLogin = attendanceService.doLogin(filteredUserInfo.get(), request.getRemoteAddr());
				message = "Login Success";
			} else {
				boolean hasLogout = attendanceLogService.hasLogout(hasLoginEmployee.get());
				message = "Logout Success";
			}
			System.out.println("Login status : " + hasLoginEmployee.isPresent());
		}else {
			message = "User not valid";
		}
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}


	@PostMapping( "/biometric-reg-rest")
	public ResponseEntity<String> bioReg(@RequestParam("file") MultipartFile file, @RequestParam("username") String username, @RequestParam("password") String password) {

		String message = "";
		CUser cUser = new CUser();
		cUser.setUsername(username);
		cUser.setPassword(password);
		
		if (file == null) {
			message = "No file exist!";
			return new ResponseEntity<String>(message, HttpStatus.OK);
		}
		
		MultipartFile biometricImage = file ;
		
		// collect all user
		Optional<List<UserInfo>> userInfos = Optional.ofNullable(userInfoService.findAll());
		// filter this user exist or not?
		Optional<UserInfo> filteredUserInfo = userInfos.get().stream()
				.filter(userInfo -> (cUser.getUsername().equals(userInfo.getUsername())
						&& cUser.getPassword().equals(userInfo.getPassword())))
				.findFirst();
		
		// if exist then perform registration
		if (filteredUserInfo.isPresent()) {
			System.out.println("user Id : " + filteredUserInfo.get().getId());
			try {
				String name = filteredUserInfo.get().getId() + ".png";
				byte[] bytes = biometricImage.getBytes();
				System.out.println("Byte length "+bytes.length);

				File filePath = new File("src/main/resources/static/image/biometric/" + name);
				
				if (!filePath.exists()) {
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(filePath));
					stream.write(bytes);
					stream.close();
					message = "Registration Success";
				}else {
					message = "Already registared";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			message = "User not valid";
			// this user is not valid
		}
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
}
