package com.abdullah.pms.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.abdullah.pms.domain.BiometricData;
import com.abdullah.pms.domain.CUser;
import com.abdullah.pms.domain.UserInfo;
import com.abdullah.pms.service.AttendanceLogService;
import com.abdullah.pms.service.UserInfoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BiometricController {


	@Autowired
	UserInfoService userInfoService;

	@Autowired
	AttendanceLogService attendanceLogService;

	@RequestMapping(value = "/biometric-reg", method = RequestMethod.GET)
	// @ResponseBody
	public String bioAdd(ModelMap model) {
		model.addAttribute("cUser", new CUser(0, "", ""));
		BiometricData biometricData = new BiometricData();
		model.addAttribute("biometricData", biometricData);
		return "biometric-reg";
	}

	@ResponseBody
	@RequestMapping(value = "/biometric-reg", method = RequestMethod.POST)
	public String bioReg(Model model, @Valid CUser cUser, @ModelAttribute("biometricData") BiometricData biometricData  ,
			BindingResult result, HttpServletRequest request) {

		MultipartFile biometricImage = biometricData.getBiometricImage();
		
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

				File file = new File("src/main/resources/static/image/biometric/" + name);
				
				if (!file.exists()) {
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(file));
					stream.write(bytes);
					stream.close();
					System.out.println("Registration Success");
				}else {
					System.out.println("Already registared");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			// this user is not valid
		}

		return cUser.toString();
	}

}
