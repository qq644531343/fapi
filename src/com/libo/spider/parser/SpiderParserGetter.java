package com.libo.spider.parser;

import com.libo.tools.StringTool;

public class SpiderParserGetter {
	public SpiderPaserInterface getSpider(String tid) throws Exception {
		if (StringTool.isEmpty(tid)) {
			return null;
		}
		
		String classPrefix = this.getClass().getPackage().getName() + ".SpiderPaser";
		Class<SpiderPaserInterface> clazz = (Class<SpiderPaserInterface>) Class.forName(classPrefix+tid);
		return clazz.newInstance();
	}
}
