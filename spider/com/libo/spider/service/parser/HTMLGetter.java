package com.libo.spider.service.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.libo.model.AllInfoModel;
import com.libo.spider.config.SpiderConfig;
import com.libo.spider.http.HTTPTaskDelegate;
import com.libo.spider.http.HTTPTaskObject;
import com.libo.spider.http.HTTPTaskQueue;
import com.libo.spider.model.HTMLContentModel;
import com.libo.tools.FileTool;
import com.libo.tools.StringTool;
import com.libo.tools.XLog;

/**
 * 此类主要获取html内容
 * 
 * @author libo
 * 
 */

public class HTMLGetter implements HTTPTaskDelegate {
	
	private HTTPTaskQueue taskQueue;
	
	private static HTMLGetter instance = new HTMLGetter();
	
	private HTMLGetter() {
		super();
		
		File dir = new File(SpiderConfig.tempFileDir);
		if (!dir.exists()) {
			boolean res = dir.mkdir();
			XLog.logger.info("临时目录不存在，创建临时目录 result:" + res);
		}else {
			XLog.logger.info("临时目录HTML: " + dir.toString());
		}
		
		taskQueue = new HTTPTaskQueue();
		taskQueue.setDelegate(instance);
	}
	
	public static HTMLGetter sharedInstance()
	{
		return instance;
	}

	public static HTMLContentModel getHTMLContentFromInfo(AllInfoModel info) {

		XLog.logger.info("\n\n----------Begin To Get: " + info.getTurl());
		HTMLContentModel model = requestForUrl(info.getTurl());
		model.setTid(info.getTid());
		XLog.logger.info("\n----------End Get: " + info.getTurl() +"! ");

		return model;
	}

	private static HTMLContentModel requestForUrl(String urlString) {

		if (StringTool.isEmpty(urlString)) {
			XLog.logger.error("url为空");
			return null;
		}
		
		//判断是否应该读取缓存
		HTMLContentModel catcheModel = checkFileExsits(urlString);
		if(catcheModel != null) {
			return catcheModel;
		}

		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		try {

			client = HttpClientBuilder.create().build();
			HttpClientContext context = HttpClientContext.create();
			
			 URL url1 = new URL(urlString);   
	         URI uri = new URI(url1.getProtocol(), url1.getHost(), url1.getPath(), url1.getQuery(), null); 
			
			HttpGet get = new HttpGet(uri);
			get.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:45.0) Gecko/20100101 Firefox/45.0");
			get.setHeader("Referer",  get.getURI().getHost());
			
		    response = client.execute(get, context);

			return parse(response, urlString);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (client != null) {
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static HTMLContentModel parse(CloseableHttpResponse response,
			String urlString) {

		if (response == null) {
			XLog.logger.info("解析失败,response为空");
			return null;
		}

		XLog.logger.info("Start to Parsing...");

		String encode = SpiderConfig.defaultEncoding;
		HttpEntity entity = response.getEntity();
		long contentLength = response.getEntity().getContentLength();

		if (ContentType.getOrDefault(entity).getCharset() != null) {
			encode = ContentType.getOrDefault(entity).getCharset().name();
		}
		XLog.logger.info("encoding: " + encode);

		try {

			String filepath = SpiderConfig.tempFileDir + StringTool.MD5(urlString);

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent(), encode));
			FileOutputStream fos = new FileOutputStream(filepath);
			
			IOUtils.copy(reader, fos);
			
			if (contentLength == -1) {
				fos.flush();
				contentLength = fos.getChannel().size();
			}
			
			fos.close();
			reader.close();

			HTMLContentModel model = new HTMLContentModel();
			model.setEncoding(encode);
			model.setContentLength(contentLength);
			model.setFilePath(filepath);
			model.setOriginUrl(urlString);
			model.setUpdateDate(new Date());
			
			return model;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	private static HTMLContentModel checkFileExsits(String urlString) {
		
		String filepath = SpiderConfig.tempFileDir + StringTool.MD5(urlString);
		File file = new File(filepath);
	
		if (file != null  && file.exists()) {	
			
			Date updateDate = FileTool.getUpdateDate(file);
			long timeInterval = new Date().getTime() - updateDate.getTime();
			if (timeInterval/1000 < SpiderConfig.Catch_Avaible_Minutes*60 && file.length() > 0) {
				try {
					HTMLContentModel model = new HTMLContentModel();
					model.setEncoding(FileTool.getFilecharset(file));
					model.setContentLength(file.length());
					model.setFilePath(filepath);
					model.setOriginUrl(urlString);
					model.setUpdateDate(updateDate);
					
					XLog.logger.info("\n读取缓存成功: " + urlString);
					return model;
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
		}
		
		XLog.logger.info("\n读取缓存失败，需要请求网络: " + urlString);
		return null;
	}

	@Override
	public void taskBegin(HTTPTaskObject task) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void taskEnd(HTTPTaskObject task, CloseableHttpResponse response) {
		// TODO Auto-generated method stub
		
	}

}
