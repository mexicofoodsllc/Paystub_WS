package com.elrancho.paystubwebapp.repository;

import com.elrancho.paystubwebapp.entity.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Integer>{

	Users findByEmployeeId(int employeeId);
	Users findByUserName(String userName);
}
