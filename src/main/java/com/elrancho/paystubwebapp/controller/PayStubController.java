package com.elrancho.paystubwebapp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.elrancho.paystubwebapp.entity.Paystub;
import com.elrancho.paystubwebapp.service.PaystubServiceImpl;
import com.elrancho.paystubwebapp.service.UserServiceImpl;


@RestController
public class PayStubController {

	@Autowired
	PaystubServiceImpl psimpl;
	
	@Autowired
	UserServiceImpl usrimpl;

	@CrossOrigin(origins = "http://ec2-3-90-133-23.compute-1.amazonaws.com:8080")  
	@GetMapping(path = "/paystubs/{password}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public List<Paystub> getPaystub(@PathVariable String password) {
			
			   int employeeId = usrimpl.getEmpId(password);
		  
			   List<Paystub> paystubList = psimpl.getAllPaystubs(employeeId);
			   
			  return paystubList;
		   }
	
	  	 
	@CrossOrigin(origins = "http://ec2-3-90-133-23.compute-1.amazonaws.com:8080") 
	@GetMapping(path = "/paystubs/{password}/{Date}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
   public List<Paystub> getPaystubByDate(@PathVariable String password, @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate Date) {
	  
		int employeeId = usrimpl.getEmpId(password);
	   List<Paystub> paystubList= psimpl.findPaystubDetails(Date, employeeId);
 
		  return paystubList;
	   }
  
	 
  
    
  
}

