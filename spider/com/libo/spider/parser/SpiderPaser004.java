package com.libo.spider.parser;

import java.util.Date;

import org.htmlparser.nodes.AbstractNode;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;

import com.libo.service.SpringContext;
import com.libo.spider.model.HTMLContentModel;
import com.libo.spider.model.HTMLGoodFutureModel;
import com.libo.spider.service.dao.SpiderGoodFutureDAO;
import com.libo.spider.service.parser.HTMLPaserUtil;
import com.libo.spider.service.parser.HTMLTool;
import com.libo.tools.XLog;

/**
 * 现期表
 * @author libo
 *
 */

public class SpiderPaser004 implements SpiderPaserInterface {

	@Override
	public void parser(String htmlString, HTMLContentModel info) throws Exception{
		
		NodeList list = HTMLPaserUtil.parserTags(htmlString, TableRow.class, "bgcolor", "#fafdff");
		
		NodeIterator it = list.elements();
		int index = 0;
		String tradingExchangeName = "上海期货交易所";
		
		while(it.hasMoreNodes()) {
			
			NodeList tdList = HTMLPaserUtil.parserTags(it.nextNode().toHtml(), TableColumn.class, null, null);
			
			LinkTag titleTag = (LinkTag) HTMLPaserUtil.parserTag(tdList.elementAt(0).toHtml(), LinkTag.class, null, null);
			String goodName = titleTag.getLinkText().trim();
			String goodLink = titleTag.extractLink();
			
			String goodPrice =  HTMLTool.filterForPuttyString(tdList.elementAt(1).getFirstChild().getText());
			
			String nearCode =  HTMLTool.filterForPuttyString(tdList.elementAt(2).getFirstChild().getText());
			String nearPrice = HTMLTool.filterForPuttyString(tdList.elementAt(3).getFirstChild().getText());
			
			NodeList nearGapList =  HTMLPaserUtil.parserTags(tdList.elementAt(4).toHtml(), TextNode.class, null, null);
			String nearGapPrice = nearGapList.elementAt(1).getText().trim();
			String nearGapPercent = nearGapList.elementAt(2).getText().trim();
			
			String mainCode =  HTMLTool.filterForPuttyString(tdList.elementAt(7).getFirstChild().getText());
			String mainPrice = HTMLTool.filterForPuttyString(tdList.elementAt(8).getFirstChild().getText());
			
			NodeList mainGapList =  HTMLPaserUtil.parserTags(tdList.elementAt(9).toHtml(), TextNode.class, null, null);
			String mainGapPrice = mainGapList.elementAt(1).getText().trim();
			String mainGapPercent = mainGapList.elementAt(2).getText().trim();	
			
			HTMLGoodFutureModel model = new HTMLGoodFutureModel(goodName, goodPrice, nearCode, nearPrice, nearGapPrice, nearGapPercent, mainCode, mainPrice, mainGapPrice, mainGapPercent, tradingExchangeName, goodLink, index, new Date());
			XLog.logger.info("\n" + model);
			
			SpiderGoodFutureDAO dao = (SpiderGoodFutureDAO) SpringContext.getBean("goodFuture");
			dao.updateGoodFuture(model);
			
			index++;
			if (index == 14) {
				tradingExchangeName = "郑州商品交易所";
			}else if(index == 29) {
				tradingExchangeName = "大连商品交易所";
			}
		}
	}

}
