package com.hackathon.vault.exception;

/**
 * This exception should be thrown by services whenever a particular resource is
 * unavailable.
 * 
 * For example Patient resource does not exist for update, read or delete.
 * 
 * @author prashanth.shenoy@philips.com
 *
 */
public class MissingResourceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5470242227416385627L;

	public MissingResourceException() {

	}

	public MissingResourceException(Throwable cause) {
		super(cause);
	}

	public MissingResourceException(String message) {
		super(message);
	}

	public MissingResourceException(String message, Throwable cause) {
		super(message, cause);
	}

}
