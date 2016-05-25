package com.libo.action.spider;

import java.util.HashMap;
import java.util.Map;

import com.libo.base.BaseHTTPBean;
import com.libo.spider.config.SpiderConfig;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.Key;


public class SpiderConfigAction extends ActionSupport implements ModelDriven<Map<String, String[]>>{

	private static final long serialVersionUID = -4026731707970981932L;
	private Map<String, String[]> configMap = new HashMap<String, String[]>();
	
	private Object responseJson;
	
	public String config() {
		ActionContext.getContext().getValueStack().push(SpiderConfig.getConfigMap());
		return INPUT;
	}
	
	public String configSubmit() {
		
		this.responseJson = new BaseHTTPBean();
		
		SpiderConfig.Catch_Avaible_Minutes = Integer.parseInt(configMap.get("Catch_Avaible_Minutes")[0]);
		SpiderConfig.forceUpdate = Boolean.parseBoolean(configMap.get("forceUpdate")[0]);
		SpiderConfig.timeInterval = Integer.parseInt(configMap.get("timeInterval")[0]);
		SpiderConfig.defaultEncoding = configMap.get("defaultEncoding")[0];
		SpiderConfig.tempFileDir = configMap.get("tempFileDir")[0];
		
		return SUCCESS;
	}

	public Object getResponseJson() {
		return responseJson;
	}

	public void setResponseJson(Object responseJson) {
		this.responseJson = responseJson;
	}

	@Override
	public Map<String, String[]> getModel() {
		
		return configMap;
	}

}
