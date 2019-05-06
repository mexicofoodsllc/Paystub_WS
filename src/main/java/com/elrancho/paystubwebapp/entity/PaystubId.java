package com.elrancho.paystubwebapp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/*
 * This class is a composite id for Paystub table
 * It can be embedded into Paystub.java and 
 * checkControl & lineNumber act as composite primary keys.
 */

@Embeddable
public class PaystubId implements Serializable {
	
	private int checkControl;	

	private int lineNumber; 

	public int getCheckControl() {
		return checkControl;
	}

	public void setCheckControl(int checkControl) {
		this.checkControl = checkControl;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	


	

}
