package com.elrancho.paystubwebapp.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.userdetails.User;

import com.elrancho.paystubwebapp.entity.Users;
import com.elrancho.paystubwebapp.service.PaystubServiceImpl;
import com.elrancho.paystubwebapp.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/*
 * AuthenticationFilter is the class responsible for the authentication process.
 * It extends the UsernamePasswordAuthenticationFilter class. 
 * When we add a new filter to Spring Security, we can explicitly define where in the filter chain we want that filter,
 *  or we can let the framework figure it out by itself. By extending the filter provided within the security framework, 
 *  Spring can automatically identify the best place to put it in the security chain.
 */

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	@Autowired
	UserServiceImpl usrimpl;
	
	private final AuthenticationManager authenticationManager;

	//constructor
	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	// we parse the user's credentials and issue them to the AuthenticationManager.
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {

		try {
			//reading json input username&pwd
			Users creds = new ObjectMapper()
                    .readValue(req.getInputStream(), Users.class);
			
			//String pwd = creds.getPassword();
			//int employeeId = usrimpl.getEmpId(pwd);
			

			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getUserName(), creds.getPassword(), new ArrayList<>()));	
		
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed)
			throws IOException, ServletException {
		
		String msg = failed.getMessage();
		//response.setStatus(HttpStatus.SC_UNAUTHORIZED);
				
		response.addHeader("failedMessage", msg);
	}
	
	//This method is called when a user successfully logs in. We use this method to generate a JWT for this user.
	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		String username = ((User) auth.getPrincipal()).getUsername();
		
        //security token generation Java web token security

		String token = Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET).compact();
        

		//add to httpheader
		res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		
		
	}
}

