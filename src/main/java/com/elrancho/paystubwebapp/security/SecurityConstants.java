package com.elrancho.paystubwebapp.security;

public class SecurityConstants {
	
	public  static final String REGISTER_URL = "/empRegSucess";
	public  static final String PASSWORD_RESET_URL = "/pwdReset";
	//public  static final String PAYSTUBS_EMPLOYEE_URL = "/payStubs/";
	//public  static final String PAYSTUBS_BY_DATE = "/payStubs/";
	
	public static final String TOKEN_PREFIX= "Bearer ";
	public static final String HEADER_STRING = "authorization";
	public static final String TOKEN_REQUEST_PARAM = "token";
	public static final String SECRET = "d3f16XlDksSJH21g68hQE100";
	
	public static final long EXPIRATION_TIME = 86400000;
	

}
