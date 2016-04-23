package com.libo.spider.service;

import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;
import org.apache.el.parser.Node;
import org.hamcrest.core.IsInstanceOf;
import org.htmlparser.nodes.AbstractNode;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;

import com.libo.service.SpringContext;
import com.libo.spider.model.HTMLContentModel;
import com.libo.spider.parser.SpiderParserGetter;
import com.libo.spider.parser.SpiderPaserInterface;
import com.sun.javafx.image.impl.IntArgb;

public class HTMLParser {
	
	public static void parser(HTMLContentModel model) {
		
		if (model == null) {
			return ;
		}
		
		try {
			
			String htmlString = IOUtils.toString(new FileInputStream(model.getFilePath()));
			SpiderParserGetter getter = (SpiderParserGetter) SpringContext.getContext().getBean("spiderGetter");
			SpiderPaserInterface sp = getter.getSpider(model.getTid());
			sp.parser(htmlString, model);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
