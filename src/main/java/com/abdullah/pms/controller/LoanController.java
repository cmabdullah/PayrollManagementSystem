package com.abdullah.pms.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.abdullah.pms.cash.service.MessageService;
import com.abdullah.pms.domain.Loan;
import com.abdullah.pms.domain.UserInfo;
import com.abdullah.pms.service.LoanService;
import com.abdullah.pms.service.UserInfoService;

@Controller
public class LoanController {
	
	@Autowired
	UserInfoService userInfoService;
	
	@Autowired
	LoanService loanService;
	
	@Autowired
	MessageService messageService;
	
	@RequestMapping("/loanreq")
	public String requestForLoan(Model model , Principal principal) {
		
		Optional<UserInfo> userInfo = userInfoService.exists(principal.getName());
		boolean isPandingRequest = loanService.isPandingRequest(userInfo.get());
		System.out.println("isPandingRequest Status : "+isPandingRequest);
		model.addAttribute("isPandingRequest", isPandingRequest);
		
		boolean isRunningLoan =  loanService.isRunningLoan(userInfo.get());
		System.out.println("isRunningLoan Status : "+isRunningLoan);
		model.addAttribute("isRunningLoan", isRunningLoan);
		
		model.addAttribute("loan",new Loan());//add attribute into model
		return "loanreq";
	}
	
	@RequestMapping(value = "/loanreq", method=RequestMethod.POST)
	public String requestForLoanProcess(Model model, @Valid Loan loan , BindingResult result, Principal principal,   RedirectAttributes redirectAttributes) {
		Optional<UserInfo> userInfo = userInfoService.exists(principal.getName());
		if (userInfo.isPresent()) {
			// implement user auth first
			loanService.postLoanApplication(loan, userInfo.get());
		}
		
		redirectAttributes.addFlashAttribute("message", "Success");
	    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		
		System.out.println(loan);
		
//		boolean isPandingLoanRequest = false;
//		//isPandingLoanRequest = loanService.isPandingLoanRequest(loan.getUserinfo_id());
//		boolean isRunningLoan = false;
//		isRunningLoan = loanService.isRunningLoan(loan.getUserinfo_id());
//		if(isPandingLoanRequest || isRunningLoan) {
//			model.addAttribute("isPandingLoanRequest",isPandingLoanRequest);
//			model.addAttribute("isRunningLoan",isRunningLoan);
//			return "loanreq";
//		} else {
//			loanService.postLeaveApplication(loan);
//		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/ad_loan")
	public String adLoanRequest(Model model) {
		List<Loan> loanList = loanService.getAllLoanRequests();
		System.out.println("loan status"+loanList);
		model.addAttribute("loanList",loanList);
		return "ad_loan";
	}
	
	@RequestMapping(value="/acceptLoan/{id}",method = RequestMethod.GET)  
	public String acceptLeave(@PathVariable int id,  RedirectAttributes redirectAttributes){
		Optional<Loan> loan = loanService.findById(id);
		if (loan.isPresent()) {
			Loan loanAcceptStatusUpdate = loan.get();
			loanAcceptStatusUpdate.setStatus(1);
			loanAcceptStatusUpdate.setApproveDate(new Date());
			//show message js
			Loan loanSaveRes = loanService.save(loanAcceptStatusUpdate);
			messageService.postLoanAcceptionMessage(loanSaveRes);
		}
		
		redirectAttributes.addFlashAttribute("message", "Success");
	    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		
		return "redirect:/ad_loan";
	}
	
	
	@RequestMapping(value="/deleteLoan/{id}",method = RequestMethod.GET)  
	public String deleteLeave(@PathVariable int id,  RedirectAttributes redirectAttributes) {
		Optional<Loan> loan = loanService.findById(id);
		if (loan.isPresent()) {
			Loan loanRejectStatusUpdate = loan.get();
			loanRejectStatusUpdate.setStatus(2);//status 2 for reject
			loanRejectStatusUpdate.setApproveDate(new Date());
			//show message js
			Loan loanSaveRes =loanService.save(loanRejectStatusUpdate);
			messageService.postLoanRejectionMessage(loanSaveRes);
		}
		
		redirectAttributes.addFlashAttribute("message", "Failed");
	    redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
	    
		return "redirect:/ad_loan";
	}

}
