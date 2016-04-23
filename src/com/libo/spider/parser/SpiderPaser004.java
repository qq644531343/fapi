package com.libo.spider.parser;

import org.htmlparser.nodes.AbstractNode;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;

import com.libo.spider.model.HTMLContentModel;
import com.libo.spider.service.HTMLPaserUtil;

/**
 * 现期表
 * @author libo
 *
 */

public class SpiderPaser004 implements SpiderPaserInterface {

	@Override
	public void parser(String htmlString, HTMLContentModel info) throws Exception{
		
		NodeList list = HTMLPaserUtil.parserTags(htmlString, TableRow.class, "bgcolor", "#fafdff");
		 
		list = HTMLPaserUtil.parserTags(list.toHtml(), TableColumn.class, null, null);
		
		NodeIterator it = list.elements();
		while(it.hasMoreNodes()) {
			AbstractNode node = (AbstractNode ) it.nextNode().getFirstChild();
			if (node != null) {
				if (node instanceof TextNode) {
					if (node.getText().trim().length() == 0) {

					}else 
						System.out.println(HTMLPaserUtil.filterForPuttyString(((TextNode) node).getText()));
				}else if(node instanceof LinkTag) {
					String urlString = ((LinkTag) node).extractLink();
					String txtString = ((LinkTag) node).getLinkText();
					System.out.println(HTMLPaserUtil.filterForPuttyString(txtString) + " " +
							HTMLPaserUtil.filterForPuttyString(urlString));
				}else {
					System.out.println(node.getNextSibling().toPlainTextString());
				}
			}
		}
	}

}
