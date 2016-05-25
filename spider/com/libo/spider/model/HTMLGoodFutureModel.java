package com.libo.spider.model;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

/**
 * 现期表
 * @author libo
 *
 */

public class HTMLGoodFutureModel extends HTMLBaseModel{
	
	//商品名
	private String goodName;
	
	//现货价
	private String goodPrice;
	
	//近月合约代码
	private String nearCode;
	
	//近月合约价格
	private String nearPrice;
	
	//近月现期价差
	private String nearGapPrice;
	
	//近月现期价差百分率
	private String nearGapPercent;
	
	//主力合约代码
	private String mainCode;
	
	//主力合约价格
	private String mainPrice;
	
	//主力现期价差
	private String mainGapPrice;
	
	//主力现期价差百分率
	private String mainGapPercent;
	
	//交易所名称
	private String tradingExchangeName;
	
	public HTMLGoodFutureModel() {
		super();
	}

	public HTMLGoodFutureModel(String goodName, String goodPrice,
			String nearCode, String nearPrice, String nearGapPrice,
			String nearGapPercent, String mainCode, String mainPrice,
			String mainGapPrice, String mainGapPercent,
			String tradingExchangeName, String originUrl, int torder, Date updateDate) {
		super();
		this.goodName = goodName;
		this.goodPrice = goodPrice;
		this.nearCode = nearCode;
		this.nearPrice = nearPrice;
		this.nearGapPrice = nearGapPrice;
		this.nearGapPercent = nearGapPercent;
		this.mainCode = mainCode;
		this.mainPrice = mainPrice;
		this.mainGapPrice = mainGapPrice;
		this.mainGapPercent = mainGapPercent;
		this.tradingExchangeName = tradingExchangeName;
		this.originUrl = originUrl;
		this.torder = torder;
		this.updateDate = updateDate;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public String getGoodPrice() {
		return goodPrice;
	}

	public void setGoodPrice(String goodPrice) {
		this.goodPrice = goodPrice;
	}

	public String getNearCode() {
		return nearCode;
	}

	public void setNearCode(String nearCode) {
		this.nearCode = nearCode;
	}

	public String getNearPrice() {
		return nearPrice;
	}

	public void setNearPrice(String nearPrice) {
		this.nearPrice = nearPrice;
	}

	public String getNearGapPrice() {
		return nearGapPrice;
	}

	public void setNearGapPrice(String nearGapPrice) {
		this.nearGapPrice = nearGapPrice;
	}

	public String getNearGapPercent() {
		return nearGapPercent;
	}

	public void setNearGapPercent(String nearGapPercent) {
		this.nearGapPercent = nearGapPercent;
	}

	public String getMainCode() {
		return mainCode;
	}

	public void setMainCode(String mainCode) {
		this.mainCode = mainCode;
	}

	public String getMainPrice() {
		return mainPrice;
	}

	public void setMainPrice(String mainPrice) {
		this.mainPrice = mainPrice;
	}

	public String getMainGapPrice() {
		return mainGapPrice;
	}

	public void setMainGapPrice(String mainGapPrice) {
		this.mainGapPrice = mainGapPrice;
	}

	public String getMainGapPercent() {
		return mainGapPercent;
	}

	public void setMainGapPercent(String mainGapPercent) {
		this.mainGapPercent = mainGapPercent;
	}

	public String getTradingExchangeName() {
		return tradingExchangeName;
	}

	public void setTradingExchangeName(String tradingExchangeName) {
		this.tradingExchangeName = tradingExchangeName;
	}

	@Override
	public String toString() {
		return "HTMLGoodFutureModel [goodName=" + goodName + ", goodPrice="
				+ goodPrice + ", nearCode=" + nearCode + ", nearPrice="
				+ nearPrice + ", nearGapPrice=" + nearGapPrice
				+ ", nearGapPercent=" + nearGapPercent + ", mainCode="
				+ mainCode + ", mainPrice=" + mainPrice + ", mainGapPrice="
				+ mainGapPrice + ", mainGapPercent=" + mainGapPercent
				+ ", tradingExchangeName=" + tradingExchangeName
				+ ", originUrl=" + originUrl + ", torder=" + torder + "]";
	}
	
}
