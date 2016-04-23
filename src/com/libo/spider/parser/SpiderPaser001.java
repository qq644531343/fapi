package com.libo.spider.parser;

import org.apache.struts2.views.jsp.TextTag;
import org.htmlparser.Node;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.tags.DefinitionListBullet;

import com.libo.spider.model.HTMLContentModel;
import com.libo.spider.service.HTMLPaserUtil;
import com.libo.tools.XLog;

/**
 * 商品站
 * @author libo
 *
 */

public class SpiderPaser001 implements SpiderPaserInterface {

	@Override
	public void parser(String htmlString, HTMLContentModel info) throws Exception {
		
		NodeList pdaoList = HTMLPaserUtil.parserTags(htmlString, Div.class, "class", "p-dao");
		
		if (pdaoList.size() == 3) {
			
			String[] blockNames = new String[]{"大宗商品榜", "期货交易所", "电子盘市场"};
			
			for(int i = 0; i < pdaoList.size(); i++) {
				
				Node pdaoNode = pdaoList.elementAt(i);
				XLog.logger.info("版块:" + blockNames[i]);
				
				NodeList temp = HTMLPaserUtil.parserTags(pdaoNode.toHtml(), DefinitionListBullet.class, null, null);
				NodeIterator it = temp.elements();
				while(it.hasMoreNodes()) {
					Node node = it.nextNode();
					LinkTag link = (LinkTag) HTMLPaserUtil.parserTag(node.toHtml(), LinkTag.class, null, null);
					
					if (node instanceof TagNode 
						&&  "blues".equals(((TagNode) node).getAttribute("class")) 
						|| (link != null && "blues".equals(link.getAttribute("class")))) 
					{
						NodeList titles = HTMLPaserUtil.parserTags(node.toHtml(), TextNode.class, null, null);
						TextNode title = (TextNode) titles.elementAt(0);
						if(title.getText().trim().length() > 0) {
							System.out.println(title.getText());
						}else {
							title = (TextNode) titles.elementAt(1);
							System.out.println(title.getText());
						}
					}else {
						if (link != null) {
							System.out.println("---" + link.getLinkText() + " " + link.extractLink());
						}
					}
				}
			}
			
		}else {
			XLog.logger.error("解析出现错误，预期p-dao为3个，实际为:" + pdaoList.size());
		}
		
	}

}
