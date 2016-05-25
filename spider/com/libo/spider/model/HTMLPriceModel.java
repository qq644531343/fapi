package com.libo.spider.model;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.libo.tools.DateTool;
import com.libo.tools.StringTool;

/**
 * 存放价格数据
 * @author libo
 *
 */
public class HTMLPriceModel extends HTMLBaseModel{
	
	//对应的价格信息id
	private String cid;
	
	//类目
	private String cateName;
	//类目格式化串
	private String cateNameFormated;

	//公司
	private String company;
	
	//规格、价格单位
	private String spec;
	
	//价格
	private String price;
	
	//产品类型
	private String priceType;
	
	//发布时间
	private String pubTime;
	
	//报价地区
	private String address;
	
	public HTMLPriceModel() 
	{
		super();
	}
	
	public HTMLPriceModel(String cid, String cateName, String company,
			String spec, String price, String priceType, String pubTime,
			Date updateDate, String address) {
		super();
		this.cid = cid;
		this.cateName = cateName;
		cateNameFormated = cateName.replaceAll("-", "");
		cateNameFormated = cateNameFormated.replaceAll(",", "");
		cateNameFormated = cateNameFormated.replaceAll("()", "");
		this.company = company;
		this.spec = spec;
		this.price = price;
		this.priceType = priceType;
		this.pubTime = pubTime;
		this.updateDate = updateDate;
		this.address = address;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
		cateNameFormated = cateName.replaceAll("-", "");
		cateNameFormated = cateNameFormated.replaceAll(",", "");
		cateNameFormated = cateNameFormated.replaceAll("()", "");
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getPubTime() {
		return pubTime;
	}

	public void setPubTime(String pubTime) {
		this.pubTime = pubTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCateNameFormated() {
		return cateNameFormated;
	}

	@Override
	public String toString() {
		return "HTMLPriceModel [cid=" + cid + ", cateName=" + cateName
				+ ", company=" + company + ", spec=" + spec + ", price="
				+ price + ", priceType=" + priceType + ", pubTime=" + pubTime
				+ ", updateDate=" + DateTool.stringFromDate(updateDate) + ", address=" + address + "]";
	}
	
	
	
}
