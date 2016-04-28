package com.libo.spider.config;

public class SpiderConfig {
	
	//缓存有效时间(分钟)
	public static final int Catch_Avaible_Minutes = 60*12;
	
	//默认编码
	public static final String defaultEncoding = "UTF-8";
	
	//缓存目录
	public static final String tempFileDir = "/Users/libo/Desktop/log/";
	
	//多重解析时网络间隔(ms)
	public static final int timeInterval = 5000;
	
	//是否强制更新所有数据
	public static final boolean forceUpdate = false;
	
}
