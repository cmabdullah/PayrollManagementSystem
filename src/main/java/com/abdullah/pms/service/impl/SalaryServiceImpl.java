package com.abdullah.pms.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdullah.pms.domain.Attendance;
import com.abdullah.pms.domain.Grade;
import com.abdullah.pms.domain.Leave;
import com.abdullah.pms.domain.Loan;
import com.abdullah.pms.domain.LoanPaidDetails;
import com.abdullah.pms.domain.Salary;
import com.abdullah.pms.domain.UserInfo;
import com.abdullah.pms.repository.SalaryRepository;
import com.abdullah.pms.service.AttendanceService;
import com.abdullah.pms.service.LeaveService;
import com.abdullah.pms.service.LoanPaidDetailsService;
import com.abdullah.pms.service.LoanService;
import com.abdullah.pms.service.SalaryService;
import com.abdullah.pms.service.UserInfoService;

@Service
public class SalaryServiceImpl implements SalaryService {

	@Autowired
	UserInfoService UserInfoService;

	@Autowired
	LeaveService leaveService;

	@Autowired
	AttendanceService attendanceService;

	@Autowired
	LoanService loanService;

	@Autowired
	LoanPaidDetailsService loanPaidDetailsService;

	@Autowired
	SalaryRepository salaryRepository;

	@Override
	public void calculateSalary() {
		// enable disable not valid yet
		List<UserInfo> userEnableInfo = UserInfoService.findByEnabled(true);
		userEnableInfo.forEach(singleUserInfo -> {

			final UserInfo userInfo = singleUserInfo;
			final Grade grade = singleUserInfo.getGrade();

			// calculate bonus
			final float totalsalary = grade.getBasic() + grade.getHouseRent() + grade.getLunch() + grade.getMedicalAllowence()
					+ grade.getStudy() + grade.getTransport();

			final float oneDaySalary = totalsalary / 22;

			Date dateTo = new Date();
			LocalDate localDateTo = dateTo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate localDateFrom = localDateTo.minusMonths(1);
			Date dateFrom = Date.from(localDateFrom.atStartOfDay(ZoneId.systemDefault()).toInstant());

			final int numberOfLeaveDays = countNumberOfLeaveDays(userInfo, dateFrom, dateTo);
			final int monthlyWorkingHour = countMonthlyWorkingHour(singleUserInfo, localDateFrom, localDateTo);
			final float finalSalary = countMonthlySalary(totalsalary, oneDaySalary, monthlyWorkingHour);
			final Optional<Loan> loan = Optional.ofNullable(loanService.runningLoanObject(userInfo));
			
			double installment = 0;// kisti
			Optional<LoanPaidDetails> loanPaidDetails = Optional.empty();
			
			if (loan.isPresent()) {
				loanPaidDetails = Optional.ofNullable(getLoanPaidDetails(loan.get(), finalSalary));
				installment = loanPaidDetails.get().getInstallment();
			} // loan service end

			// loan,leave
			Salary salary = Salary.builder().userInfo(userInfo).grade(grade).monthlyWorkingHour(monthlyWorkingHour)
					.totalLeaveDays(numberOfLeaveDays).regular("y").totalSalary(finalSalary)
					.netSalary((float) (finalSalary - installment)).paymentDate(new Date()).build();

			if (loan.isPresent()) 
				salary.setLoan(loan.get());
			

			if (loanPaidDetails.isPresent())
				loanPaidDetailsService.save(loanPaidDetails.get());
			
			salaryRepository.save(salary);
			System.out.println("Calculate method called");
			System.out.println(singleUserInfo.toString());

		});

	}

	private int countMonthlyWorkingHour(UserInfo singleUserInfo, LocalDate localDateFrom, LocalDate localDateTo) {
		int monthlyWorkingHour = 0;
		Optional<List<Attendance>> usersAllAttendanceList = Optional.ofNullable(
				attendanceService.findByUserInfoAndLoginDateBetween(singleUserInfo, localDateFrom, localDateTo));
		// get monthly working hour
		if (usersAllAttendanceList.isPresent()) {
			monthlyWorkingHour = usersAllAttendanceList.get().stream().mapToInt(n -> n.getWorkingHours()).sum();
			System.out.println("monthlyWorkingHour : " + monthlyWorkingHour);// 19
			System.out.println("Attendance list size " + usersAllAttendanceList.get().size());
		}

		return monthlyWorkingHour;
	}

	private int countNumberOfLeaveDays(UserInfo userInfo, Date dateFrom, Date dateTo) {
		int numberOfLeaveDays = 0;
		Optional<List<Leave>> usersAllLeaveList = Optional
				.ofNullable(leaveService.findByUserInfoAndEntryFromBetween(userInfo, dateFrom, dateTo));
		if (usersAllLeaveList.isPresent()) {
			List<Leave> regularLeaveList = usersAllLeaveList.get().stream()
					.filter(n -> n.getLeaveType().equals("regular")).collect(Collectors.toList());
			// get total leave days
			numberOfLeaveDays = regularLeaveList.stream().mapToInt(n -> n.getTotalLeaveDays()).sum();
			System.out.println("List size : " + usersAllLeaveList.get().size());
			System.out.println("numberOfDays : " + numberOfLeaveDays);
		}
		return numberOfLeaveDays;
	}

	private LoanPaidDetails getLoanPaidDetails(Loan loan, float finalSalary) {

		double installment = 0;// kisti
		int loanStatus = 1;// update status
		double checkLoanAmountIsLessThenOrGraterThen = 0;
		double extraMoney = 0;

		double loanAmountBasedOnLoanId = loan.getAmount();
		System.out.println("loanAmountBasedOnLoanId : " + loanAmountBasedOnLoanId);

		Optional<List<LoanPaidDetails>> loanPaidDetails = Optional.ofNullable(loanPaidDetailsService.findByLoan(loan));
		double paidAmount = 0;
		System.out.println("lpouter");
		if (loanPaidDetails.isPresent()) {
			System.out.println("lpinner");
			paidAmount = loanPaidDetails.get().stream().mapToDouble(n -> n.getPaidAmount()).sum();
		}

		if (loanAmountBasedOnLoanId > paidAmount) {
			// 81000 80000
			installment = (finalSalary * 5) / 100; // 5% 10_000
			checkLoanAmountIsLessThenOrGraterThen = paidAmount + installment; // 90_000
			if (loanAmountBasedOnLoanId <= checkLoanAmountIsLessThenOrGraterThen) {
				extraMoney = checkLoanAmountIsLessThenOrGraterThen - loanAmountBasedOnLoanId;// 9000
				installment = (checkLoanAmountIsLessThenOrGraterThen - extraMoney) - paidAmount; // 81_000 bdt
				loanStatus = 2;// update status
			}
		}

		if (loanStatus == 2) {
			loan.setStatus(loanStatus);
			loanService.save(loan);
		}
		System.out.println("pa : " + paidAmount);
		return LoanPaidDetails.builder().installmentDate(new Date()).loan(loan).installment((float) installment)
				.paidAmount((float) (paidAmount + installment)).build();

	}

	float countMonthlySalary(float totalsalary, float oneDaySalary, int monthlyWorkingHour) {
		if (monthlyWorkingHour >= 176) {
			return totalsalary;
			// 3 d
		} else if (monthlyWorkingHour > 152) {
			return totalsalary - oneDaySalary;
			// 6 d
		} else if (monthlyWorkingHour > 128) {
			return totalsalary - oneDaySalary * 2;
			// 9d
		} else if (monthlyWorkingHour > 104) {
			return totalsalary - oneDaySalary * 3;
			// 12d
		} else if (monthlyWorkingHour > 80) {
			return totalsalary - oneDaySalary * 4;
			// 15d
		} else if (monthlyWorkingHour > 56) {
			return totalsalary - oneDaySalary * 5;

		} else {
			return totalsalary * 0;
		}
	}

}
