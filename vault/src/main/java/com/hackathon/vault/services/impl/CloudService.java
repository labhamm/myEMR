package com.hackathon.vault.services.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;

import com.hackathon.vault.config.S3Wrapper;
import com.hackathon.vault.exception.ServiceException;
import com.hackathon.vault.services.VaultService;

/**
 * Cloud service is implementation of <tt>VaultService</tt>.
 * <p>
 * It implements all the method provided by VaultService as
 * {@link #storeObject(byte[])} and {@link #retrieveObject(String)}
 * 
 * @author vimal.singh@philips.com
 * 
 * @see VaultService
 *
 */
public class CloudService implements VaultService {

	@Autowired
	private S3Wrapper s3Wrapper;

	/**
	 * Retrieve the object from cloud.
	 * 
	 * @param String
	 * @return Returns the byte[] of object.
	 * @see com.philips.phoenix.vault.services.VaultService#retrieveObject(java.lang.String)
	 */
	@Override
	public byte[] retrieveObject(String resourceID) throws ServiceException {
		byte[] download = null;
		try {
			download = s3Wrapper.download(resourceID);
		} catch (IOException e) {
			throw new ServiceException(e);
		}
		return download;
	}

	/**
	 * Store the object into cloud.
	 * 
	 * @param Object
	 *            as byte[]
	 * @return File name
	 * @see com.philips.phoenix.vault.services.VaultService#storeObject(byte[])
	 */
	@Override
	public String storeObject(byte[] contents, String fileName) throws ServiceException {
		InputStream stream = new ByteArrayInputStream(contents);
		s3Wrapper.upload(stream, fileName);
		return fileName;
	}
}
