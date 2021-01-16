package com.cg.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LoginExceptionTest {
	@Test
    public void whenExceptionThrown_thenAssertionSucceeds() {
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            Integer.parseInt("1a");
        });
        String expectedMessage = "For input string";
        String actualMessage = exception.getMessage();

 

        assertTrue(actualMessage.contains(expectedMessage));
    }

	@Test
	void exceptionTesting() {
	    Throwable exception = assertThrows(OperationFailedException.class, () -> {
	        throw new OperationFailedException ("a message");
	    });
	    assertEquals("a message", exception.getMessage());
	}
	
	
}