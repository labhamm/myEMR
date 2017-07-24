package com.hackathon.vault.util;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * {@code VaultFileUtils} could be used as utility at file processing as
 * <p>
 * Final file name.
 * 
 * @author vimal.singh@philips.com
 *
 */
@Component
public class VaultFileUtils {

	/**
	 * Gets the file name.
	 *
	 * @param originalFileName
	 *            the file name
	 * @return the file name
	 */
	public String getFileName(String uuid, String originalFileName) {
		StringBuilder fileName = new StringBuilder(uuid).append(getFileExtention(originalFileName));
		return fileName.toString();
	}

	/**
	 * Gets the file extention.
	 *
	 * @param fileName
	 *            the file name
	 * @return the file extention
	 */
	private String getFileExtention(String fileName) {
		String extention = fileName.substring(fileName.lastIndexOf('.') + 1);
		return StringUtils.isEmpty(extention) ? StringUtils.EMPTY : "." + extention;
	}
}
