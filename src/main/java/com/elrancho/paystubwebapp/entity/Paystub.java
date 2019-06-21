package com.elrancho.paystubwebapp.entity;


import java.time.LocalDate;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="Paystub")
public class Paystub {
	
	@EmbeddedId private PaystubId id;
	
	String description;
	int employeeId, dbaCode;
	float currentAmount, ytdAmount, hours;

	LocalDate payPeriodEndDate;  
	
	LocalDate checkDate; 
	
	
	
	public PaystubId getId() {
		return id;
	}



	public void setId(PaystubId id) {
		this.id = id;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public int getEmployeeId() {
		return employeeId;
	}



	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}



	public int getDbaCode() {
		return dbaCode;
	}



	public void setDbaCode(int dbaCode) {
		this.dbaCode = dbaCode;
	}



	public float getHours() {
		return hours;
	}



	public void setHours(float hours) {
		this.hours = hours;
	}



	public float getCurrentAmount() {
		return currentAmount;
	}



	public void setCurrentAmount(float currentAmount) {
		this.currentAmount = currentAmount;
	}



	public float getYtdAmount() {
		return ytdAmount;
	}



	public void setYtdAmount(float ytdAmount) {
		this.ytdAmount = ytdAmount;
	}



	public LocalDate getPayPeriodEndDate() {
		return payPeriodEndDate;
	}



	public void setPayPeriodEndDate(LocalDate payPeriodEndDate) {
		this.payPeriodEndDate = payPeriodEndDate;
	}



	public LocalDate getCheckDate() {
		return checkDate;
	}



	public void setCheckDate(LocalDate checkDate) {
		this.checkDate = checkDate;
	}



	@Override
	public String toString() {
		return "PaystubResponse [id=" + id + ", description=" + description + ", employeeId=" + employeeId + ", dbaCode="
				+ dbaCode + ", hours=" + hours + ", currentAmount=" + currentAmount + ", ytdAmount=" + ytdAmount
				+ ", payPeriodEndDate=" + payPeriodEndDate + ", checkDate=" + checkDate + "]";
	}



	


	
	
}
