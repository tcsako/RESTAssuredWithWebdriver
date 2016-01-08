package com.epam.restassured.exception;

/**
 * Exception class to handle exceptions during test execution
 *
 * @author Tamas_Csako
 */
public class TestExecutionException extends Exception {

    /**
     * Generated version ID
     */
    private static final long serialVersionUID = -5521593491738554901L;

    /**
     * Parameterless Constructor
     */
    public TestExecutionException() {
    }

    public TestExecutionException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
