package com.libo.spider.parser;

import org.htmlparser.tags.Div;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;

import com.libo.spider.model.HTMLContentModel;
import com.libo.spider.service.HTMLPaserUtil;
import com.libo.tools.XLog;

/**
 * 价格的三级解析器
 * 
 * @author libo
 * 
 */

public class SpiderPaser002t implements SpiderPaserInterface {

	@Override
	public void parser(String htmlString, HTMLContentModel info)
			throws Exception {

		NodeList trList = HTMLPaserUtil.parserTags(htmlString, TableRow.class,
				null, null);
		if (trList.size() > 1) {
			trList.remove(0);
			NodeIterator it = trList.elements();
			while (it.hasMoreNodes()) {
				NodeList tdList = HTMLPaserUtil.parserTags(it.nextNode()
						.toHtml(), TableColumn.class, null, null);
				
				LinkTag titleTag = (LinkTag) HTMLPaserUtil.parserTag(tdList.elementAt(0).toHtml(), LinkTag.class, null, null);
				String company = titleTag.getLinkText().trim();
				
				String priceType = tdList.elementAt(1).getFirstChild().getText();
				String price = tdList.elementAt(2).getFirstChild().getText();
				String spec = HTMLPaserUtil.filterForPuttyString(tdList.elementAt(3).getFirstChild().getText());
				
				Div addDiv = (Div) HTMLPaserUtil.parserTag(tdList.elementAt(4).toHtml(), Div.class, null, null);
				String address = addDiv.getStringText().trim();
				
				String pubTime = tdList.elementAt(5).getFirstChild().getText();
				
				System.out.println(company + ", " + priceType + ", " + price
						+ ", " + spec + ", " + address + ", " + pubTime);

			}
		} else {
			XLog.logger.error("解析失败，目标tr个数为0");
		}

	}

}
