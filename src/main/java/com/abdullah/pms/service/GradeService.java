package com.abdullah.pms.service;

import java.util.List;
import java.util.Optional;

import com.abdullah.pms.domain.Grade;

public interface GradeService {

	List<Grade> findAll();

	Optional<Grade> findById(int id);
}
