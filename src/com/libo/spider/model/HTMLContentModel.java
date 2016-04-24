package com.libo.spider.model;

import java.util.Date;

import com.libo.tools.DateTool;
import com.libo.tools.FileTool;
import com.libo.tools.StringTool;

/**
 * 用于临时承载html内容
 * @author libo
 *
 */

public class HTMLContentModel {
	
	String tid;		//类目id
	String encoding;
	Date   requestDate;
	String filePath;
	long contentLength;
	String originUrl;
	String userinfo;    //用户识别信息，用于处理结果返回
	
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getOriginUrl() {
		return originUrl;
	}
	public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
	}
	
	public long getContentLength() {
		return contentLength;
	}
	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}
	
	public String getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(String userinfo) {
		this.userinfo = userinfo;
	}
	
	@Override
	public String toString() {
		return "HTMLContentModel [tid="+ tid + ", encoding=" + encoding + ", requestDate="
				+ DateTool.stringFromDate(requestDate) + ", filePath=" + filePath +", length:" + FileTool.fileSize(contentLength) +", originUrl="
				+ originUrl + "]";
	}
	
	
}
