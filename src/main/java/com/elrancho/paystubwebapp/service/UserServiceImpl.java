package com.elrancho.paystubwebapp.service;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.elrancho.paystubwebapp.entity.Users;
import com.elrancho.paystubwebapp.repository.UsersRepository;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	EmployeeServiceImpl esimpl;	


	@Override
	public void registerUser(Users user) {
		
		usersRepository.save(user);	
	}

	@Override
	public boolean activeUserCheck(int empid) {
		boolean isActive=false;
		Iterable<Users> userList = usersRepository.findAll();
		System.out.println(userList);
		for(Users u:userList) {
			if(u.getEmployeeId()==empid) {
				isActive=true;
			}
			else
				isActive=false;
		}
		System.out.println(isActive);
		return isActive;
	}

	@Override
	public boolean passwordValidator(String password) {
		boolean isValid = false;
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		Iterable<Users> userList = usersRepository.findAll();
		for(Users u:userList) {
			if(encoder.matches(password, u.getPassword())) {
				isValid=true;
				//System.out.println("u.getPassword()  "+u.getEmployeeId());
				
			}
		}
		
		
		return isValid;
	}

	@Override
	public int getEmpId(String password) {
		Iterable<Users> userList = usersRepository.findAll();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		int eId = 0;
		for(Users u:userList) {
			if(encoder.matches(password, u.getPassword())) {
				eId=u.getEmployeeId();
			}
		}
		return eId;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users u = usersRepository.findByUserName(username);
		System.out.println("loadUserByUsername "+u);
		
		if (u == null)
			throw new UsernameNotFoundException(username);
		/*refer https://docs.spring.io/spring-security/site/docs/3.2.x/apidocs/org/springframework/security/core/userdetails/User.html
		set all other variables as true for the constructor call */
		
		return new User(u.getUserName(), u.getPassword(), true,
				true, true, true, new ArrayList<>());
	}


	
	
	

	
}
