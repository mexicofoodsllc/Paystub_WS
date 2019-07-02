package com.elrancho.paystubwebapp.service;

import java.time.LocalDate;
import java.util.List;

import com.elrancho.paystubwebapp.entity.Paystub;

public interface PaystubService {
	

	public List<Float> findCurrentAmount(int checkNum, int employeeId);
	public List<Integer> findDbaCode(int checkNum,int employeeId);
	public List<Float> findTotalHours(int checkNum,int employeeId);
	public Float findTotalYrToPay(LocalDate date,int employeeId);
	
	public List<Paystub> findPaystubDetails(int checkNum,int employeeId);
	public List<Paystub> getAllPaystubs(int empId);
	
	

}
