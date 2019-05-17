package com.elrancho.paystubwebapp.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elrancho.paystubwebapp.entity.Dba;

public interface DbaRepository extends JpaRepository<Dba,Integer>{
	List<Dba> findByDbaCode(int dbaCode);
}
