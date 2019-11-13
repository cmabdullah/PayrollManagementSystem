package com.abdullah.pms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.abdullah.pms.domain.Attendance;


public interface AttendanceRepository extends CrudRepository<Attendance, Long>{
	List<Attendance> findAll();
	Optional<Attendance> findById(int id);
	void deleteById(int id);
}
