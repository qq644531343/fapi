package com.libo.spider.service.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.libo.db.MyBatisSqlSessionFactory;
import com.libo.spider.config.SpiderConfig;
import com.libo.spider.model.HTMLPriceInfoModel;
import com.libo.spider.model.HTMLPriceModel;

/**
 * 商品价格DAO
 * @author libo
 *
 */
public class SpiderPriceDAO {
	
	//----------------价格信息
	
	/**
	 * 批量填充价格信息
	 */
	public void updatePriceInfos(List<HTMLPriceInfoModel> list) {
		SqlSession session = MyBatisSqlSessionFactory.openSession();

		for (HTMLPriceInfoModel model : list) {
			Object object = session.selectOne(this.getClass().getName() + ".findPriceInfoById", model.getCid());
			if (object != null) {
				if (SpiderConfig.forceUpdate) {
					session.update(this.getClass().getName() + ".updatePriceInfo", model);
				}
			}else {
				session.insert(this.getClass().getName() + ".addPriceInfo", model);
			}
		}
		session.commit();
		session.close();
	}
	
	/**
	 * 更新或增加一条价格信息
	 */
	public void updatePriceInfo(HTMLPriceInfoModel model) {
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		Object object = session.selectOne(this.getClass().getName() + ".findPriceInfoById", model.getCid());
		if (object != null) {
			if (SpiderConfig.forceUpdate) {
				session.update(this.getClass().getName() + ".updatePriceInfo", model);
			}
		}else {
			session.insert(this.getClass().getName() + ".addPriceInfo", model);
		}
		session.commit();
		session.close();
	}
	
	public HTMLPriceInfoModel findPriceInfoModel(String cateName) {
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		HTMLPriceInfoModel object = session.selectOne(this.getClass().getName() + ".findPriceInfoByName", cateName);
		session.close();
		return object;
	}
	
	public List<HTMLPriceInfoModel> findPriceInfoList() {
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		List<HTMLPriceInfoModel> list = session.selectList(this.getClass().getName() + ".findPriceInfoList");
		session.close();
		return list;
	}
	
	//-----------------价格数据
	
	public void updatePriceData(HTMLPriceModel model) {
		
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		
		session.update(this.getClass().getName() + ".createPriceTable", model);
		HTMLPriceModel object = session.selectOne(this.getClass().getName() + ".findPriceByModel", model);
		
		if (object == null) {
			session.insert(this.getClass().getName() + ".addPrice", model);
		}
		session.commit();
		session.close();
	}
	
	public List<HTMLPriceModel> findPriceList(String cateNameFormated) {
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		List<HTMLPriceModel> list = session.selectList(this.getClass().getName() + ".findPriceList",cateNameFormated);
		session.close();
		return list;
	}
	
}

