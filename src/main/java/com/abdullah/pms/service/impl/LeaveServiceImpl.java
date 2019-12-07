package com.abdullah.pms.service.impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdullah.pms.domain.Leave;
import com.abdullah.pms.domain.UserInfo;
import com.abdullah.pms.repository.LeaveRepository;
import com.abdullah.pms.service.LeaveService;

@Service
public class LeaveServiceImpl implements LeaveService {

	@Autowired
	LeaveRepository leaveRepository;

	@Override
	public Leave postLeaveApplication(@Valid Leave leave, LocalDate entryFrom, LocalDate entryTo, UserInfo userInfo) {
		leave.setStatus(0);
		leave.setUserInfo(userInfo);

		int totalLeaveDays = 1;
		while (entryFrom.isBefore(entryTo)) {
			System.out.println("FFFF");
			totalLeaveDays++;
			entryFrom = entryFrom.plusDays(1);
			System.out.println(entryFrom + " : " + entryTo);
		}
		leave.setTotalLeaveDays(totalLeaveDays);
		return leaveRepository.save(leave);
	}

	@Override
	public boolean isPandingRequest(UserInfo userInfo) {
		int status = 0;// for pending status
		Optional<Leave> leave = leaveRepository.leaveInfo(status, userInfo).stream().findAny();
		return leave.isPresent();
	}

	@Override
	public List<Leave> getAllLeaveRequests() {
		List<Leave> leaveList = leaveRepository.findByStatus(0);

		// if (leaveList != null)
//			leaveList.stream().forEach(n -> System.out.println("Pending list" + n.toString()));
		return leaveList;
	}

	@Override
	public Optional<Leave> findById(int id) {
		return leaveRepository.findById(id);
	}

	@Override
	public Leave save(Leave leave) {
		return leaveRepository.save(leave);
	}

	@Override
	public List<Leave> findByUserInfoAndEntryFromBetween(UserInfo userInfo, Date dateFrom, Date dateTo) {
		System.out.println(userInfo.toString());
		System.out.println(dateFrom);
		System.out.println(dateTo);

		List<Leave> list = leaveRepository.findByUserInfoAndEntryFromBetween(userInfo, dateFrom, dateTo);
//		List<Leave> list = leaveRepository.findByUserInfo(userInfo);
		// List<Leave> list = leaveRepository.findByUserInfoIdBetween( dateFrom,
		// dateTo);
		return list;
	}

}
