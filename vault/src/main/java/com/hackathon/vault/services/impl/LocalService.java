package com.hackathon.vault.services.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.hackathon.vault.entity.ObjectData;
import com.hackathon.vault.entity.Timeline;
import com.hackathon.vault.exception.MissingResourceException;
import com.hackathon.vault.exception.ServiceException;
import com.hackathon.vault.repository.TimelineRepository;
import com.hackathon.vault.services.VaultService;
import com.hackathon.vault.util.CommonUtil;
import com.hackathon.vault.util.VaultFileUtils;

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

	@Autowired
	private TimelineRepository repository;

	@Autowired
	private CommonUtil commonUtil;

	@Autowired
	private VaultFileUtils utils;

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
	public String storeObject(ObjectData data, StringBuffer baseURL) throws ServiceException {
		String uuid = UUID.randomUUID().toString();
		String fileName = utils.getFileName(uuid, data.getFile().getOriginalFilename());
		String fileLocation = baseURL.append("/").append(fileName).toString();
		String filePath = new StringBuilder(location).append(vaultDir).append(fileName).toString();
		File file = new File(filePath);
		try {
			FileUtils.writeByteArrayToFile(file, data.getFile().getBytes());
		} catch (IOException e) {
			throw new ServiceException(e);
		}

		// save the file
		Timeline timeline = new Timeline();
		timeline.setId(uuid);
		timeline.setFileName(data.getFile().getOriginalFilename());
		timeline.setThumbnailUrl(fileLocation);
		timeline.setCreatedDate(commonUtil.getCurrentDate());
		repository.save(timeline);
		return fileLocation;
	}

	@Override
	public List<Timeline> getObjectList(String basePath) {
		List<Timeline> timelines = repository.findAll();
		return timelines;
	}

	@Override
	public Timeline updateTags(Timeline data) {
		Timeline timeline = repository.findByThumbnailUrl(data.getThumbnailUrl());
		timeline.setTags(data.getTags());
		Timeline save = repository.save(timeline);
		return save;
	}

}
