package com.dev_amit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev_amit.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long>{

}
