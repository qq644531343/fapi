package com.libo.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.libo.db.MyBatisSqlSessionFactory;
import com.libo.service.SpringContext;
import com.libo.tools.XLog;

public class ServerStartupListener  implements ServletContextListener{
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		XLog.setup();
		XLog.logger.info("服务器部署");
		SpringContext.getContext();
		MyBatisSqlSessionFactory.getSqlSessionFactory();
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
		XLog.logger.info("服务器停止服务");
	}
}
