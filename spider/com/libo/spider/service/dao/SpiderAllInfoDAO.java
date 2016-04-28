package com.libo.spider.service.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.libo.db.MyBatisSqlSessionFactory;
import com.libo.model.AllInfoModel;

/**
 * 总信息DAO
 * @author libo
 *
 */
public class SpiderAllInfoDAO {
	
	/**
	 * 查询所有的站点信息
	 */
	public List<AllInfoModel> findAllInfoList() {
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		List<AllInfoModel> list = session.selectList(SpiderAllInfoDAO.class.getName() + ".findAllInfoList");
		session.close();
		return list;
	}
	
}
