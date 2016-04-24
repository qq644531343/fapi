package com.libo.spider.parser;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import org.htmlparser.tags.Div;
import org.htmlparser.tags.HeadingTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;

import com.libo.model.AllInfoModel;
import com.libo.spider.config.SpiderConfig;
import com.libo.spider.model.HTMLContentModel;
import com.libo.spider.service.HTMLGetter;
import com.libo.spider.service.HTMLParser;
import com.libo.spider.service.HTMLPaserUtil;
import com.libo.tools.XLog;

/**
 * 价格
 * @author libo
 *
 */

public class SpiderPaser002 implements SpiderPaserInterface {

	@Override
	public void parser(String htmlString, HTMLContentModel info) throws Exception {
		
		if (info.getUserinfo() == null) {
			parseForOrigin(htmlString, info);
		
		}else if("second".equals(info.getUserinfo())) {
			parseForSecond(htmlString, info);
		}

	}
	
	//顶层解析
	private void parseForOrigin(String htmlString , HTMLContentModel info) throws Exception{
		NodeList divList = HTMLPaserUtil.parserTags(htmlString, Div.class, "class", "price-hd clearfix mb10");
		
		if (divList.size() > 0) {
			
		   CountDownLatch latch=new CountDownLatch(divList.size());
		   ArrayList<Runnable> tlist = new ArrayList<Runnable>();
			
			NodeIterator it = divList.elements();
			while (it.hasMoreNodes()) {
				Div div = (Div) it.nextNode();
				
				HeadingTag title = (HeadingTag) div.getChild(2);
				System.out.println(title.getStringText());
				
				LinkTag link = (LinkTag) div.getChild(1).getFirstChild();
				String linkUrl = "http://www.100ppi.com/price/" + link.extractLink();
				System.out.println(linkUrl);
				
				//开始二级解析
				Worker work = new Worker(latch, linkUrl, "second");
				tlist.add(work);
			}
			for (Runnable work : tlist) {
				new Thread(work).start();
			}
			latch.await();
			XLog.logger.info("二级任务执行完毕");
		}else {
			XLog.logger.error("解析失败，目标div个数为0");
		}
	}
	
	//二级解析
	private void parseForSecond(String htmlString, HTMLContentModel info) throws Exception{
		
		System.out.println("二级解析:" + info.getOriginUrl());
		
		NodeList divList = HTMLPaserUtil.parserTags(htmlString, Div.class, "class", "p_list");
		if (divList.size() > 0) {
			NodeIterator it = divList.elements();
			while (it.hasMoreNodes()) {
				NodeList linkList = HTMLPaserUtil.parserTags(it.nextNode().toHtml(), LinkTag.class, null, null);
				for(int i = 0; i < linkList.size(); i++) {
					LinkTag link = (LinkTag) linkList.elementAt(i);
					String name = link.getLinkText();
					String url = "http://www.100ppi.com/price/" + link.extractLink();
					System.out.println(name + " " + url);
					
					System.out.println("执行三级请求：" + url);
					AllInfoModel info2  = new AllInfoModel();
					info2.setTurl(url);
					info2.setTid("002t");
					
					Thread.sleep(SpiderConfig.timeInterval);
					
					HTMLContentModel model = HTMLGetter.getHTMLContentFromInfo(info2);
					HTMLParser.parser(model);
				}
			}
			
		}else {
			XLog.logger.error("解析失败，目标div个数为0");
		}
		
	}
	
	class Worker implements Runnable {
		CountDownLatch latch; 
		String		   linkUrl;
		String		   userinfo;
		
		public Worker(CountDownLatch latch, String linkUrl, String userinfo) {
			this.latch = latch;
			this.linkUrl = linkUrl;
			this.userinfo = userinfo;
		}
		
		public Worker(String linkUrl , String userinfo) {
			this.userinfo = userinfo;
			this.linkUrl = linkUrl;
		}
		
		@Override
		public void run() {
			
			AllInfoModel info  = new AllInfoModel();
			info.setTurl(linkUrl);
			info.setTid("002");
			
			HTMLContentModel model = HTMLGetter.getHTMLContentFromInfo(info);
			model.setUserinfo(userinfo);
			HTMLParser.parser(model);
			
			if (latch != null) {
				latch.countDown();
			}
	
		}
	}

}


