package com.abdullah.pms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdullah.pms.domain.Shift;
import com.abdullah.pms.repository.ShiftRepository;
import com.abdullah.pms.service.ShiftService;

@Service
public class ShiftServiceImpl implements ShiftService{

	@Autowired
	ShiftRepository shiftRepository;
	
	@Override
	public List<Shift> findAll() {
		return shiftRepository.findAll();
	}

}
