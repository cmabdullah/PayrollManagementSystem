package com.abdullah.PayrollManagementSystem.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abdullah.PayrollManagementSystem.dao.Leave;
import com.abdullah.PayrollManagementSystem.dao.Loan;
import com.abdullah.PayrollManagementSystem.messageBrokerService.SendMessageService;
import com.abdullah.PayrollManagementSystem.service.LoanService;
import com.abdullah.PayrollManagementSystem.service.UserinfoService;
@Controller
public class LoanController {
	private static Logger logger = Logger.getLogger(LoanController.class);
	
	
	UserinfoService userinfoService;
	
	@Autowired
	public void setUserinfoService(UserinfoService userinfoService) {
		this.userinfoService = userinfoService;
	}
	
	LoanService loanService;
	
	@Autowired
	public void setLoanService(LoanService loanService) {
		this.loanService = loanService;
	}
	
	@Autowired
	SendMessageService sendMessageService;
	
	public void setSendMessageService(SendMessageService sendMessageService) {
		this.sendMessageService = sendMessageService;
	}
	
	@RequestMapping("/loanreq")
	public String requestForLoan(Model model) {
		model.addAttribute(new Loan());//add attribute into model
		logger.info("Showing loan.....");
		return "loanreq";
	}
	//@RequestMapping("/loanreq_process")
	@RequestMapping(value = "/loanreq_process", method=RequestMethod.POST)
	public String requestForLoanProcess(Model model, @Valid Loan loan , BindingResult result, Principal principal) {
		
		
		
//		if (result.hasErrors()) {
//			List<ObjectError> errors = result.getAllErrors();
//			for (ObjectError error: errors) {
//				System.out.println(error.getDefaultMessage());
//			}
//		}else {
//			System.out.println("Form is validet");
//		}
		
		loan.setUserinfo_id(userinfoService.getUserIdFromName(principal.getName()).getId());
		logger.info("Showing loan....."+loan);
		
		
		System.out.println(loan);
		
		boolean isPandingLoanRequest = false;
		isPandingLoanRequest = loanService.isPandingLoanRequest(loan.getUserinfo_id());
		boolean isRunningLoan = false;
		
		isRunningLoan = loanService.isRunningLoan(loan.getUserinfo_id());
		
		
		
		logger.info("is Panding Loan Request Status : "+isPandingLoanRequest);
		if(isPandingLoanRequest || isRunningLoan) {
			model.addAttribute("isPandingLoanRequest",isPandingLoanRequest);
			model.addAttribute("isRunningLoan",isRunningLoan);
			return "loanreq";
		} else {
			loanService.postLeaveApplication(loan);
		}
		
		
		
		return "disable_enable_user_success";
	}
	
	
	@RequestMapping("/ad_loan")
	public String approveOrDenayLoan(Model model) {
		
		
		
		List<Loan> loanAllPendingRequests = loanService.getAllPendingRequests();
		
		for (Loan loan : loanAllPendingRequests) {
			System.out.println(loan);
		}
		
		logger.info(" loanAllPendingRequests " +loanAllPendingRequests.size());
		model.addAttribute("loanAllPendingRequests",loanAllPendingRequests);
		
		
		logger.info("Showing approve or deny loan request.....");
		return "ad_loan";
	}
	
	
	@RequestMapping(value="/deleteemp/{id}",method = RequestMethod.GET)  
	public String deleteLoan(@PathVariable int id) throws IOException, Exception {
		
		logger.info("deleted id is : "+id);
			loanService.deletePendingLoanApplication(id);
			sendMessageService.postLoanRejectionMessage(id);
		return "redirect:/ad_loan";
	}
	
	@RequestMapping(value="/accept_loan_request/{id}",method = RequestMethod.GET)  
	public String acceptLoanRequest(@PathVariable int id) {
		logger.info("Accept id is : "+id);
		loanService.acceptPendingLoanApplication(id);
		sendMessageService.postLoanAcceptionMessage(id);
		return "redirect:/ad_loan";
		//redirect:/attendance
	}
	
}
