package com.elrancho.paystubwebapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elrancho.paystubwebapp.entity.Dba;
import com.elrancho.paystubwebapp.repository.DbaRepository;



@Service
public class DbaServiceImpl implements DbaService {
	
	@Autowired
	private DbaRepository dbaRepository;
	

	@Override
	public List<String> findDbaType(List<Integer> code) {
		
		List<Dba> dbaList = dbaRepository.findAll();
		List<String> typeList=new ArrayList<String>();
		//iterating through the dbaList and storing the dbaType corresponding to the code into typeList
		for(int i=0;i<code.size();i++) {
			for(Dba dba:dbaList) {
				if(code.get(i)==dba.getDbaCode()) {
					typeList.add(dba.getDbaType());
					
				}
			}
		}
			
		return typeList;
	}


	@Override
	public List<String> findDbaDescription(List<Integer> code) {
		List<Dba> dbaList = dbaRepository.findAll();
	
		List<String> descList=new ArrayList<String>();
		//iterating through the dbaList and storing the dbaDescription corresponding to the code into descList
		for(int i=0;i<code.size();i++) {
			for(Dba dba:dbaList) {
				if(code.get(i)==dba.getDbaCode()) {
					descList.add(dba.getDescription());
					
				}
			}
		}
		return descList;
	}

}
