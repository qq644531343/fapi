package com.libo.tools;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import com.libo.base.BaseHTTPBean;

/**
 * 对json工具的一个封装
 * @author libo
 *
 */
public class JSON {
	
	private static  JsonConfig config = null;
	
	public static JsonConfig getConfig() {
		if (config == null) {
			config = new JsonConfig();
			config.setRootClass( BaseHTTPBean.class ); 
			config.setExcludes(new String[]{"cause","localizedMessage","message","stackTrace","suppressed"});
		}
		return config;
	}
	
	private JSON() {
	
	}
	
	/**
	 *  将Object转为JSONString
	 * @param obj
	 * @return
	 */
	public static String toJSONString(Object obj) {
		String str = null;
		if (obj == null) {
			str = "";
		}
		if (obj instanceof List) {
			JSONArray array = JSONArray.fromObject(obj);
			str = array.toString();
		}else if(obj instanceof String) {
			str = (String)obj;
		} else {
			
			JSONObject  jobj = JSONObject.fromObject(obj,getConfig());
			str = jobj.toString();
		}
		return str;
	}
	
	/**
	 * 将Object转为http json
	 * 会自动拼上值为正常的code和msg
	 * @param obj
	 * @return
	 */
	public static String toHTTPJSON(Object obj)
	{
		String str = null;
		if (obj == null) {
			str = JSON.toJSONString(new BaseHTTPBean());
			return str;
		}
		if (obj instanceof BaseHTTPBean) {
			str = JSON.toJSONString(obj);
			return str;
		}
		
		BaseHTTPBean bean = new BaseHTTPBean();
		bean.setData(obj);
		str = JSON.toJSONString(bean);
		return str;
	}
	
	/**
	 *  将Object和status拼接为http json
	 * @param obj
	 * @param status
	 * @return
	 */
	public static String toHTTPJSON(Object obj, BaseHTTPBean status) {
		String str = null;
		if (obj == null || status == null) {
			str = JSON.toJSONString(new BaseHTTPBean());
			return str;
		}
		status.setData(obj);
		str = JSON.toJSONString(status);
		return str;
	}
	
}
