package com.elrancho.paystubwebapp.service;

import java.time.LocalDate;
import java.util.List;

import com.elrancho.paystubwebapp.entity.Paystub;

public interface PaystubService {
	

	public List<Float> findCurrentAmount(LocalDate Date, int employeeId);
	public List<Integer> findDbaCode(LocalDate Date,int employeeId);
	public List<Integer> findTotalHours(LocalDate Date,int employeeId);
	public Float findTotalYrToPay(LocalDate date,int employeeId);
	
	public List<Paystub> findPaystubDetails(LocalDate Date,int employeeId);
	public List<Paystub> getAllPaystubs(int empId);
	
	

}
