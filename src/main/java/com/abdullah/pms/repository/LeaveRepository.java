package com.abdullah.pms.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.abdullah.pms.domain.Leave;
import com.abdullah.pms.domain.UserInfo;

public interface LeaveRepository extends JpaRepository<Leave, Integer>{

	@Query("SELECT a FROM Leave a WHERE a.status = :status AND a.userInfo = :userInfo")
    List<Leave> leaveInfo(@Param("status") int status, @Param("userInfo") UserInfo userInfo );

	List<Leave> findByStatus(int i);

//	@Query("SELECT a FROM Leave a WHERE  a.entryFrom BETWEEN  :dateFrom  AND :dateTo ")
//	List<Leave> findByUserInfoIdBetween( Date dateFrom, Date dateTo);

	List<Leave> findByUserInfoAndEntryFromBetween(UserInfo userInfo, Date startDate, Date endDate);
	
}
