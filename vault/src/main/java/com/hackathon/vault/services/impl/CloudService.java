package com.hackathon.vault.services.impl;

import org.apache.commons.lang3.StringUtils;

import com.hackathon.vault.exception.ServiceException;
import com.hackathon.vault.services.VaultService;

/**
 * Cloud service is implementation of <tt>VaultService</tt>. 
 * <p> It implements all the method provided by VaultService as {@link #storeObject(byte[])} and {@link #retrieveObject(String)}
 * 
 * @author vimal.singh@philips.com
 * 
 * @see VaultService
 *
 */
public class CloudService implements VaultService {

  /**
   * Retrieve the object from cloud.
   * 
   * @param String
   * @return Returns the byte[] of object.
   * @see com.philips.phoenix.vault.services.VaultService#retrieveObject(java.lang.String)
   */
  @Override
  public byte[] retrieveObject(String resourceID) throws ServiceException {
    // TODO
    return StringUtils.EMPTY.getBytes();
  }

  /**
   * Store the object into cloud.
   * 
   * @param Object as byte[]
   * @return File name
   * @see com.philips.phoenix.vault.services.VaultService#storeObject(byte[])
   */
  @Override
  public String storeObject(byte[] file,String fileName) throws ServiceException {
    // TODO
    return StringUtils.EMPTY;
  }

}
