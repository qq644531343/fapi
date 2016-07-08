package com.libo.spider.http;

public class HTTPTaskObject {
	
	private String urlString;
	
	private Object userinfo;
	
	private String taskGroup;

	public String getTaskGroup() {
		return taskGroup;
	}

	public void setTaskGroup(String taskGroup) {
		this.taskGroup = taskGroup;
	}

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

	public HTTPTaskObject(String urlString, Object userinfo, String taskGroup) {
		super();
		this.urlString = urlString;
		this.userinfo = userinfo;
		this.taskGroup = taskGroup;
	}

	public Object getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(Object userinfo) {
		this.userinfo = userinfo;
	}

	@Override
	public String toString() {
		return "HTTPTaskObject [urlString=" + urlString + ", userinfo="
				+ userinfo + ", taskGroup=" + taskGroup + "]";
	}
	
	
}
