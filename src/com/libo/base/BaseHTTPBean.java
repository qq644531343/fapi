package com.libo.base;

import com.libo.tools.JSON;

public class BaseHTTPBean extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private String code;
	private String msg;
	private Object data;
	
	public BaseHTTPBean()
	{
		this.code = MsgString.CODE_OK;
		this.msg = MsgString.MSG_OK;
		this.data = "{}";
	}
	
	public BaseHTTPBean(String code, String msg) {
		this();
		this.code = code;
		this.msg = msg;
	}
	
	public BaseHTTPBean(Object data) {
		this();
		this.data = data;
	} 
	
	public BaseHTTPBean(Object data, String code, String msg) {
		this(code, msg);
		this.data = data;
	} 
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object object) {
		this.data = object;
	}	
	
	public String description() {
		return JSON.toJSONString(this);
	}
}
