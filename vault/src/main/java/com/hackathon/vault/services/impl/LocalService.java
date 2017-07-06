package com.hackathon.vault.services.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;

import com.hackathon.vault.exception.MissingResourceException;
import com.hackathon.vault.exception.ServiceException;
import com.hackathon.vault.services.VaultService;

/**
 * Local service is implementation of <tt>VaultService</tt>.
 * <p>
 * It implements all the method provided by VaultService as
 * {@link #storeObject(byte[])} and {@link #retrieveObject(String)}
 * 
 * @author vimal.singh@philips.com
 * 
 * @see VaultService
 *
 */
public class LocalService implements VaultService {

	@Value("${multipart.location}")
	private String location;

	@Value("${local.vault_dir}")
	private String vaultDir;

	/**
	 * Retrieve the object from local.
	 * 
	 * @see com.philips.phoenix.vault.services.VaultService#retrieveObject(java.lang.String)
	 */
	@Override
	public byte[] retrieveObject(String resourceID) throws ServiceException, MissingResourceException {
		File file = new File(location + vaultDir, resourceID);
		if (!file.exists()) {
			throw new MissingResourceException();
		}
		try {
			return FileUtils.readFileToByteArray(file);
		} catch (IOException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Store the object into local.
	 * 
	 * @see com.philips.phoenix.vault.services.VaultService#storeObject(byte[])
	 */
	@Override
	public String storeObject(byte[] object, String fileName) throws ServiceException {
		File file = new File(location + vaultDir, fileName);
		try {
			FileUtils.writeByteArrayToFile(file, object);
		} catch (IOException e) {
			throw new ServiceException(e);
		}
		return fileName;
	}

}
