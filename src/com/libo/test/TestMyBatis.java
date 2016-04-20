package com.libo.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.BeforeClass;
import org.junit.Test;

import com.libo.db.MyBatisSqlSessionFactory;
import com.libo.model.AllInfoModel;
import com.libo.tools.XLog;

public class TestMyBatis {
	
	@BeforeClass
	public static void  setup () {
		XLog.setup();
	}

	public List<AllInfoModel> findAllStudents() {
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		List<AllInfoModel> list = session.selectList("com.libo.test.TestMapper" + ".findAllStudents");
		session.close();
		return list;
	}
	
	@Test
	public void findAllInfo () {
		for(AllInfoModel info : this.findAllStudents()) {
			XLog.logger.info(info);
		}
		XLog.logger.error("error!!!");
	}
	
}
