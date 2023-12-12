package com.dev_amit.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev_amit.model.Employee;
import com.dev_amit.repository.EmployeeRepository;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	// for Post-request RestApi
	@PostMapping("/employees")
	public String createNewEmployee(@RequestBody Employee employee) {
		
		employeeRepository.save(employee);
		return "Employee Created in database";
	}
	
	
		
	// for Get-request REST-API
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees(){
		List<Employee>  empList = new ArrayList<>();
		employeeRepository.findAll().forEach(empList::add);
		return new ResponseEntity<List<Employee>>(empList,HttpStatus.OK);
		
		
	}
	
	// for Get-request REST-API byId to all EmployeeId
	
	@GetMapping("/employees/{empid}")
	public ResponseEntity<Employee>getEmployeeById(@PathVariable long empid){
Optional<Employee>	emp = employeeRepository.findById(empid);
if(emp.isPresent()) {
	return new ResponseEntity<Employee>(emp.get(),HttpStatus.FOUND);
	
}else {
	return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
}
	}
		
	// for Put-request REST-APIS
@PutMapping("/employees/{empid}")
public String updateEmployeeById(@PathVariable long empid,@RequestBody Employee employee) {
 Optional<Employee> emp= employeeRepository.findById(empid);
 if(emp.isPresent()) {
	 Employee existEmp = emp.get();
	 existEmp.setEmp_age(employee.getEmp_age());
	 existEmp.setEmp_city(employee.getEmp_city());
	 existEmp.setEmp_name(employee.getEmp_name());
	 existEmp.setEmp_salary(employee.getEmp_salary());
	 employeeRepository.save(existEmp);
	 return "Employee Details against Id" + empid + "updated";
 }else{
	 
	 return "Employee Details does not exist for empid " + empid;
 
	 
 }
	}
// for Delete -request REST-APIS BYId
@DeleteMapping("/employees/{empid}")
public String deleteEmployeeById(@PathVariable Long empid) {
	employeeRepository.deleteById(empid);
	return "Employee Deleted Successfully";
	
}

//for Delete -request REST-API

@DeleteMapping("/employees")
public String deleteAllEmployee() {
	employeeRepository.deleteAll();
	return "Employee deleted Successfully...";
}
	
		}

