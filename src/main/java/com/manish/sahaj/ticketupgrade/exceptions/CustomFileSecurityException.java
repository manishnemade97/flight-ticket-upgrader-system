package com.manish.sahaj.ticketupgrade.exceptions;

public class CustomFileSecurityException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1154761663303796098L;

	public CustomFileSecurityException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomFileSecurityException(String message) {
		super(message);
	}

}
