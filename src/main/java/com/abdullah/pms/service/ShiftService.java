package com.abdullah.pms.service;

import java.util.List;
import java.util.Optional;

import com.abdullah.pms.domain.Shift;

public interface ShiftService {
	List<Shift> findAll();

	Optional<Shift> findById(int id);
}
