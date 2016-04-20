package com.libo.db;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

import com.libo.tools.XLog;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0DataSourceFactory extends UnpooledDataSourceFactory{
	
	public C3P0DataSourceFactory() {  
        this.dataSource = new ComboPooledDataSource();  
        XLog.logger.info("C3P0完成初始化");
    }  
}

