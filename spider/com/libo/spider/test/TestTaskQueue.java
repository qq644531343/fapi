package com.libo.spider.test;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import com.libo.db.MyBatisSqlSessionFactory;
import com.libo.service.SpringContext;
import com.libo.spider.http.HTTPTaskDelegate;
import com.libo.spider.http.HTTPTaskObject;
import com.libo.spider.http.HTTPTaskQueue;
import com.libo.tools.XLog;

interface FinishDelegate {
	void finished(String url, Object userinfo);
}

public class TestTaskQueue  implements HTTPTaskDelegate{
	
	FinishDelegate delegate;
	
	@BeforeClass
	public static void setup() {
		XLog.setup();
		SpringContext.getContext();
		MyBatisSqlSessionFactory.getSqlSessionFactory();
	}
	
	@Test
	public void testqueue() throws InterruptedException
	{
		HTTPTaskQueue queue = new HTTPTaskQueue();
		queue.setDelegate(this);
		queue.setMaxRunCount(2);
		queue.setTaskInterval(3);
		
		for(int i =0 ; i < 5000; i++) {
			String urlString = "http://www.100ppi.com/news/detail-20151028-674199.html";
			HTTPTaskObject task = new HTTPTaskObject(urlString, urlString, i/10 + "");
			queue.addTask(task);
			task.setUserinfo(i + "");
			this.delegate = new FinishDelegate() {
				
				@Override
				public void finished(String url, Object userinfo) {
					System.out.println("任务完成了：url:" + url + " userinfo:" + userinfo);
				}
			};
		}
		
		queue.startRunLoop();
		
		while (true) {
			Thread.sleep(100000);
		}
	}

	@Override
	public void taskBegin(HTTPTaskObject task) {
		XLog.logger.info("Begin:" + task.getUrlString() + "   group:" + task.getTaskGroup() + " userinfo:" + task.getUserinfo());
	}

	@Override
	public void taskEnd(HTTPTaskObject task,  CloseableHttpResponse response) {
//		System.out.println("End:" + task.getUrlString());
		if(this.delegate != null) {
			this.delegate.finished(task.getUrlString(), task.getUserinfo());
		}
		
		try {
//			BufferedReader reader = new BufferedReader(new InputStreamReader(
//					response.getEntity().getContent(), "utf-8"));
//			
//			String htmlString = IOUtils.toString(reader);
//			System.out.println(htmlString);
//			reader.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {

		}
		
	}

	@Override
	public void taskGroupFinished(String group) {
		System.out.println("----------------\ngroup:"+ group + " finished!\n-----------------");
	}
	
}
