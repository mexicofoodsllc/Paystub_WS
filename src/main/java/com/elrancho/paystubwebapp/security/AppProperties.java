package com.elrancho.paystubwebapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {

	@Autowired
	private Environment env;
	
	public String getTokenSecret() {
		return env.getProperty("tokenSecret");
	}
	
	public String getAwsSesAccessKey() {
		return env.getProperty("accessKey");
	}
	
	public String getAwsSesSecretKey() {
		return env.getProperty("secretKey");
	}
}
