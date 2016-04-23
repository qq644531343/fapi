package com.libo.spider.test;

import org.htmlparser.util.NodeList;
import org.junit.BeforeClass;
import org.junit.Test;

import com.libo.model.AllInfoModel;
import com.libo.spider.model.HTMLContentModel;
import com.libo.spider.service.HTMLGetter;
import com.libo.spider.service.HTMLParser;
import com.libo.tools.XLog;

public class TestSpiderGet {
	
	@BeforeClass
	public static void setup() {
		XLog.setup();
	}
	
	@Test
	public void testGet () {
		
		AllInfoModel info  = new AllInfoModel();
		info.setTurl("http://www.100ppi.com/sf/");
		info.setTid("004");
		
		HTMLContentModel model = HTMLGetter.getHTMLContentFromInfo(info);
		XLog.logger.debug(model.toString());
		
		HTMLParser.parser(model);
	}
	
	@Test
	public void TestRead() {
		HTMLContentModel model = new HTMLContentModel();
		model.setTid("004");
		model.setFilePath("/Users/libo/Desktop/log/4728dbf63d2667185ecbb6cdab1bae25");
		
		HTMLParser.parser(model);
	}
	
}
