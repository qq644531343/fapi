package com.libo.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.junit.BeforeClass;

import com.libo.base.BaseHTTPBean;
import com.libo.base.MsgString;
import com.libo.model.AllInfoModel;
import com.libo.service.SpringContext;
import com.libo.spider.model.HTMLContentModel;
import com.libo.spider.service.dao.SpiderAllInfoDAO;
import com.libo.spider.service.dao.SpiderGoodFutureDAO;
import com.libo.spider.service.dao.SpiderPriceDAO;
import com.libo.spider.service.parser.HTMLGetter;
import com.libo.spider.service.parser.HTMLParser;
import com.libo.tools.StringTool;
import com.libo.tools.XLog;
import com.opensymphony.xwork2.ActionSupport;

public class TestStruts extends ActionSupport {

	private String username;

	private Object responseJson;

	@BeforeClass
	public void setup() {

	}

	public String sayHello() {
		System.out.println("hello struts: " + username);
		System.out.println("method:"
				+ ServletActionContext.getRequest().getMethod());

		SpiderAllInfoDAO dao = (SpiderAllInfoDAO) SpringContext
				.getBean("allInfo");

		this.responseJson = new BaseHTTPBean(dao.findAllInfoList());
		
		return "success";
	}

	public String sayGood() {

		SpiderGoodFutureDAO dao = (SpiderGoodFutureDAO) SpringContext
				.getBean("goodFuture");
		this.responseJson = new BaseHTTPBean(dao.findGoodFutureList());
		return SUCCESS;
	}

	public String sayinfo() {
		SpiderPriceDAO dao = (SpiderPriceDAO) SpringContext
				.getBean("priceInfo");
		this.responseJson = new BaseHTTPBean(dao.findPriceInfoList());
		return SUCCESS;
	}
	
	public String sayPrice() {
		if (StringTool.isEmpty(this.username)) {

			this.responseJson = new BaseHTTPBean(MsgString.CODE_ERROR_USERFAIL,
					"没有输入参数");
		} else {
			SpiderPriceDAO dao = (SpiderPriceDAO) SpringContext
					.getBean("priceInfo");
			String msg = getUsername();
			System.out.println("价格名：" + msg);
			
//			msg = new String(getUsername().getBytes("iso-8859-1"),"UTF-8");
			
			this.responseJson = new BaseHTTPBean(dao.findPriceList(msg));

		}
		return SUCCESS;
	}
	
	public String saySync() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				XLog.logger.info("开始同步数据");
				
				SpiderAllInfoDAO dao = (SpiderAllInfoDAO) SpringContext.getBean("allInfo");
				List<AllInfoModel> list = dao.findAllInfoList();
				for (AllInfoModel info : list) {
					HTMLContentModel model = HTMLGetter.getHTMLContentFromInfo(info);
					
					XLog.logger.info(model.toString());
					
					HTMLParser.parser(model);
				}
				
				XLog.logger.info("所有数据同步完毕");
			}
		}).start();
		
		this.responseJson = new BaseHTTPBean();
		return SUCCESS;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Object getResponseJson() {
		return responseJson;
	}

	public void setResponseJson(Object responseJson) {
		this.responseJson = responseJson;
	}

}
