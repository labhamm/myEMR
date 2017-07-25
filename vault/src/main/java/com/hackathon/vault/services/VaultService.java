package com.hackathon.vault.services;

import java.io.IOException;
import java.util.List;

import com.hackathon.vault.entity.ObjectData;
import com.hackathon.vault.entity.Timeline;
import com.hackathon.vault.exception.MissingResourceException;
import com.hackathon.vault.exception.ServiceException;
import com.hackathon.vault.services.impl.CloudService;
import com.hackathon.vault.services.impl.LocalService;

/**
 * The user of this interface has precise control over storing as well as
 * retrieving the files.
 * <p>
 * Object could be stored by providing the byte[] and returns the file name
 * using {@link #storeObject(byte[])}.
 * <p>
 * Retrieve the object using the resource ID and gets the byte[] using
 * {@link #retrieveObject(String)}.
 *
 * @author vimal.singh@philips.com
 * 
 * @see CloudService
 * @see LocalService
 */
public interface VaultService {

	/**
	 * Store object.
	 * 
	 * @param stringBuffer
	 *
	 * @param object
	 *            the object
	 * @param fileName
	 * @return the string
	 * @throws ServiceException
	 *             the service exception
	 * @throws IOException
	 */
	public String storeObject(ObjectData data, StringBuffer baseURL) throws ServiceException, IOException;

	/**
	 * Retrieve object.
	 *
	 * @param resourceID
	 *            the resource ID
	 * @return the byte[]
	 * @throws ServiceException
	 *             the service exception
	 * @throws MissingResourceException
	 *             the missing resource exception
	 */
	public byte[] retrieveObject(String resourceID) throws ServiceException, MissingResourceException;

	public List<Timeline> getObjectList(String tags);

	public Timeline updateTags(Timeline data);

}
