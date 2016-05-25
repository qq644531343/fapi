package com.libo.spider.model;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

/**
 * 爬虫数据基类
 * 
 * @author libo
 * 
 */
public class HTMLBaseModel {

	// 原始url
	protected String originUrl;

	// 更新时间
	protected Date updateDate;

	// 顺序
	protected int torder;

	@JSON(serialize=false)
	public String getOriginUrl() {
		return originUrl;
	}

	public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
	}

	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getTorder() {
		return torder;
	}

	public void setTorder(int torder) {
		this.torder = torder;
	}

}
