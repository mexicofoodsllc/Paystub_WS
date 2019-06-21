package com.elrancho.paystubwebapp.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.elrancho.paystubwebapp.entity.Paystub;
import com.elrancho.paystubwebapp.service.DbaServiceImpl;
import com.elrancho.paystubwebapp.service.PaystubServiceImpl;
import com.elrancho.paystubwebapp.service.UserServiceImpl;
import com.elrancho.paystubwebapp.util.PaystubUtil;


@RestController
public class PayStubController {

	@Autowired
	PaystubServiceImpl psimpl;
	
	@Autowired
	UserServiceImpl usrimpl;
	
	@Autowired
	PaystubUtil psutil;
	
	@Autowired
	DbaServiceImpl dbaimpl;
	
	

	//service to return all paystub details for the employee
	@CrossOrigin(origins = "http://ec2-3-90-133-23.compute-1.amazonaws.com:8080")  
	@GetMapping(path = "/paystubs/{password}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public List<Paystub> getPaystub(@PathVariable String password) {
			
			   int employeeId = usrimpl.getEmpId(password);

			   System.out.println("Reached pastub controller");
			   System.out.println(employeeId);
			   // list of paystubs for employee with id employeeId
			   List<Paystub> paystubList = psimpl.getAllPaystubs(employeeId);
			   			   
			  return paystubList;
		   }
	
	
	//service to return List of gross Pays for the employee
	@CrossOrigin(origins = "http://ec2-3-90-133-23.compute-1.amazonaws.com:8080")  
	@GetMapping(path = "/paystubs/grossPays/{employeeId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public List<String> getGrossPayList(@PathVariable int employeeId) {
			
			   //int employeeId = usrimpl.getEmpId(password);

			   
			   // list of paystubs for employee with id employeeId
			   List<Paystub> paystubList = psimpl.getAllPaystubs(employeeId);
			   
			   //set containing unique dates from paystub list
			   Set<LocalDate> dateSet = psutil.getDates(paystubList);
			   

			   //grossPayList has all the grossPays in the table
			   List<String> grossPayList = new ArrayList<String>();
			   for(LocalDate d:dateSet) {
				 
				   grossPayList.add(psutil.grossPayGenerator(d,employeeId));   
			   }
			   	System.out.println("grossPayList "+grossPayList);
			    
			  return grossPayList;
		   }
	
	
	//service to return Set of pay period end dates for the employee
	@CrossOrigin(origins = "http://ec2-3-90-133-23.compute-1.amazonaws.com:8080")  
	@GetMapping(path = "/paystubs/dates/{employeeId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public Set<LocalDate> getDateSet(@PathVariable int employeeId) {
			
			   //int employeeId = usrimpl.getEmpId(password);

			   
			   // list of paystubs for employee with id employeeId
			   List<Paystub> paystubList = psimpl.getAllPaystubs(employeeId);
			   
			   //set containing unique dates from paystub list
			   Set<LocalDate> dateSet = psutil.getDates(paystubList);
 
			  return dateSet;
		   }
	
	
	//service to return List of net Pays for the employee
	@CrossOrigin(origins = "http://ec2-3-90-133-23.compute-1.amazonaws.com:8080")  
	@GetMapping(path = "/paystubs/netPays/{employeeId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public List<String> getNetPayList(@PathVariable int employeeId) {
			
			  // int employeeId = usrimpl.getEmpId(password);

			   
			   // list of paystubs for employee with id employeeId
			   List<Paystub> paystubList = psimpl.getAllPaystubs(employeeId);
			   
			   //set containing unique dates from paystub list
			   Set<LocalDate> dateSet = psutil.getDates(paystubList);

		   
			   List<String> netPayList = new ArrayList<String>();
		  
			   for(LocalDate d:dateSet) {
			   
				   netPayList.add(psutil.netPayGenerator(d,employeeId));
			   }
			   
			  return netPayList;
		   }
	
 
	//service to return List of total hours for the employee
	@CrossOrigin(origins = "http://ec2-3-90-133-23.compute-1.amazonaws.com:8080")  
	@GetMapping(path = "/paystubs/hours/{employeeId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public List<Integer> getTotalHoursList(@PathVariable int employeeId) {
			
			  // int employeeId = usrimpl.getEmpId(password);

			   
			   // list of paystubs for employee with id employeeId
			   List<Paystub> paystubList = psimpl.getAllPaystubs(employeeId);
			   
			   //set containing unique dates from paystub list
			   Set<LocalDate> dateSet = psutil.getDates(paystubList);

		   
			   List<Integer> hoursList = new ArrayList<Integer>();
			  
			  for(LocalDate d:dateSet) {
				hoursList.add(psutil.totalHoursGenerator(d,employeeId));
				   
			   }
			   
			  return hoursList;
		   }
	
	@CrossOrigin(origins = "http://ec2-3-90-133-23.compute-1.amazonaws.com:8080") 
	@GetMapping(path = "/earnings/{employeeId}/{Date}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
   public List<Paystub> getEarningsByDate(@PathVariable int employeeId, @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate Date) {
	  
		//int employeeId = usrimpl.getEmpId(password);
	   List<Paystub> paystubList= psimpl.findPaystubDetails(Date, employeeId);
	   
	   List<Paystub> earningsList = new ArrayList<Paystub>();
	   
	   
	   for(Paystub p: paystubList) {
		   int dbaCode = p.getDbaCode();
		   if(dbaimpl.getDbaDesciption(dbaCode).contains("Earnings")) {
			   earningsList.add(p);
		   }
	   }
 
		  return earningsList;
	   }
  
	 
	
	@CrossOrigin(origins = "http://ec2-3-90-133-23.compute-1.amazonaws.com:8080") 
	@GetMapping(path = "/deductions/{employeeId}/{Date}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
   public List<Paystub> getDeductionsByDate(@PathVariable int employeeId, @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate Date) {
	  
		//int employeeId = usrimpl.getEmpId(password);
	   List<Paystub> paystubList= psimpl.findPaystubDetails(Date, employeeId);
	   
	   List<Paystub> deductionsList = new ArrayList<Paystub>();
	   
	   
	   for(Paystub p: paystubList) {
		   int dbaCode = p.getDbaCode();
		   if(dbaimpl.getDbaDesciption(dbaCode).contains("Deductions")) {
			   deductionsList.add(p);
		   }
	   }
 
		  return deductionsList;
	   }
  
  
	@CrossOrigin(origins = "http://ec2-3-90-133-23.compute-1.amazonaws.com:8080") 
	@GetMapping(path = "/grossNetDed/{employeeId}/{Date}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
   public Map<String, String> getGrossNetPayByDate(@PathVariable int employeeId, @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate Date) {
	  
		//int employeeId = usrimpl.getEmpId(password);
	   List<Paystub> paystubList= psimpl.findPaystubDetails(Date, employeeId);
	   
	   String netPay = psutil.netPayGenerator(Date, employeeId);
	   
	   String grossPay = psutil.grossPayGenerator(Date, employeeId);
	   
	   String deduction = psutil.deductionsGenerator(Date, employeeId);
	   
	   Map<String, String> grossNetDed = new HashMap<String, String>();
	   grossNetDed.put("grossPay", grossPay);
	   grossNetDed.put("netPay", netPay);
	   grossNetDed.put("deduction",deduction);
	   
 
		  return grossNetDed;
	   }
  
  
}

