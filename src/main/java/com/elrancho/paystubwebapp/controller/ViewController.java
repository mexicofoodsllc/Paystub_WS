package com.elrancho.paystubwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ViewController {
	
	
	//returns index.jsp
	  /* @GetMapping("/login")
	   public String login() {
	      return "index";
	   }*/
	   
	   @PostMapping("/register")
	   public String registerEmployee() {
		   return "register";
	   }
	   
	   @PostMapping("/forgotpwd")
	   public String resetPassword() {
		   return "forgotpwdSecurityQuestions";
	   }

}
