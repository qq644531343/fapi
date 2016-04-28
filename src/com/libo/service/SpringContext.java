package com.libo.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.libo.tools.XLog;

public class SpringContext {
	
	private static ApplicationContext context = null;
	
	public static ApplicationContext getContext() {
		
		if (context == null) {
			context = new ClassPathXmlApplicationContext("applicationContext.xml");
			XLog.logger.info("Spring完成初始化");
		}
		return context;
	}
	
	public static void closeSpring() {
		if (context instanceof ClassPathXmlApplicationContext) {
			ClassPathXmlApplicationContext applicationContext = (ClassPathXmlApplicationContext)context;
			applicationContext.close();
			XLog.logger.info("Spring已关闭");
		}
	}
	
	public  static Object getBean(String beanId) {
		return getContext().getBean(beanId);
	}
	
}
