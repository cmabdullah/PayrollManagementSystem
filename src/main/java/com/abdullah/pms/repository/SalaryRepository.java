package com.abdullah.pms.repository;

import org.springframework.data.repository.CrudRepository;

import com.abdullah.pms.domain.Salary;


public interface SalaryRepository extends CrudRepository<Salary, Integer>{
}
