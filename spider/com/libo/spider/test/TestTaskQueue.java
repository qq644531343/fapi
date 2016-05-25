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

public class TestTaskQueue  implements HTTPTaskDelegate{
	
	@BeforeClass
	public static void setup() {
//		XLog.setup();
//		SpringContext.getContext();
//		MyBatisSqlSessionFactory.getSqlSessionFactory();
	}
	
	@Test
	public void testqueue() throws InterruptedException
	{
		HTTPTaskQueue queue = new HTTPTaskQueue();
		queue.setDelegate(this);
		
		for(int i =0 ; i < 5000; i++) {
			String urlString = "http://taobao.com";
			HTTPTaskObject task = new HTTPTaskObject(urlString);
			queue.addTask(task);
		}
		
		Thread.sleep(999999999);
	}

	@Override
	public void taskBegin(HTTPTaskObject task) {
		System.out.println("Begin:" + task.getUrlString());
	}

	@Override
	public void taskEnd(HTTPTaskObject task,  CloseableHttpResponse response) {
		System.out.println("End:" + task.getUrlString());

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent(), "utf-8"));
			
			String htmlString = IOUtils.toString(reader);
			System.out.println(htmlString);
			reader.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {

		}
		
	}
	
}
