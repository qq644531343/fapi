package com.libo.spider.service.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.libo.db.MyBatisSqlSessionFactory;
import com.libo.spider.config.SpiderConfig;
import com.libo.spider.model.HTMLGoodFutureModel;
import com.libo.spider.model.HTMLPriceInfoModel;


/**
 * 现期价格DAO
 * @author libo
 *
 */
public class SpiderGoodFutureDAO {
	
	public void updateGoodFuture(HTMLGoodFutureModel model) {
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		Object object = session.selectOne(this.getClass().getName() + ".findGoodPrice", model.getGoodName());
		if (object != null) {
			if (SpiderConfig.forceUpdate) {
				session.update(this.getClass().getName() + ".updateGoodFuture", model);
			}
		}else {
			session.insert(this.getClass().getName() + ".addGoodFuture", model);
		}
		session.commit();
		session.close();
	}
	
	public List<HTMLGoodFutureModel> findGoodFutureList() {
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		List<HTMLGoodFutureModel> list = session.selectList(this.getClass().getName() + ".findGoodFutureList");
		session.close();
		return list;
	}
	
}
