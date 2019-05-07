package com.elrancho.paystubwebapp.model;

import java.time.LocalDate;

public class PaystubSummary {
	LocalDate payPeriodEndDate;
	float grossPay;
	float netPay;
	int hours;
	
	
	public PaystubSummary(LocalDate payPeriodEndDate, float grossPay, float netPay, int hours) {
		this.payPeriodEndDate = payPeriodEndDate;
		this.grossPay = grossPay;
		this.netPay = netPay;
		this.hours = hours;
	}
	public LocalDate getPayPeriodEndDate() {
		return payPeriodEndDate;
	}
	public void setPayPeriodEndDate(LocalDate payPeriodEndDate) {
		this.payPeriodEndDate = payPeriodEndDate;
	}
	public float getGrossPay() {
		return grossPay;
	}
	public void setGrossPay(float grossPay) {
		this.grossPay = grossPay;
	}
	public float getNetPay() {
		return netPay;
	}
	public void setNetPay(float netPay) {
		this.netPay = netPay;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}

}
