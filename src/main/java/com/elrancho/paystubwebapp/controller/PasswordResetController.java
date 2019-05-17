package com.elrancho.paystubwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.elrancho.paystubwebapp.entity.Employee;
import com.elrancho.paystubwebapp.entity.Users;
import com.elrancho.paystubwebapp.exception.InvalidPasswordException;
import com.elrancho.paystubwebapp.exception.InvalidSecurityQuestionException;
import com.elrancho.paystubwebapp.service.EmployeeServiceImpl;
import com.elrancho.paystubwebapp.service.UserServiceImpl;

@RestController
public class PasswordResetController {

	
	@Autowired
	EmployeeServiceImpl esimpl;
	@Autowired
	UserServiceImpl usimpl;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	
	
	//dob & ssn validation
	@CrossOrigin(origins = "http://ec2-3-90-133-23.compute-1.amazonaws.com:8080") 
	@PostMapping("/pwdReset/securityQuestion")
	public HttpStatus securityQnValidator(@RequestBody Employee e) {
		
		//validating security questions- dob and ssn 4 digits
		  boolean securityQuestionValid = esimpl.securityQuestionCheck(e.getBirthDate(), e.getSsn());
		  System.out.println("securityQuestionValid "+securityQuestionValid);
		
		  if(securityQuestionValid==true) {
			  return HttpStatus.OK;
		  }
		  
		  else {
			  throw new InvalidSecurityQuestionException("Please correct your date of birth or SSN");
		  }
	}
	
	//for password reset
	  @CrossOrigin(origins = "http://ec2-3-90-133-23.compute-1.amazonaws.com:8080") 
	  @PutMapping("/users/updatePwd")
	  public void passwordReset(@RequestBody Users user) {
		  if(usimpl.passwordValidator(user.getPassword())==false) {
				 
			  //password encryption
			  String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
			  System.out.println("encodedPassword "+encodedPassword);

		     //setting encrypted password to user
			   user.setPassword(encodedPassword);
			   
		     //updating new password in users table
			   usimpl.registerUser(user);
			 }
			 else
			 {
				 throw new  InvalidPasswordException("Please choose a different password");
			 }
		   
		  }
		  
}
	

