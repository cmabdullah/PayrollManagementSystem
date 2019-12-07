package com.abdullah.pms.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.abdullah.pms.domain.Attendance;
import com.abdullah.pms.domain.UserInfo;


public interface AttendanceRepository extends JpaRepository<Attendance, Long>{
	List<Attendance> findAll();
	//logintime", logintime + "%"
	//SELECT * FROM attendence where logintime like  :logintime AND userinfo_id=:userinfo_id AND logouttime IS NULL
	//@Query("SELECT u FROM User u WHERE u.status = :status and u.name = :name")
	@Query("SELECT a FROM Attendance a WHERE a.loginDate like :loginDate AND a.userInfo = :userInfo  ")
	// 
    List<Attendance> retrieveByTodaysLogin(@Param("loginDate") LocalDate loginDate, @Param("userInfo") UserInfo userInfo );
	
	Optional<Attendance> findById(int id);
	void deleteById(int id);
	List<Attendance> findByUserInfoAndLoginDateBetween(UserInfo userInfo, LocalDate dateFrom, LocalDate dateTo);
}
