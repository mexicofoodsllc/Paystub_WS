package com.elrancho.paystubwebapp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elrancho.paystubwebapp.entity.Paystub;
import com.elrancho.paystubwebapp.repository.PaystubRepository;
import com.elrancho.paystubwebapp.util.PaystubUtil;

@Service
public class PaystubServiceImpl implements PaystubService {
	
	@Autowired 
	private PaystubRepository paystubRepository;
	@Autowired
	PaystubUtil psutil;
	public List<Paystub> paystubs;
	
	@Override
	public List<Paystub> getAllPaystubs(int empId) {
		List<Paystub> paystubs = paystubRepository.findByEmployeeId(empId);
		System.out.println("in service "+paystubs);

		
		return paystubs;
	}

	//method to get the current Amount corresponding to the date passed from controller
	@Override
	public List<Float> findCurrentAmount(LocalDate date, int employeeId) {
		List<Float> curAmount = new ArrayList<>();
		Iterable<Paystub> paystubs = paystubRepository.findByEmployeeId(employeeId);
		System.out.println("PAystubs "+paystubs);
		//selecting the currentAmount corresponding to the dates selected by user and adding it to the list curAmount
		for(Paystub paystub: paystubs) {
			if(date.equals(paystub.getPayPeriodEndDate())) {
				curAmount.add(paystub.getCurrentAmount());
			} 
			//else
				//curAmount.add(0.0f);
						
		}
		return curAmount;
	}

	@Override
	public List<Integer> findDbaCode(LocalDate date,int employeeId) {
		List<Integer> dbaCode = new ArrayList<>();
		Iterable<Paystub> paystubs = paystubRepository.findByEmployeeId(employeeId);
		for(Paystub paystub: paystubs) {
			if(date.equals(paystub.getPayPeriodEndDate())) {
				dbaCode.add(paystub.getDbaCode());
			}

		}
		return dbaCode;
	}

	@Override
	public List<Integer> findTotalHours(LocalDate date,int employeeId) {
		List<Integer> hoursList = new ArrayList<Integer>();
		Iterable<Paystub> paystubs = paystubRepository.findByEmployeeId(employeeId);
		for(Paystub paystub: paystubs) {
			if(date.equals(paystub.getPayPeriodEndDate())) {
				
				hoursList.add(paystub.getHours());
			}

		}
		return hoursList;
	}

	@Override
	public List<Paystub> findPaystubDetails(LocalDate Date,int employeeId) {
		Iterable<Paystub> paystubs = paystubRepository.findByEmployeeId(employeeId);
		List<Paystub> ps= new ArrayList<Paystub>();
		LocalDate saturdayDate = psutil.dayConverter(Date);
		//filtering the paystub based on payperiod end date
		for(Paystub paystub: paystubs) {
			if(saturdayDate.equals(paystub.getPayPeriodEndDate())) {
				ps.add(paystub);
			}
						
		}
		return ps;
	}

	@Override
	public Float findTotalYrToPay(LocalDate date,int employeeId) {
		Float TotalYearToPay = 0.0f;
		Iterable<Paystub> paystubs = paystubRepository.findByEmployeeId(employeeId);
		for(Paystub paystub: paystubs) {
			if(date.equals(paystub.getPayPeriodEndDate())) {
				
				TotalYearToPay += TotalYearToPay+ paystub.getYtdAmount();
			}
		}

		
		return TotalYearToPay;
	}




}
