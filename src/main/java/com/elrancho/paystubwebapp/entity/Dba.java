package com.elrancho.paystubwebapp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Dba")
public class Dba {
	
	@Id
	int dbaCode;
	String description;
	String dbaType;
	public int getDbaCode() {
		return dbaCode;
	}
	public void setDbaCode(int dbaCode) {
		this.dbaCode = dbaCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDbaType() {
		return dbaType;
	}
	public void setDbaType(String dbaType) {
		this.dbaType = dbaType;
	}
	@Override
	public String toString() {
		return "Dba [dbaCode=" + dbaCode + ", description=" + description + ", dbaType=" + dbaType + "]";
	}

}
