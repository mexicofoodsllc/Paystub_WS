package com.elrancho.paystubwebapp.service;

import java.util.List;

import com.elrancho.paystubwebapp.entity.Dba;

public interface DbaService {

	public List<String> findDbaType(List<Integer> code);
	public List<String> findDbaDescription(List<Integer> code);

}
