package com.hackathon.vault.common;

/**
 * 
 * Represents the type of the error
 * 
 * @author prashanth.shenoy@philips.com
 *
 */
public enum ErrorType {

	/**
	 * Invalid : Phoenix does not support the parameter.
	 * 
	 * Required: Phoenix mandated parameter was not provided.
	 * 
	 * Conflict: Indicates conflict in the state of the resource.
	 * 
	 * Exception: An unexpected internal error has occured.
	 */

	invalid("invalid"), required("required"), conflict("conflict"), exception("exception"), notfound("not-found"), security("security"), unauthorized("unauthorized");

	private final String errorType;

	private ErrorType(final String errorType) {
		this.errorType = errorType;
	}

	@Override
	public String toString() {
		return errorType;
	}

}
