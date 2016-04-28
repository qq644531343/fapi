package com.libo.spider.parser;

import java.util.Date;

import org.htmlparser.tags.Div;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;

import com.libo.service.SpringContext;
import com.libo.spider.model.HTMLContentModel;
import com.libo.spider.model.HTMLPriceInfoModel;
import com.libo.spider.model.HTMLPriceModel;
import com.libo.spider.service.dao.SpiderPriceDAO;
import com.libo.spider.service.parser.HTMLPaserUtil;
import com.libo.spider.service.parser.HTMLTool;
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

		if ("search".equals(info.getUserinfo())) {
			parserForSearch(htmlString, info);
		}else {
			parserForPrice(htmlString, info);
		}
	}
	
	public void parserForPrice(String htmlString, HTMLContentModel info) throws Exception{
		
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
				String spec = HTMLTool.filterForPuttyString(tdList.elementAt(3).getFirstChild().getText());
				
				Div addDiv = (Div) HTMLPaserUtil.parserTag(tdList.elementAt(4).toHtml(), Div.class, null, null);
				String address = addDiv.getStringText().trim();
				
				String pubTime = tdList.elementAt(5).getFirstChild().getText();
				
				HTMLPriceInfoModel priceInfo = (HTMLPriceInfoModel)info.getUserinfo();
				HTMLPriceModel model = new HTMLPriceModel(priceInfo.getCid(), priceInfo.getCateName(), company, spec, price, priceType, pubTime, new Date(), address);
				XLog.logger.info("\n" + model);
				
				SpiderPriceDAO dao = (SpiderPriceDAO)SpringContext.getBean("priceInfo");
				dao.updatePriceData(model);
			}
		} else {
			XLog.logger.error("解析失败，目标tr个数为0");
		}
	}
	
	public void parserForSearch(String htmlString, HTMLContentModel info) throws Exception{
		NodeList trList = HTMLPaserUtil.parserTags(htmlString, TableRow.class,
				null, null);
		
		HTMLPriceInfoModel priceInfo = null;
		
		if (trList.size() > 1) {
			trList.remove(0);
			NodeIterator it = trList.elements();
			while (it.hasMoreNodes()) {
				NodeList tdList = HTMLPaserUtil.parserTags(it.nextNode()
						.toHtml(), TableColumn.class, null, null);
				
				LinkTag titleTag = (LinkTag) HTMLPaserUtil.parserTag(tdList.elementAt(0).toHtml(), LinkTag.class, null, null);
				String type = titleTag.getLinkText().trim();
				
				Div addDiv = (Div) HTMLPaserUtil.parserTag(tdList.elementAt(1).toHtml(), Div.class, null, null);
				String company = addDiv.getStringText().trim();
				
				String spec = HTMLTool.filterForPuttyString(tdList.elementAt(2).getFirstChild().getText());
				
				String priceType = tdList.elementAt(3).getFirstChild().getText();
				String price = tdList.elementAt(4).getFirstChild().getText();
				
				String pubTime = tdList.elementAt(5).getFirstChild().getText();
				
				if (priceInfo == null) {
					SpiderPriceDAO priceDAO = (SpiderPriceDAO)SpringContext.getBean("priceInfo");
					 priceInfo = priceDAO.findPriceInfoModel(type);
				}
				
				HTMLPriceModel model = new HTMLPriceModel(priceInfo.getCid(), priceInfo.getCateName(), company, spec, price, priceType, pubTime, new Date(), null);
				
				XLog.logger.info("\n" + model);

			}
		} else {
			XLog.logger.error("解析失败，目标tr个数为0");
		}
	}

}
