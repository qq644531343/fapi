package com.libo.spider.http;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang.ObjectUtils.Null;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.libo.tools.StringTool;
import com.libo.tools.XLog;

public class HTTPTaskQueue {

	// 最大运行任务数
	private int maxRunCount = 2;
	// 任务执行间隔(秒)
	private int taskInterval = 2;
	// 任务反馈代理
	private HTTPTaskDelegate delegate;
	// 任务池
	private ArrayList<HashMap<String, ArrayList<HTTPTaskObject>>> tasklist = new ArrayList<HashMap<String,ArrayList<HTTPTaskObject>>>();

	public HTTPTaskQueue(HTTPTaskDelegate delegate) {
		this();
		this.delegate = delegate;
	}

	public HTTPTaskQueue() {
		super();
	}

	public synchronized void addTask(HTTPTaskObject task) {
		if (StringTool.isEmpty(task.getTaskGroup())) {
			task.setTaskGroup( "default");
		}
		for (int i = 0; i < tasklist.size(); i++) {
			HashMap<String, ArrayList<HTTPTaskObject>> map = tasklist.get(i);
			ArrayList<HTTPTaskObject> list =  map.get(task.getTaskGroup());
			if (list != null) {
				list.add(task);
				return;
			}
		}
		
		HashMap<String, ArrayList<HTTPTaskObject>> map = new HashMap<String, ArrayList<HTTPTaskObject>>();
		ArrayList<HTTPTaskObject> list =  new ArrayList<HTTPTaskObject>();
		list.add(task);
		map.put(task.getTaskGroup(), list);
		tasklist.add(map);
	}

	public void startRunLoop() {

		for (int i = 0; i < maxRunCount; i++) {
			Thread t = new Thread(new Producer());
			t.start();
			try {
				Thread.sleep(this.getTaskInterval() * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private synchronized HashMap<String, Object> popTask() {
		
		if (tasklist.size() == 0) {
			return null;
		}
		
		System.out.println("剩余任务组数:" + tasklist.size());
		
		HashMap<String, ArrayList<HTTPTaskObject>> map = tasklist.get(0);

		HashMap<String, Object> taskMap = null;		
		Iterator<Map.Entry<String, ArrayList<HTTPTaskObject>>> it = map.entrySet().iterator();
		
		while(it.hasNext()) {
			Map.Entry<String, ArrayList<HTTPTaskObject>> entry = it.next();
			ArrayList<HTTPTaskObject> list = entry.getValue();
			if (list.size() > 0) {
				taskMap = new HashMap<String, Object>();
				taskMap.put("task", list.remove(0));
			}
			if (list.size() == 0) {
				if (taskMap != null) {
					taskMap.put("groupEnd", true);
				}
				tasklist.remove(0);
			}
			if (taskMap != null) {
				break;
			}
		}
		return taskMap;
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
					HashMap<String, Object> taskMap = HTTPTaskQueue.this.popTask();
					if (taskMap == null) {
						continue;
					}
					
					currentTask = taskMap.get("task") == null ? null : (HTTPTaskObject)taskMap.get("task");
					boolean groupEnd = (taskMap.get("groupEnd") == null ? false: (boolean)taskMap.get("groupEnd"));
					
					if(currentTask != null) {
						// 开始任务的回调
						HTTPTaskQueue.this.delegate.taskBegin(currentTask);

						// 发送请求
					    Map<String, Object> map = sendHttpRequest(currentTask);
					    if (map == null) {
							XLog.logger.error("任务请求失败！url:" + currentTask);
							XLog.logger.info("进行一次重试。。。" + currentTask);
							map = sendHttpRequest(currentTask);
							if (map == null) {
								XLog.logger.info("重试失败，放弃任务。。。" + currentTask);
								currentTask = null;
								continue;
							}
						}
					    
					    CloseableHttpClient client = (CloseableHttpClient)map.get("client");
						CloseableHttpResponse response = (CloseableHttpResponse)map.get("response");

						// 结束任务的回调
						HTTPTaskQueue.this.delegate.taskEnd(currentTask, response);
						
						if (groupEnd) {
							Thread.sleep(500);
							HTTPTaskQueue.this.delegate.taskGroupFinished(currentTask.getTaskGroup());
						}
						
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
				CloseableHttpClient client = null;
				CloseableHttpResponse response = null;
				try {
					
					client = HttpClientBuilder.create().build();
					HttpClientContext context = HttpClientContext.create();

					URL url1 = new URL(task.getUrlString());
					URI uri = new URI(url1.getProtocol(), url1.getHost(),
							url1.getPath(), url1.getQuery(), null);

					HttpGet get = new HttpGet(uri);
					get.setHeader(
							"User-Agent",
							this.getUserAgent());
					get.setHeader("Referer", get.getURI().getHost());
					RequestConfig config = RequestConfig.custom().setSocketTimeout(15000).setConnectTimeout(15000).build();
					get.setConfig(config);

					response = client.execute(get, context);
					
					if (client != null && response != null) {
						HashMap<String , Object> map = new HashMap<String, Object>();
						map.put("response", response);
						map.put("client", client);
						return map;
					}else {
						return null;
					}
				} catch (Exception e) {
					e.printStackTrace();
					if (client != null) {
						try {
							client.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					if (response != null) {
						try {
							response.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					return null;
				} finally {
					
				}
			}
			return null;
		}
		
		Random random = new Random();
		private String getUserAgent() {
			int i = random.nextInt(4);
			String agent = null;
			switch (i) {
			case 0:
				agent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:44.0) Gecko/20160520 Firefox/44.0";
				break;
			case 1:
				agent = "IE/9.0 (PC; Intel Windows 10; rv:48.0) IE/9.0";
				break;
			case 2:
				agent = "IE/8.0 (PC; Intel Windows 7; rv:41.0) IE/8.0";
				break;
			case 3:
				agent = "Safari/5.0 (iPhone; iPhone 6s;) Safari/5.0";
				break;
			default:
				break;
			}
			return agent;
		}
		
	}
}

