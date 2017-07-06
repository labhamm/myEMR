package com.hackathon.vault.exception;

/**
 * 
 * This exception should be thrown by services that are unable to proceed
 * further because of an internal error.
 * 
 * Should be used only in context, where the service is at fault and consumer of
 * the service had nothing to do with the error.
 * 
 * For example, error in loading JOLT spec, corrupt data from back end, runtime
 * errors etc.
 * 
 * @author prashanth.shenoy@philips.com
 *
 */
public class ServiceException extends Exception {
	/**
	* 
	*/
	private static final long serialVersionUID = -6919172582634569764L;

	public ServiceException() {

	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
