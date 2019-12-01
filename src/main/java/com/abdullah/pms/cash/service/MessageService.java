package com.abdullah.pms.cash.service;

import com.abdullah.pms.domain.Leave;
import com.abdullah.pms.domain.UserInfo;

public interface MessageService {

	void postLeaveAcceptionMessage(Leave leavesaveRes);

	String getPendingLeaveMessage(UserInfo userInfo, String mapkey);

}
