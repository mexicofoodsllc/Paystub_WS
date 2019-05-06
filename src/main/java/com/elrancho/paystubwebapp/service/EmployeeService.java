package com.elrancho.paystubwebapp.service;


import java.time.LocalDate;
import java.util.List;

import com.elrancho.paystubwebapp.entity.Employee;


public interface EmployeeService {

	public boolean activeEmployeeCheck(int empid);
	Employee getEmployeeDetails(int empid);
	public boolean securityQuestionCheck(LocalDate dob, String ssn);
	public int getEmpIdWithDobSsn(LocalDate dob, String ssn);
	//public boolean isEmployee(int empid);

	
	
	
}
