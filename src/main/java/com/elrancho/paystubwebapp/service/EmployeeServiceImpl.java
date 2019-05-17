package com.elrancho.paystubwebapp.service;


import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elrancho.paystubwebapp.entity.Employee;
import com.elrancho.paystubwebapp.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public Employee getEmployeeDetails(int empid) {
		
		Employee emp = employeeRepository.findByEmployeeId(empid);
		 
		return emp;
	}
	
	//checking if an employee is active by 
	@Override
	public boolean activeEmployeeCheck(int empid) {
		String status = null;
		boolean isActive=true;
		
		Employee emp = employeeRepository.findByEmployeeId(empid);
		status = emp.getStatus();
		if(status.equalsIgnoreCase("A")) //comparing with active flag "A" from employee table
				isActive=true;	
			else
				isActive=false;
		//System.out.println("isActive"+isActive);
		return isActive;
	}
	

	@Override
	public boolean securityQuestionCheck(LocalDate dob, String ssn) {
		boolean isSecurityQuestionTrue= false;
			
		Optional<Employee> emp = employeeRepository.findByBirthDateAndSsn(dob, ssn);
		if(emp.isPresent()) {
			isSecurityQuestionTrue = true;
		}
		else {
			isSecurityQuestionTrue= false;
		}
		
		return isSecurityQuestionTrue;
	}

	@Override
	public int getEmpIdWithDobSsn(LocalDate dob, String ssn) {
		/*List<Employee> emp = employeeRepository.findByBirthDateAndSsn(dob, ssn);
		int empId = 0;
		for(Employee e: emp) {
			if((e.getBirthDate().equals(dob))&& (e.getSsn().equals(ssn))){
					empId = e.getEmployeeId();
				}
			}*/
		Optional<Employee> emp = employeeRepository.findByBirthDateAndSsn(dob, ssn);
		int empId = 0;
		if(securityQuestionCheck(dob,ssn)==true) {
			empId = emp.get().getEmployeeId();
			System.out.println("empId "+empId);
		}
		return empId;
	}



}
