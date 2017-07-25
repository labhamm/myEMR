package com.hackathon.vault.entity;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class ObjectData {

	@NotNull(message = "File should not be null")
	private MultipartFile file;
	private List<String> tags;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "ObjectData [file=" + file + ", tags=" + tags + "]";
	}
}
