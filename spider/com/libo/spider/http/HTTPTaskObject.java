package com.libo.spider.http;

public class HTTPTaskObject {
	
	private String urlString;

	public String getUrlString() {
		return urlString;
	}

	public void setUrlString(String urlString) {
		this.urlString = urlString;
	}

	public HTTPTaskObject(String urlString) {
		super();
		this.urlString = urlString;
	}
	
}
