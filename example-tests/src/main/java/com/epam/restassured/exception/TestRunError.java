package com.epam.restassured.exception;

/**
 * .
 *
 * @author Jozsef_Koza
 */
public class TestRunError extends RuntimeException {
    public TestRunError(String message, Throwable throwable) {
        super(message, throwable);
    }
}
