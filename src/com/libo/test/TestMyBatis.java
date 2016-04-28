package com.libo.test;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.BeforeClass;
import org.junit.Test;

import com.libo.db.MyBatisSqlSessionFactory;
import com.libo.model.AllInfoModel;
import com.libo.service.SpringContext;
import com.libo.spider.model.HTMLPriceInfoModel;
import com.libo.spider.model.HTMLPriceModel;
import com.libo.spider.service.dao.SpiderAllInfoDAO;
import com.libo.spider.service.dao.SpiderPriceDAO;
import com.libo.tools.XLog;

public class TestMyBatis {
	
	@BeforeClass
	public static void  setup () {
		XLog.setup();
	}
	
	@Test
	public void findAllInfo () {
		for(AllInfoModel info : this.findAllStudents()) {
			XLog.logger.info(info);
		}
		
	}
	
	public List<AllInfoModel> findAllStudents() {
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		List<AllInfoModel> list = session.selectList("com.libo.test.TestMapper" + ".findAllStudents");
		session.close();
		return list;
	}
	
	@Test
	public void testAllInfo() {
	
		SpiderAllInfoDAO dao = (SpiderAllInfoDAO) SpringContext.getBean("allInfo");
		for(AllInfoModel info : dao.findAllInfoList()) {
			XLog.logger.info(info);
		}
	}
	
	@Test
	public void testPriceInfo() {
		HTMLPriceInfoModel model = new HTMLPriceInfoModel();
		model.setCid("86");
		model.setCateName("天然香蕉");
		model.setOriginUrl("http://baidu.com");
		model.setTorder(1);
		model.setUpdateDate(new Date());
		model.setVisible(true);
		
		SpiderPriceDAO dao = (SpiderPriceDAO)SpringContext.getBean("priceInfo");
		dao.updatePriceInfo(model);
	}
	
	@Test
	public void testPrice() {
		HTMLPriceModel model = new HTMLPriceModel("86", "天然香蕉", "北京棉花", "品种：细绒棉", "11808元/吨", "市场价", "2016-4-13", new Date(), "江西省");

		SpiderPriceDAO dao = (SpiderPriceDAO)SpringContext.getBean("priceInfo");
		dao.updatePriceData(model);
	}
}
