package com.libo.spider.test;

import java.util.List;

import javax.swing.Spring;

import org.junit.BeforeClass;
import org.junit.Test;

import com.libo.db.MyBatisSqlSessionFactory;
import com.libo.model.AllInfoModel;
import com.libo.service.SpringContext;
import com.libo.spider.model.HTMLContentModel;
import com.libo.spider.service.dao.SpiderAllInfoDAO;
import com.libo.spider.service.parser.HTMLGetter;
import com.libo.spider.service.parser.HTMLParser;
import com.libo.tools.XLog;

public class TestSpiderGet {
	
	@BeforeClass
	public static void setup() {
		XLog.setup();
		SpringContext.getContext();
		MyBatisSqlSessionFactory.getSqlSessionFactory();
	}
	
	@Test
	public void testGet () {
		
		AllInfoModel info  = new AllInfoModel();
//		info.setTurl("http://www.100ppi.com/sf/");
//		info.setTid("004");
		
//		info.setTurl("http://www.100ppi.com/ppi/");
//		info.setTid("001");
		
		info.setTurl("http://www.100ppi.com/price/");
		info.setTid("002");
		
		HTMLContentModel model = HTMLGetter.getHTMLContentFromInfo(info);
		XLog.logger.debug(model.toString());
		
		HTMLParser.parser(model);
	}
	
	@Test
	public void TestRead() {
		HTMLContentModel model = new HTMLContentModel();
		model.setTid("004");
		model.setFilePath("/Users/libo/Desktop/log/4728dbf63d2667185ecbb6cdab1bae25");
		
		model.setFilePath("/Users/libo/Desktop/log/9ec0fd6da7014c185281edf71714544c");
		model.setTid("001");
		
		HTMLParser.parser(model);
	}
	
	@Test
	public void testSearch() {
		
		String string = "http://www.100ppi.com/price/?f=search&c=product&terms=皮棉";
		
		AllInfoModel info  = new AllInfoModel();
		info.setTurl(string);
		info.setTid("002t");
		
		HTMLContentModel model = HTMLGetter.getHTMLContentFromInfo(info);
		XLog.logger.debug(model.toString());
		model.setUserinfo("search");
		
		HTMLParser.parser(model);
	}
	
	@Test
	//测试整个体系的运转
	public void testAllDataGetter () {
		SpiderAllInfoDAO dao = (SpiderAllInfoDAO) SpringContext.getBean("allInfo");
		List<AllInfoModel> list = dao.findAllInfoList();
		for (AllInfoModel info : list) {
			HTMLContentModel model = HTMLGetter.getHTMLContentFromInfo(info);
			
			XLog.logger.info(model.toString());
			
			HTMLParser.parser(model);
		}
	}
	
}
