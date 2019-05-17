package com.elrancho.paystubwebapp.service;

import java.util.List;

public interface DbaService {

	public List<String> findDbaType(List<Integer> code);
	public List<String> findDbaDescription(List<Integer> code);
	public String getDbaDesciption(int dbaCode);

}
