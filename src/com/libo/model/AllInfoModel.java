package com.libo.model;

import java.util.Date;

import com.libo.tools.DateTool;

public class AllInfoModel {
	
	private String tid;
	private String ttable;
	private String tname;
	private int ttype;
	private String ttypename;
	private int torder;
	private String turl;
	private int visible;
	private int isDelete;
	private Date updateDate;
	
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getTtable() {
		return ttable;
	}
	public void setTtable(String ttable) {
		this.ttable = ttable;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public int getTtype() {
		return ttype;
	}
	public void setTtype(int ttype) {
		this.ttype = ttype;
	}
	public String getTypename() {
		return ttypename;
	}
	public void setTypename(String typename) {
		this.ttypename = typename;
	}
	public int getTorder() {
		return torder;
	}
	public void setTorder(int torder) {
		this.torder = torder;
	}
	public String getTurl() {
		return turl;
	}
	public void setTurl(String turl) {
		this.turl = turl;
	}
	public int getVisible() {
		return visible;
	}
	public void setVisible(int visible) {
		this.visible = visible;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	
	@Override
	public String toString() {
		return "AllInfoModel [tid=" + tid + ", ttable=" + ttable + ", tname="
				+ tname + ", ttype=" + ttype + ", typename=" + ttypename
				+ ", torder=" + torder + ", turl=" + turl + ", visible="
				+ visible + ", isDelete=" + isDelete + ", updateDate="
				+ DateTool.stringFromDate(updateDate) + "]";
	}
	
}
