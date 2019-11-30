package com.abdullah.pms.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.abdullah.pms.domain.Leave;
import com.abdullah.pms.domain.UserInfo;

public interface LeaveService {

	Leave postLeaveApplication(@Valid Leave leave, LocalDate entryFrom, LocalDate entryTo, UserInfo userInfo);

	boolean isPandingRequest(UserInfo userInfo);

	List<Leave> getAllLeaveRequests();

	Optional<Leave> findById(int id);

	Leave save(Leave leave);

}
