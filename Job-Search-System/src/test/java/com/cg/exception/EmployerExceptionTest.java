package com.cg.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.cg.dao.EmployerSpringDataDAO;
import com.cg.entity.Employer;

public class EmployerExceptionTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	  void whenNullPointerExceptionThrown_thenAssertionSucceeds() {
	    Assertions.assertThrows(NullPointerException.class, () -> {
	    	Employer fromDb = new Employer("capgemini");
			entityManager.persistAndFlush(fromDb);
	    });
	  }
	
	@Test
	  void whenIllegalArgumentExceptionThrown_thenAssertionSucceeds() {
	    Assertions.assertThrows(IllegalArgumentException.class, () -> {
	    	Employer fromDb = new Employer("capgemini","mumbai",Long.parseLong("cvb12"),"xyz.gmail.com","xyz1234","pass1234");
			entityManager.persistAndFlush(fromDb);
	    });
	  }
	
	
}