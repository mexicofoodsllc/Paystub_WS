package com.elrancho.paystubwebapp.entity;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



@Entity
@Table(name="Users")
public class Users {

	@NotNull
	@Id
	int employeeId;
	String password;
	String userName;
	

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Users(int employeeId, String password, String userName) {
		this.employeeId = employeeId;
		this.password = password;
		this.userName = userName;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "Users [employeeId=" + employeeId + ", password=" + password + ", userName=" + userName + "]";
	}
	

	
}
