package com.libo.spider.model;

import java.util.Date;

import org.hamcrest.SelfDescribing;

import com.libo.spider.service.parser.HTMLTool;
import com.libo.tools.DateTool;
import com.libo.tools.StringTool;

public class HTMLPriceInfoModel {
	
	//品种名， 也作为了表名
	private String cateName;
	//类目格式化串
	private String cateNameFormated;
	
	//索引url
	private String originUrl;
	//更新时间
	private Date updateDate;
	//品种id，识别originUrl获得，用作品种唯一标识
	private String cid;
	//是否可见
	private boolean visible;
	//顺序
	private int torder;
	
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
		cateNameFormated = cateName.replaceAll("-", "");
	}
	public String getOriginUrl() {
		return originUrl;
	}
	public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
		
		this.cid = HTMLTool.getSeperatedString(originUrl);
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public int getTorder() {
		return torder;
	}
	public void setTorder(int order) {
		this.torder = order;
	}
	
	public String getCateNameFormated() {
		return cateNameFormated;
	}
	
	@Override
	public String toString() {
		return "HTMLPriceInfoModel [cateName=" + cateName + ", originUrl="
				+ originUrl + ", updateDate=" + DateTool.stringFromDate(updateDate)  + ", cid=" + cid
				+ ", visible=" + visible + ", order=" + torder + "]";
	}
	
	
	
}
