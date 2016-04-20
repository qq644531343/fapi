package com.libo.tools;

import org.apache.log4j.Logger;

public class XLog {
	
	public static Logger logger = null;

	public static void setup() {
		try {
//			URL path =ClassLoader.getSystemResource("log4j.properties");
//			PropertyConfigurator
//					.configure(path.getFile());
			logger = Logger.getLogger(XLog.class);
			logger.info("log4j完成初始化");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
