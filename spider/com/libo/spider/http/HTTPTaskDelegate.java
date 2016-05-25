package com.libo.spider.http;

import org.apache.http.client.methods.CloseableHttpResponse;

public interface HTTPTaskDelegate {
	
	public void taskBegin(HTTPTaskObject task);
	
	public void taskEnd(HTTPTaskObject task, CloseableHttpResponse response);
}
