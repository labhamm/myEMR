package com.hackathon.vault.entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseUtils {

	final String INVALID_REQUEST_INPUT = "Request input is invalid";

	public String getInvalidResourceMessage(String resourceID) {
		return "Resource with ID = " + resourceID + " does not exist";
	}

	public ResponseEntity<PhoenixResponse> resourceCreated(Object response) {
		PhoenixResponse phoenixResponse = new PhoenixResponse(response);
		return new ResponseEntity<PhoenixResponse>(phoenixResponse, HttpStatus.CREATED);

	}

	public ResponseEntity<PhoenixResponse> resourceCreationFailed(String message) {

		ErrorResponse errorResponse = new ErrorResponse(ErrorType.invalid, INVALID_REQUEST_INPUT);
		errorResponse.setMessage(message);
		PhoenixResponse phoenixResponse = new PhoenixResponse(errorResponse);
		return new ResponseEntity<PhoenixResponse>(phoenixResponse, HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<PhoenixResponse> resourceRetrieved(Object response) {
		PhoenixResponse phoenixResponse = new PhoenixResponse(response);
		return new ResponseEntity<PhoenixResponse>(phoenixResponse, HttpStatus.OK);
	}

	public ResponseEntity<PhoenixResponse> missingResource(String resourceID) {
		String errorMessage = getInvalidResourceMessage(resourceID);
		ErrorResponse errorResponse = new ErrorResponse(ErrorType.notfound, errorMessage);
		PhoenixResponse phoenixResponse = new PhoenixResponse(errorResponse);
		return new ResponseEntity<PhoenixResponse>(phoenixResponse, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<PhoenixResponse> resourceUpdated(Object response) {
		PhoenixResponse phoenixResponse = new PhoenixResponse(response);
		return new ResponseEntity<PhoenixResponse>(phoenixResponse, HttpStatus.OK);
	}

	public ResponseEntity<PhoenixResponse> resourceUpdationFailedDueToNonExistentResource(String resourceID) {
		String errorMessage = getInvalidResourceMessage(resourceID);
		ErrorResponse errorResponse = new ErrorResponse(ErrorType.notfound, errorMessage);
		PhoenixResponse phoenixResponse = new PhoenixResponse(errorResponse);
		return new ResponseEntity<PhoenixResponse>(phoenixResponse, HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<PhoenixResponse> resourceUpdationFailedDueToInvalidBody(String message) {
		ErrorResponse errorResponse = new ErrorResponse(ErrorType.invalid, INVALID_REQUEST_INPUT);
		errorResponse.setMessage(message);
		PhoenixResponse phoenixResponse = new PhoenixResponse(errorResponse);
		return new ResponseEntity<PhoenixResponse>(phoenixResponse, HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<PhoenixResponse> internalServerError() {
		String errorMessage = "An internal server error occured";
		ErrorResponse errorResponse = new ErrorResponse(ErrorType.exception, errorMessage);
		PhoenixResponse phoenixResponse = new PhoenixResponse(errorResponse);
		return new ResponseEntity<PhoenixResponse>(phoenixResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<PhoenixResponse> referentialIntegrityViolation(String resourceID, String errorMessage) {
		ErrorResponse errorResponse = new ErrorResponse(ErrorType.conflict, errorMessage);
		PhoenixResponse phoenixResponse = new PhoenixResponse(errorResponse);
		return new ResponseEntity<PhoenixResponse>(phoenixResponse, HttpStatus.CONFLICT);
	}

	public ResponseEntity<PhoenixResponse> noContent() {
		return new ResponseEntity<PhoenixResponse>(HttpStatus.NO_CONTENT);
	}

	public ResponseEntity<PhoenixResponse> success() {
		return new ResponseEntity<PhoenixResponse>(HttpStatus.OK);
	}

	public ResponseEntity<PhoenixResponse> unauthorized(String errorMessage) {
		ErrorResponse errorResponse = new ErrorResponse(ErrorType.unauthorized, errorMessage);
		PhoenixResponse phoenixResponse = new PhoenixResponse(errorResponse);
		return new ResponseEntity<PhoenixResponse>(phoenixResponse, HttpStatus.UNAUTHORIZED);
	}

}
