package com.epam.restassured.exception;

/**
 * .
 *
 * @author Jozsef_Koza
 */
public class TestRunError extends RuntimeException {
    /**
	 * Generated version ID
	 */
	private static final long serialVersionUID = -6077378151346358669L;

	public TestRunError(String message, Throwable throwable) {
        super(message, throwable);
    }
}
