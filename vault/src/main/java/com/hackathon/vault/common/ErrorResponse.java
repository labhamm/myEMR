package com.hackathon.vault.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

/**
 * Not all error information can be conveyed through HTTP status code alone.
 * 
 * The purpose of the Error class is to include additional error information as
 * part of an API Response.
 * 
 * For example when create patient API is invoked and a mandatory parameter is
 * missing , the error structure will include information about the missing
 * parameter.
 *
 */

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = -778071434299134529L;

	private ErrorType errorType;
	private String message;

	public ErrorResponse() {

	}

	public ErrorResponse(String message) {
		this.message = message;
	}

	public ErrorResponse(ErrorType errorType, String message) {
		this.errorType = errorType;
		this.message = message;
	}
}
