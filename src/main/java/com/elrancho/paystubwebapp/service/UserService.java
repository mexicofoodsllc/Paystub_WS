package com.elrancho.paystubwebapp.service;



import org.springframework.security.core.userdetails.UserDetailsService;

import com.elrancho.paystubwebapp.entity.Users;

public interface UserService extends UserDetailsService {

	public void registerUser(Users user);
	public boolean activeUserCheck(int empid);
	public boolean passwordValidator(String password);
	public int getEmpId(String password);
}
