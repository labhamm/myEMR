package com.hackathon.vault.services.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.hackathon.vault.config.S3Wrapper;
import com.hackathon.vault.entity.ObjectData;
import com.hackathon.vault.entity.Timeline;
import com.hackathon.vault.exception.ServiceException;
import com.hackathon.vault.repository.TimelineRepository;
import com.hackathon.vault.services.VaultService;
import com.hackathon.vault.util.CommonUtil;
import com.hackathon.vault.util.VaultFileUtils;

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

	@Autowired
	private VaultFileUtils utils;

	@Autowired
	private TimelineRepository repository;

	@Autowired
	private CommonUtil commonUtil;

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
	 * @throws IOException
	 * @see com.philips.phoenix.vault.services.VaultService#storeObject(byte[])
	 */
	@Override
	public String storeObject(ObjectData data, StringBuffer baseURL) throws ServiceException, IOException {
		String uuid = UUID.randomUUID().toString();
		String fileName = utils.getFileName(uuid, data.getFile().getOriginalFilename());
		baseURL.append("/").append(fileName);
		InputStream stream = new ByteArrayInputStream(data.getFile().getBytes());
		s3Wrapper.upload(stream, fileName);
		String fileLocation = baseURL.append("/").append(fileName).toString();
		// save the file
		Timeline timeline = new Timeline();
		timeline.setId(uuid);
		timeline.setFileName(data.getFile().getOriginalFilename());
		timeline.setTags(data.getTags());
		timeline.setCreatedDate(commonUtil.getCurrentDate());
		repository.save(timeline);
		return fileName;
	}

	@Override
	public List<Timeline> getObjectList(String basePath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Timeline updateTags(Timeline data) {
		return null;
	}
}
