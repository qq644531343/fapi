package com.libo.spider.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SpiderConfig {
	
	static{
		synchronized (SpiderConfig.class) {
			loadConfig();
		}
	}
	
	//缓存有效时间(分钟)
	public static  int Catch_Avaible_Minutes;
	
	//默认编码
	public static String defaultEncoding;
	
	//缓存目录
	public static String tempFileDir = "/Users/libo/Desktop/log/";
	
	//多重解析时网络间隔(ms)
	public static int timeInterval;
	
	//是否强制更新所有数据
	public static boolean forceUpdate;
	
	public static void loadConfig() {
		try {
			
			Properties properties = new Properties();
			properties.load(SpiderConfig.class.getClassLoader().getResourceAsStream("/config.properties"));
			
			Catch_Avaible_Minutes = Integer.parseInt(properties.getProperty("Catch_Avaible_Minutes"));
			defaultEncoding = properties.getProperty("defaultEncoding");
			tempFileDir = properties.getProperty("tempFileDir");
			timeInterval = Integer.parseInt(properties.getProperty("timeInterval"));
			forceUpdate = Boolean.parseBoolean(properties.getProperty("forceUpdate"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Map<String, String> getConfigMap() {

		Map<String, String> map = new HashMap<String, String>();
		map.put("Catch_Avaible_Minutes", Catch_Avaible_Minutes + "");
		map.put("defaultEncoding", defaultEncoding + "");
		map.put("tempFileDir", tempFileDir + "");
		map.put("timeInterval", timeInterval + "");
		map.put("forceUpdate", forceUpdate + "");
		return map;
	}
	
	
}
