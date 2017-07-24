package com.hackathon.vault.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.hackathon.vault.entity.PhoenixResponse;
import com.hackathon.vault.entity.ResponseUtils;

/**
 * VaultResponseUtil provides the utility for response.
 *
 * @author vimal.singh@philips.com
 * @see ResponseUtils
 */
@Component
public class VaultResponseUtil extends ResponseUtils {

	/**
	 * Resource created.
	 *
	 * @param resourceID
	 *            the resource ID
	 * @param request
	 *            the request
	 * @return the response entity
	 */
	public ResponseEntity<PhoenixResponse> resourceCreated(String fileLocation, HttpServletRequest request) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", fileLocation);
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	/**
	 * Gets the byte response.
	 *
	 * @param data
	 *            the data
	 * @return the byte response
	 */
	public ResponseEntity<byte[]> getByteResponse(byte[] data) {
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

}
