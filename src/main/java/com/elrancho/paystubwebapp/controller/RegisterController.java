package com.elrancho.paystubwebapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.elrancho.paystubwebapp.entity.Employee;
import com.elrancho.paystubwebapp.entity.Users;
import com.elrancho.paystubwebapp.exception.EmployeeInactiveException;
import com.elrancho.paystubwebapp.exception.InvalidPasswordException;
import com.elrancho.paystubwebapp.service.EmployeeServiceImpl;
import com.elrancho.paystubwebapp.service.UserServiceImpl;


@RestController
public class RegisterController {
	
	
	@Autowired
	EmployeeServiceImpl esimpl;
	@Autowired
	UserServiceImpl usimpl;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//to get activeEmployee details
	@CrossOrigin(origins = "http://ec2-3-90-133-23.compute-1.amazonaws.com:8080")   
	@GetMapping(path = "/employee/{employeeId}", produces = { MediaType.APPLICATION_JSON_VALUE,
				MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
						MediaType.APPLICATION_XML_VALUE })
	  public Employee getEmployee(@PathVariable int employeeId) {
		  Employee activeEmployee;
		  Boolean isActive = esimpl.activeEmployeeCheck(employeeId);
		  
		  if(isActive==true)
			  activeEmployee = esimpl.getEmployeeDetails(employeeId);
		  else
			  throw new EmployeeInactiveException("Employee id "+employeeId +" is inactive");
		
		  return activeEmployee;
		  		  
	  }
	  
	  //how to validate security questions while registering user?
	  @CrossOrigin(origins = "http://ec2-3-90-133-23.compute-1.amazonaws.com:8080") 
	  @PostMapping("/users/new")
	  public void registerUser(@RequestBody Users user) {
 
			 if(usimpl.passwordValidator(user.getPassword())==false) {
				 
			  //password encryption
			  String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
			  System.out.println("encodedPassword "+encodedPassword);

		     //setting encrypted password to user
			   user.setPassword(encodedPassword);
			   
		     //registering new user in users table
			   usimpl.registerUser(user);
			 }
			 else
			 {
				 throw new  InvalidPasswordException("Please choose a different password");
			 }
		   
		  }
	  

}
