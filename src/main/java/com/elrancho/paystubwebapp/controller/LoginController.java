package com.elrancho.paystubwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elrancho.paystubwebapp.entity.Users;
import com.elrancho.paystubwebapp.service.UserServiceImpl;

@RestController
public class LoginController {
	
	@Autowired
	UserServiceImpl usimpl;
	
	//when "/login" is posted, 
//	@PostMapping(path = "/login", produces = { MediaType.APPLICATION_JSON_VALUE,
//			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
//					MediaType.APPLICATION_XML_VALUE })
//	public void login(Users user) {
//		String password = user.getPassword();
//		int empId = usimpl.getEmpId(password);
//		user.setEmployeeId(empId);
//		
//	}
}
