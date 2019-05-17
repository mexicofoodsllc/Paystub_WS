package com.elrancho.paystubwebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.elrancho.paystubwebapp.entity.Employee;
import com.elrancho.paystubwebapp.entity.Paystub;
import com.elrancho.paystubwebapp.exception.EmployeeInactiveException;
import com.elrancho.paystubwebapp.service.EmployeeServiceImpl;
import com.elrancho.paystubwebapp.service.UserServiceImpl;



@RestController
public class EmployeeController {
	
	@Autowired
	UserServiceImpl usimpl;
	
	@Autowired
	EmployeeServiceImpl esimpl;
	
	@CrossOrigin(origins = "http://ec2-3-90-133-23.compute-1.amazonaws.com:8080")   
	@GetMapping(path = "/employee/{password}", produces = { MediaType.APPLICATION_JSON_VALUE,
				MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
						MediaType.APPLICATION_XML_VALUE })
	
	  public Employee getEmployee(@PathVariable String password) {
		  System.out.println("Reached employeecontroller");
		  int employeeId = usimpl.getEmpId(password);
		  Employee activeEmployee;
		  
		  Boolean isActive = esimpl.activeEmployeeCheck(employeeId);
		  
		  if(isActive==true)
			  activeEmployee = esimpl.getEmployeeDetails(employeeId);
		  else
			  throw new EmployeeInactiveException("Employee id "+employeeId +" is inactive");
		
		  return activeEmployee;
		  		  
	  }
}
