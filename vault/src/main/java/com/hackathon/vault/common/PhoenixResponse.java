package com.hackathon.vault.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
import lombok.Setter;

/**
 * All Phoenix APIs will return the response as PhoenixResponse
 * 
 * @author labham.gupta@philips.com
 *
 */
@Getter
@Setter
@JsonSerialize
@JsonDeserialize
@JsonInclude(Include.NON_NULL)
public class PhoenixResponse {

	/**
	 * The data returned as part of PhoenixResponse is specific to the API
	 * invoked
	 */
	private Object response;

	/**
	 * PhoenixResponse will contain an error structure only in case of an error
	 */
	private ErrorResponse errorResponse;

	public PhoenixResponse() {

	}

	public PhoenixResponse(Object data) {
		this.response = data;
	}

	public PhoenixResponse(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}

	public PhoenixResponse(Object data, ErrorResponse errorResponse) {
		this.response = data;
		this.errorResponse = errorResponse;
	}

}
