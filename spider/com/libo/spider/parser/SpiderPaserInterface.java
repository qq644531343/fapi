package com.libo.spider.parser;

import com.libo.spider.model.HTMLContentModel;

public interface SpiderPaserInterface {
	public void parser(String htmlString, HTMLContentModel info) throws Exception;
}
