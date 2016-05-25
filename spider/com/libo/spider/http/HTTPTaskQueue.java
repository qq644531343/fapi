package com.libo.spider.http;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class HTTPTaskQueue {

	// 最大运行任务数
	private int maxRunCount = 2;
	// 任务执行间隔(秒)
	private int taskInterval = 3;
	// 任务反馈代理
	private HTTPTaskDelegate delegate;
	// 任务池
	private ArrayList<HTTPTaskObject> tasklist = new ArrayList<HTTPTaskObject>();

	public HTTPTaskQueue(HTTPTaskDelegate delegate) {
		this();
		this.delegate = delegate;
	}

	public HTTPTaskQueue() {
		super();
		startRunLoop();
	}

	public void addTask(HTTPTaskObject task) {
		tasklist.add(task);
	}

	private void startRunLoop() {

		Thread t1 = new Thread(new Producer());
		Thread t2 = new Thread(new Producer());

		t1.start();
		t2.start();
	}

	private synchronized HTTPTaskObject popTask() {
		if (tasklist.size() == 0) {
			return null;
		}
		return tasklist.remove(0);
	}

	// /////////////////////////////

	public int getMaxRunCount() {
		return maxRunCount;
	}

	public void setMaxRunCount(int maxRunCount) {
		this.maxRunCount = maxRunCount;
	}

	public HTTPTaskDelegate getDelegate() {
		return delegate;
	}

	public void setDelegate(HTTPTaskDelegate delegate) {
		this.delegate = delegate;
	}

	public int getTaskInterval() {
		return taskInterval;
	}

	public void setTaskInterval(int taskInterval) {
		this.taskInterval = taskInterval;
	}

	// ////////////////////
	class Producer implements Runnable {
		private HTTPTaskObject currentTask = null;

		@Override
		public void run() {
			while (true) {
				
				try {
					
					// 休息
					Thread.sleep(taskInterval * 1000);
					
					if (currentTask != null) {
						continue;
					}
					
					// 取出当前任务
					currentTask = HTTPTaskQueue.this.popTask();
					
					if(currentTask != null) {
						// 开始任务的回调
						HTTPTaskQueue.this.delegate.taskBegin(currentTask);

						// 发送请求
					    Map<String, Object> map = sendHttpRequest(currentTask);
					    
					    CloseableHttpClient client = (CloseableHttpClient)map.get("client");
						CloseableHttpResponse response = (CloseableHttpResponse)map.get("response");

						// 结束任务的回调
						HTTPTaskQueue.this.delegate.taskEnd(currentTask, response);
						
						if (client != null) {
							client.close();
						}
						if (response != null) {
							response.close();
						}
						currentTask = null;
					}	
					
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}

		private Map<String, Object> sendHttpRequest(HTTPTaskObject task) {
			if (task != null) {
				try {

					CloseableHttpClient client = null;
					CloseableHttpResponse response = null;
					
					client = HttpClientBuilder.create().build();
					HttpClientContext context = HttpClientContext.create();

					URL url1 = new URL(task.getUrlString());
					URI uri = new URI(url1.getProtocol(), url1.getHost(),
							url1.getPath(), url1.getQuery(), null);

					HttpGet get = new HttpGet(uri);
					get.setHeader(
							"User-Agent",
							"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:45.0) Gecko/20100101 Firefox/45.0");
					get.setHeader("Referer", get.getURI().getHost());

					response = client.execute(get, context);

					HashMap<String , Object> map = new HashMap<String, Object>();
					map.put("response", response);
					map.put("client", client);
					return map;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					
				}
			}
			return null;
		}
	}
}
