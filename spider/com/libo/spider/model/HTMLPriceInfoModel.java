package com.libo.spider.model;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;
import org.hamcrest.SelfDescribing;

import com.libo.spider.service.parser.HTMLTool;
import com.libo.tools.DateTool;
import com.libo.tools.StringTool;

public class HTMLPriceInfoModel extends HTMLBaseModel {

	// 品种名， 也作为了表名
	private String cateName;
	// 类目格式化串
	private String cateNameFormated;

	// 品种id，识别originUrl获得，用作品种唯一标识
	private String cid;

	// 是否可见
	private boolean visible;

	// 大的类别名
	private String kindTitle;

	@Override
	public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;

		this.cid = HTMLTool.getSeperatedString(originUrl);
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

	public String getCateNameFormated() {
		return cateNameFormated;
	}

	public String getKindTitle() {
		return kindTitle;
	}

	public void setKindTitle(String kindTitle) {
		this.kindTitle = kindTitle;
	}

	@Override
	public String toString() {
		return "HTMLPriceInfoModel [cateName=" + cateName
				+ ", cateNameFormated=" + cateNameFormated + ", cid=" + cid
				+ ", visible=" + visible + ", kindTitle=" + kindTitle + "]";
	}

}
