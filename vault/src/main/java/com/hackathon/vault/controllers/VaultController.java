package com.hackathon.vault.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hackathon.vault.entity.ObjectData;
import com.hackathon.vault.entity.PhoenixResponse;
import com.hackathon.vault.entity.Timeline;
import com.hackathon.vault.exception.MissingResourceException;
import com.hackathon.vault.exception.ServiceException;
import com.hackathon.vault.services.VaultService;
import com.hackathon.vault.util.VaultResponseUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * API for uploading and downloading files
 * 
 * <p>
 * Exposes REST API for uploading files
 * {@link #uploadResource(MultipartFile, MultipartHttpServletRequest)}.
 * 
 * <blockquote>
 * </pre>
 * It accepts the data as MultipartFile and return the file location into header
 * {@code "Location"} into response. Returns the 500 in case of internal server
 * issues.
 * </pre>
 * </blockquote>
 * 
 * <p>
 * Exposes REST API for downloading files
 * {@link #downloadResource(String, HttpServletRequest, HttpServletResponse)}.
 * 
 * <blockquote>
 * </pre>
 * Using resource ID, finds the file if file found then returns bytes otherwise
 * ResponseCode as 404 with error message. Returns the 500 in case of internal
 * server issues.
 * </pre>
 * </blockquote>
 * 
 * @author vimal.singh@philips.com
 * @see VaultService
 */

@RequestMapping("/api/v1")
@RestController
public class VaultController {

	@Autowired
	private VaultService service;

	@Autowired
	private VaultResponseUtil responseUtil;

	/**
	 * Download resource.
	 *
	 * @param resourceID
	 *            the resource ID
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the response entity
	 */
	@ApiOperation(tags = { "Vault" }, value = "Retrieves a file with the specified resource ID.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved file"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/File/{resourceID:.+}", method = RequestMethod.GET)
	public ResponseEntity<?> downloadResource(@PathVariable("resourceID") String resourceID, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			byte[] readFileToByteArray = service.retrieveObject(resourceID);
			return responseUtil.getByteResponse(readFileToByteArray);
		} catch (ServiceException e) {
			return responseUtil.internalServerError();
		} catch (MissingResourceException e) {
			return responseUtil.missingResource(resourceID);
		}
	}

	/**
	 * Upload resource.
	 *
	 * @param file
	 *            the file
	 * @param request
	 *            the request
	 * @return the response entity
	 */
	@ApiOperation(tags = { "Vault" }, value = "Upload a file.")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully file stored"),
			@ApiResponse(code = 401, message = "You are not authorized to store the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/File", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<PhoenixResponse> uploadResource(@Valid @NotNull ObjectData data,
			MultipartHttpServletRequest request) {
		ResponseEntity<PhoenixResponse> response;
		try {

			String fileLocation = service.storeObject(data, request.getRequestURL());
			response = responseUtil.resourceCreated(fileLocation, request);
		} catch (ServiceException | IOException e) {
			response = responseUtil.internalServerError();
		}
		return response;
	}

	@ApiOperation(tags = { "Vault" }, value = "Update a tags.")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully file stored"),
			@ApiResponse(code = 401, message = "You are not authorized to store the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/File", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PhoenixResponse> addTag(@Valid @NotNull @RequestBody Timeline data,
			HttpServletRequest request) {
		ResponseEntity<PhoenixResponse> response;
		try {
			Timeline timeline = service.updateTags(data);
			response = responseUtil.resourceUpdated(timeline);
		} catch (Exception e) {
			response = responseUtil.internalServerError();
		}
		return response;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<PhoenixResponse> Resource(HttpServletRequest request) {
		String resourceLocation = new StringBuilder("http://").append(request.getServerName()).append(":")
				.append(request.getServerPort()).append("/index.html#/upload").toString();
		PhoenixResponse response = new PhoenixResponse(resourceLocation);
		return responseUtil.resourceRetrieved(response);
	}

	@RequestMapping(value = "/timeline", method = RequestMethod.GET)
	public ResponseEntity<PhoenixResponse> getResouceList(@RequestParam(required = false, value = "tags") String tags,
			HttpServletRequest request) {
		List<Timeline> timeline = service.getObjectList(tags);
		return responseUtil.resourceRetrieved(timeline);
	}
}
