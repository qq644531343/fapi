package com.libo.spider.service;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.nodes.AbstractNode;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.util.NodeList;

import com.libo.tools.StringTool;


public class HTMLPaserUtil {
	
	public static NodeList parserTags(String html, final Class<? extends AbstractNode> tagType, final String attributName, final String attributeVAlue) {
		try {
			Parser parser = new Parser();
			parser.setInputHTML(html);
			
			NodeList list = parser.parse(new NodeFilter() {
				
				public boolean accept(Node node) {
					if (node.getClass() == tagType ) {
						if (node instanceof TagNode) {
							TagNode tagNode = (TagNode)node;
							if (attributName != null && attributeVAlue != null) {
								if (attributeVAlue.equals(tagNode.getAttribute(attributName))) {
									return true;
								}else {
									return false;
								}
							}
						}
						
						return true;
					}
					return false;
				}
			});
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static TagNode parserTag(String html, final Class<? extends AbstractNode> tagType, final String attributName, final String attributeVAlue) {
		NodeList list =  parserTags(html, tagType, attributName, attributeVAlue);
		if (list.size() > 0) {
			return (TagNode)list.elementAt(0);
		}
		return null;
	}
	
	public static String filterForPuttyString(String string) {
		if (StringTool.isEmpty(string)) {
			return null;
		}
		String plainText = StringTool.delHTMLTag(string);
		plainText = plainText.replaceAll("生意社：", "");
		plainText = plainText.replaceAll("生意社", "");
		return plainText;
	}
	
}
