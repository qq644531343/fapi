package com.libo.test;

import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.libo.tools.FileTool;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SysLogAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String, String> htmlFiles = new HashMap<String, String>();
	private Map<String, String> logFiles = new HashMap<String, String>();
	
	public String logs() {
		setupListFiles();
		ActionContext.getContext().put("htmlfiles", htmlFiles);
		ActionContext.getContext().put("logfiles", logFiles);
		return SUCCESS;
	}
	
	private void setupListFiles() {
		
		String dir = FileTool.getProjectPath() + "../fapilogs/";
		File dirFile = new File(dir);
		if (!dirFile.exists()) {
			dirFile.mkdir();
		}

		File[] jfiles = dirFile.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				if (pathname.getName().contains(".html")) {
					return true;
				}
				return false;
			}
		});
		for (int i = 0; i < jfiles.length; i++) {
			File f = jfiles[i];
			htmlFiles.put(f.getName(), "/fapilogs/" + f.getName());
		}

		File[] mfiles = dirFile.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				if (pathname.getName().endsWith(".log") || pathname.getName().endsWith(".txt")) {
					return true;
				}
				return false;
			}
		});
		for (int i = 0; i < mfiles.length; i++) {
			File f = mfiles[i];
			logFiles.put(f.getName(), "/fapilogs/" + f.getName());
		}
	}
	
}
