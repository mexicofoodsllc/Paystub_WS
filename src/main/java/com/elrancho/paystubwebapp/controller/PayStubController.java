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
@CrossOrigin
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
	
	@GetMapping(path = "/paystubs/{password}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public List<Paystub> getPaystub(@PathVariable String password) {
			
			   int employeeId = usrimpl.getEmpId(password);

			   // list of paystubs for employee with id employeeId
			   List<Paystub> paystubList = psimpl.getAllPaystubs(employeeId);
			   			   
			  return paystubList;
		   }
	
	
	//service to return List of check Numbers for the employee
	
		@GetMapping(path = "/paystubs/checkNum/{employeeId}", produces = { MediaType.APPLICATION_JSON_VALUE,
				MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
						MediaType.APPLICATION_XML_VALUE })
		public Set<Integer> checkNumList(@PathVariable int employeeId) {
				
				   //int employeeId = usrimpl.getEmpId(password);

				   
				   // list of paystubs for employee with id employeeId
				   List<Paystub> paystubList = psimpl.getAllPaystubs(employeeId);
				   
				   
				   //map containing check control num as key and pay period end date as value
				   Map<Integer, LocalDate> checkDateMap = psutil.getDates(paystubList);
 
				  return checkDateMap.keySet();
			   }
		
		
	
	//service to return List of gross Pays for the employee
	
	@GetMapping(path = "/paystubs/grossPays/{employeeId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public List<String> getGrossPayList(@PathVariable int employeeId) {
			
			   //int employeeId = usrimpl.getEmpId(password);

			   
			   // list of paystubs for employee with id employeeId
			   List<Paystub> paystubList = psimpl.getAllPaystubs(employeeId);
			   
			   
			   //map containing check control num as key and pay period end date as value
			   Map<Integer, LocalDate> checkDateMap = psutil.getDates(paystubList);

			  //grossPayList has all the grossPays in the table
			   List<String> grossPayList = new ArrayList<String>();
			  
			   for(int checkNum:checkDateMap.keySet()) {
				   grossPayList.add(psutil.grossPayGenerator(checkNum,employeeId));   
			   }
			   
			  return grossPayList;
		   }
	
	
	//service to return Set of pay period end dates for the employee

	@GetMapping(path = "/paystubs/dates/{employeeId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public List<LocalDate> getDateSet(@PathVariable int employeeId) {
			
			   //int employeeId = usrimpl.getEmpId(password);

			   
			   // list of paystubs for employee with id employeeId
			   List<Paystub> paystubList = psimpl.getAllPaystubs(employeeId);
			   
			   //map containing check control num as key and pay period end date as value
			   Map<Integer, LocalDate> checkDateMap = psutil.getDates(paystubList);
			   
			   //System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ "+checkDateMap);
			   
			   List<LocalDate> dateListDesc = new ArrayList<LocalDate>();
			   
			   for(LocalDate d: checkDateMap.values()) {
				   dateListDesc.add(d);
			   }
			   
			  return dateListDesc;
		   }
	
	
	//service to return List of net Pays for the employee

	@GetMapping(path = "/paystubs/netPays/{employeeId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public List<String> getNetPayList(@PathVariable int employeeId) {
			
			  // int employeeId = usrimpl.getEmpId(password);

			   
			   // list of paystubs for employee with id employeeId
			   List<Paystub> paystubList = psimpl.getAllPaystubs(employeeId);
			   
			   //map containing check control num as key and pay period end date as value
			   Map<Integer, LocalDate> checkDateMap = psutil.getDates(paystubList);

		   
			   List<String> netPayList = new ArrayList<String>();
		  
			   for(int checkNum:checkDateMap.keySet()) {
			   
				   netPayList.add(psutil.netPayGenerator(checkNum,employeeId));
			   }
			   
			  return netPayList;
		   }
	
 
	//service to return List of total hours for the employee

	@GetMapping(path = "/paystubs/hours/{employeeId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public List<Integer> getTotalHoursList(@PathVariable int employeeId) {
			
			  // int employeeId = usrimpl.getEmpId(password);

			   
			   // list of paystubs for employee with id employeeId
			   List<Paystub> paystubList = psimpl.getAllPaystubs(employeeId);
			   
			   //map containing check control num as key and pay period end date as value
			   Map<Integer, LocalDate> checkDateMap = psutil.getDates(paystubList);

		   
			   List<Integer> hoursList = new ArrayList<Integer>();
			  
			  for(int checkNum:checkDateMap.keySet()) {
				hoursList.add(psutil.totalHoursGenerator(checkNum,employeeId));
				   
			   }
			   
			  return hoursList;
		   }
	

	@GetMapping(path = "/earnings/{employeeId}/{checkNum}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
   public List<Paystub> getEarningsByDate(@PathVariable int employeeId, @PathVariable int checkNum) {
	  
		//int employeeId = usrimpl.getEmpId(password);
	   List<Paystub> paystubList= psimpl.findPaystubDetails(checkNum, employeeId);
	   
	   List<Paystub> earningsList = new ArrayList<Paystub>();
	   
	   
	   for(Paystub p: paystubList) {
		   int dbaCode = p.getDbaCode();
		   if(dbaimpl.getDbaDesciption(dbaCode).contains("Earnings")) {
			   earningsList.add(p);
		   }
	   }
 
		  return earningsList;
	   }
  
	 
	

	@GetMapping(path = "/deductions/{employeeId}/{checkNum}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
   public List<Paystub> getDeductionsByDate(@PathVariable int employeeId, @PathVariable int checkNum) {
	  
		//int employeeId = usrimpl.getEmpId(password);
	   List<Paystub> paystubList= psimpl.findPaystubDetails(checkNum, employeeId);
	   
	   List<Paystub> deductionsList = new ArrayList<Paystub>();
	   
	   
	   for(Paystub p: paystubList) {
		   int dbaCode = p.getDbaCode();
		   if(dbaimpl.getDbaDesciption(dbaCode).contains("Deductions")) {
			   deductionsList.add(p);
		   }
	   }
 
		  return deductionsList;
	   }
  
  
	
	@GetMapping(path = "/grossNetDed/{employeeId}/{checkNum}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
   public Map<String, String> getGrossNetPayByDate(@PathVariable int employeeId, @PathVariable int checkNum) {
	  
		//int employeeId = usrimpl.getEmpId(password);
	  // List<Paystub> paystubList= psimpl.findPaystubDetails(Date, employeeId);
	   
	   String netPay = psutil.netPayGenerator(checkNum, employeeId);
	   
	   String grossPay = psutil.grossPayGenerator(checkNum, employeeId);
	   
	   String deduction = psutil.deductionsGenerator(checkNum, employeeId);
	   
	   Map<String, String> grossNetDed = new HashMap<String, String>();
	   grossNetDed.put("grossPay", grossPay);
	   grossNetDed.put("netPay", netPay);
	   grossNetDed.put("deduction",deduction);
	   
 
		  return grossNetDed;
	   }
  
  
}

