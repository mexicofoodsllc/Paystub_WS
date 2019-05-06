package com.elrancho.paystubwebapp.unit_test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*; 

import com.elrancho.paystubwebapp.controller.PasswordResetController;
import com.elrancho.paystubwebapp.entity.Employee;
import com.elrancho.paystubwebapp.repository.EmployeeRepository;
import com.elrancho.paystubwebapp.service.EmployeeServiceImpl;

public class EmployeeServiceTest {
	
	/*Instead of instantiating the PasswordResetController, we want to create a mock instance of it. 
	@InjectMocks creates this object and injects it into the test. 
	@InjectMocks annotation produces a private attribute called passwordResetController 
	which Mockito is managing for us.*/
	
	@InjectMocks
	private EmployeeServiceImpl esimpl;
	@Mock
	private EmployeeRepository employeeRepository;
	
   /*
    * set-up method that initializes all of the mocked objects together when the test runs. 
    * The method annotated with @Before gets ran before each test method. 
    * The init() method runs MockitoAnnotations.initMocks(this) using this instance as the argument.
    *  This sets up our mocks before each test.
	Passing this instance will make Mockito acknowledge the @InjectMocks and the @Mocks annotations and that they should be pushed together.
 	since the PasswordResetController contains an employeeRepository in it, the Mockito framework will go ahead and set that up for us, 
 	much like the Spring container would via dependency injection.
    */
	
	@Before 
	public void init() {
	      MockitoAnnotations.initMocks(this);
	 }

	

	@Test
	public void testActiveEmployeeCheck() {
		
		Employee e = new Employee();
		e.setEmployeeId(121291);
		e.setStatus("A");
		 when(employeeRepository.findByEmployeeId(121291)).thenReturn(e);

		int employeeId = e.getEmployeeId();
		//assertEquals(121291,employeeId);
		
		boolean isActiveCheck = esimpl.activeEmployeeCheck(employeeId);
		assertEquals(true,isActiveCheck);
	}
	
	/*@Test
	public void testsecurityQuestionCheck() {
		Optional<Integer> employeeId = Optional.of(121291);
		Employee e;
		if(employeeId.isPresent()) {
			e.setEmployeeId(121291);
			e.setBirthDate(LocalDate.parse("1976-02-02"));
			
		}
		
		when(employeeRepository.findByBirthDateAndSsn(LocalDate.parse("1976-02-02"), "6789")).thenReturn(e);
		boolean isSecurityQuestionTrue = esimpl.securityQuestionCheck(LocalDate.parse("1976-02-02"),"6789");
		assertEquals(true,isSecurityQuestionTrue);
	}*/



}
