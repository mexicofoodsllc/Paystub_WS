package com.elrancho.paystubwebapp.util;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elrancho.paystubwebapp.entity.Paystub;
import com.elrancho.paystubwebapp.service.DbaServiceImpl;
import com.elrancho.paystubwebapp.service.PaystubServiceImpl;



@Service
public class PaystubUtil {
	
	@Autowired
	PaystubServiceImpl psimpl;
	@Autowired
	DbaServiceImpl dbaimpl;

	//making any date user has selected to the next saturday
	
	public LocalDate dayConverter(LocalDate datePicker) {
			   
			   LocalDate saturdayDatepicker2=null;
			   String Day=datePicker.getDayOfWeek().name();
			   
			   switch(Day) {
			   
			   case "SUNDAY":saturdayDatepicker2 = datePicker.plusDays(6);
			   break;
			   case "MONDAY":saturdayDatepicker2 = datePicker.plusDays(5);
			   break;
			   case "TUESDAY":saturdayDatepicker2 = datePicker.plusDays(4);
			   break;
			   case "WEDNESDAY":saturdayDatepicker2 = datePicker.plusDays(3);
			   break;
			   case "THURSDAY":saturdayDatepicker2 = datePicker.plusDays(2);
			   break;
			   case "FRIDAY":saturdayDatepicker2 = datePicker.plusDays(1);
			   break;
			   case "SATURDAY":saturdayDatepicker2 = datePicker;
			   break;
			   
			   }
			return saturdayDatepicker2;
			
		}
	//method to check if the user entered date is in the database
	public boolean validDateCheck(LocalDate datePicker, int employeeId) {
		
		LocalDate saturdayDatepicker2 = dayConverter(datePicker);
		List<Paystub> paystubList = psimpl.findPaystubDetails(saturdayDatepicker2, employeeId);
		boolean isDateValid=true;
		Set<LocalDate> dateSet = getDates(paystubList);
		if(dateSet.contains(saturdayDatepicker2)) {
			isDateValid=true;
		}
		else
			isDateValid=false;
		
		return isDateValid;
		
	}
	//list of amounts in $ corresponding to selected date
	public List<Float> curAmountGenerator(LocalDate datePicker, int employeeId){
		LocalDate saturdayDatepicker2 = dayConverter(datePicker);
		 List<Float> currentAmount = psimpl.findCurrentAmount(saturdayDatepicker2, employeeId);
		return currentAmount;
	
	}
	
	//list of dba types corresponding to selected date
	public List<String> dbaTypeGenerator(LocalDate datepicker,int employeeId){
		LocalDate saturdayDatepicker2 = dayConverter(datepicker);
		
		//List of dba codes corresponding to the dates chosen by user
		   List<Integer> codeList = psimpl.findDbaCode(saturdayDatepicker2, employeeId); 
		   List<String> dbaType = dbaimpl.findDbaType(codeList);
		   
		   dbaType.set(1,"Federal Income Tax") ;
		   dbaType.set(2,"Federal FICA Withheld") ;
		   dbaType.set(3, "Federal Medicare Withheld");
		
		return dbaType;
		
	}
	
	public String netPayGenerator(LocalDate date, int employeeId) {
		
		
		//LocalDate saturdayDatepicker2 = dayConverter(date);
		String grossPayString = grossPayGenerator(date,employeeId);
		//initializing netpay to grossPay
		float netPay = Float.parseFloat(grossPayString);
		
		List<Float> curAmount = psimpl.findCurrentAmount(date,employeeId);

		 //List of dba codes corresponding to the dates chosen by user
		   List<Integer> codeList = psimpl.findDbaCode(date,employeeId);
		   
		 //list of  description- Earning or deduction
		   List<String> dbaDesc = dbaimpl.findDbaDescription(codeList);
		   
		   for(int i=1;i<dbaDesc.size();i++) {
			   if(dbaDesc.get(i).contains("Deductions")) {
				   netPay=netPay-curAmount.get(i);
			   }
		   }
		   DecimalFormat df = new DecimalFormat("0.00");
		   String netPayString = df.format(netPay);
		  
		return  netPayString;
		
	}
	
	public int totalHoursGenerator(LocalDate date, int employeeId) {
		
		int totalHours=0;
		
		LocalDate saturdayDatepicker2 = dayConverter(date);
		 List<Integer> hours = psimpl.findTotalHours(saturdayDatepicker2,employeeId);
		 
		 for(Integer hour:hours) {
			 totalHours += hour;
		 }
		return totalHours;
		
	}

	public Set<LocalDate> getDates(List<Paystub> paystubList) {
		Set<LocalDate> dateSet = new TreeSet<LocalDate>();
		//List<PaystubResponse> paystubList = psimpl.getAllPaystubs(empId);
		for(Paystub p:paystubList) {
			dateSet.add(p.getPayPeriodEndDate());
		}
		
		return dateSet;
	}
	


	public String grossPayGenerator(LocalDate datePicker, int employeeId){
		LocalDate saturdayDatepicker2 = dayConverter(datePicker);
		
	float grossPay = 0;
		
		List<Float> curAmount = psimpl.findCurrentAmount(saturdayDatepicker2,employeeId);


		 //List of dba codes corresponding to the dates chosen by user
		   List<Integer> codeList = psimpl.findDbaCode(saturdayDatepicker2,employeeId);
		   
		 //list of  description- Earning or deduction
		   List<String> dbaDesc = dbaimpl.findDbaDescription(codeList);
		   
		   for(int i=0;i<dbaDesc.size();i++) {
			   if(dbaDesc.get(i).contains("Earnings")) {
				   grossPay +=curAmount.get(i);
			   }
		   }
		   
		   DecimalFormat df = new DecimalFormat("0.00");
		   String grossPayString = df.format(grossPay);
		
		 return grossPayString;
		
	}
	

	

}
