package com.stc.assessments.response;

public class ResponseFile {
	private String name;
	private String url;

	public ResponseFile(String name, String url) {
		this.name = name;
		this.url = url;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}