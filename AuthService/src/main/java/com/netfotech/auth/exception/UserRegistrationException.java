package com.netfotech.auth.exception;

public class UserRegistrationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	// Constructor with only the error message
    public UserRegistrationException(String message) {
        super(message);
    }
	public UserRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }
}
