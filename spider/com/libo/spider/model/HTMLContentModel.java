package com.libo.spider.model;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;
import org.apache.struts2.json.annotations.JSONFieldBridge;

import com.libo.tools.DateTool;
import com.libo.tools.FileTool;
import com.libo.tools.StringTool;
import com.sun.org.apache.bcel.internal.generic.DDIV;
import com.sun.xml.internal.ws.developer.Serialization;

/**
 * 用于临时承载html内容
 * @author libo
 *
 */

public class HTMLContentModel extends HTMLBaseModel{
	
	String tid;		//类目id
	String encoding;
	String filePath;
	long contentLength;
	Object userinfo;    //用户识别信息，用于处理结果返回
	
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

	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public long getContentLength() {
		return contentLength;
	}
	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}
	
	public Object getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(Object userinfo) {
		this.userinfo = userinfo;
	}
	
	@Override
	public String toString() {
		return "HTMLContentModel [tid="+ tid + ", encoding=" + encoding + ", requestDate="
				+ DateTool.stringFromDate(updateDate) + ", filePath=" + filePath +", length:" + FileTool.fileSize(contentLength) +", originUrl="
				+ originUrl + "]";
	}
	
	
}
