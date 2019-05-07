package com.elrancho.paystubwebapp.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import com.elrancho.paystubwebapp.util.PaystubUtil;


@RestController
public class PayStubController {

	@Autowired
	PaystubServiceImpl psimpl;
	
	@Autowired
	UserServiceImpl usrimpl;
	
	@Autowired
	PaystubUtil psutil;

	//service to return all paystub details for the employee
	@CrossOrigin(origins = "http://ec2-3-90-133-23.compute-1.amazonaws.com:8080")  
	@GetMapping(path = "/paystubs/{password}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public List<Paystub> getPaystub(@PathVariable String password) {
			
			   int employeeId = usrimpl.getEmpId(password);

			   
			   // list of paystubs for employee with id employeeId
			   List<Paystub> paystubList = psimpl.getAllPaystubs(employeeId);
			   
			   //set containing unique dates from paystub list
			   Set<LocalDate> dateSet = psutil.getDates(paystubList);


			   //grossPayList has all the grossPays in the table
			   List<Float> grossPayList = new ArrayList<Float>();
			   for(LocalDate d:dateSet) {
				 
				   grossPayList.add(psutil.grossPayGenerator(d,employeeId));   
			   }
		  
		   
			   List<Float> netPayList = new ArrayList<Float>();
		  
			   for(LocalDate d:dateSet) {
			   
				   netPayList.add(psutil.netPayGenerator(d,employeeId));
			   }

		   
			   List<Integer> hoursList = new ArrayList<Integer>();
			  
			  for(LocalDate d:dateSet) {
				hoursList.add(psutil.totalHoursGenerator(d,employeeId));
				   
			   }
			   
			  return paystubList;
		   }
	
	
	//service to return List of gross Pays for the employee
	@CrossOrigin(origins = "http://ec2-3-90-133-23.compute-1.amazonaws.com:8080")  
	@GetMapping(path = "/paystubs/grossPays/{password}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public List<Float> getGrossPayList(@PathVariable String password) {
			
			   int employeeId = usrimpl.getEmpId(password);

			   
			   // list of paystubs for employee with id employeeId
			   List<Paystub> paystubList = psimpl.getAllPaystubs(employeeId);
			   
			   //set containing unique dates from paystub list
			   Set<LocalDate> dateSet = psutil.getDates(paystubList);
			   

			   //grossPayList has all the grossPays in the table
			   List<Float> grossPayList = new ArrayList<Float>();
			   for(LocalDate d:dateSet) {
				 
				   grossPayList.add(psutil.grossPayGenerator(d,employeeId));   
			   }
	  
			  return grossPayList;
		   }
	
	
	//service to return Set of pay period end dates for the employee
	@CrossOrigin(origins = "http://ec2-3-90-133-23.compute-1.amazonaws.com:8080")  
	@GetMapping(path = "/paystubs/dates/{password}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public Set<LocalDate> getDateSet(@PathVariable String password) {
			
			   int employeeId = usrimpl.getEmpId(password);

			   
			   // list of paystubs for employee with id employeeId
			   List<Paystub> paystubList = psimpl.getAllPaystubs(employeeId);
			   
			   //set containing unique dates from paystub list
			   Set<LocalDate> dateSet = psutil.getDates(paystubList);
 
			  return dateSet;
		   }
	
	
	//service to return List of net Pays for the employee
	@CrossOrigin(origins = "http://ec2-3-90-133-23.compute-1.amazonaws.com:8080")  
	@GetMapping(path = "/paystubs/netPays/{password}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public List<Float> getNetPayList(@PathVariable String password) {
			
			   int employeeId = usrimpl.getEmpId(password);

			   
			   // list of paystubs for employee with id employeeId
			   List<Paystub> paystubList = psimpl.getAllPaystubs(employeeId);
			   
			   //set containing unique dates from paystub list
			   Set<LocalDate> dateSet = psutil.getDates(paystubList);

		   
			   List<Float> netPayList = new ArrayList<Float>();
		  
			   for(LocalDate d:dateSet) {
			   
				   netPayList.add(psutil.netPayGenerator(d,employeeId));
			   }
			   
			  return netPayList;
		   }
	
 
	//service to return List of total hours for the employee
	@CrossOrigin(origins = "http://ec2-3-90-133-23.compute-1.amazonaws.com:8080")  
	@GetMapping(path = "/paystubs/hours/{password}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public List<Integer> getTotalHoursList(@PathVariable String password) {
			
			   int employeeId = usrimpl.getEmpId(password);

			   
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
	@GetMapping(path = "/paystubs/{password}/{Date}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
   public List<Paystub> getPaystubByDate(@PathVariable String password, @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate Date) {
	  
		int employeeId = usrimpl.getEmpId(password);
	   List<Paystub> paystubList= psimpl.findPaystubDetails(Date, employeeId);
 
		  return paystubList;
	   }
  
	 
  
    
  
}

