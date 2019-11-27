package com.abdullah.pms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdullah.pms.domain.Grade;
import com.abdullah.pms.repository.GradeRepository;
import com.abdullah.pms.service.GradeService;

@Service
public class GradeServiceImpl implements GradeService{
	
	@Autowired
	GradeRepository gradeRepository;

	@Override
	public List<Grade> findAll() {
		
		return gradeRepository.findAll();
	}
	
	
}
