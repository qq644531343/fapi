package com.libo.test;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.markdown4j.Markdown4jProcessor;

import com.libo.tools.StringTool;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class JsonListAction extends ActionSupport {

	private static final long serialVersionUID = 8648152817367235212L;

	private Map<String, String> jsonFiles = new HashMap<String, String>();
	private Map<String, String> mdFiles = new HashMap<String, String>();

	// args
	private String name;
	private String content;

	public String list() {

		setupListFiles();
		ActionContext.getContext().put("jsonfiles", jsonFiles);
		ActionContext.getContext().put("mdfiles", mdFiles);

		return "list";
	}

	public String add() {

		addFile(name, content, ".json");
		setupListFiles();
		ActionContext.getContext().put("jsonfiles", jsonFiles);
		ActionContext.getContext().put("mdfiles", mdFiles);

		return "add";
	}

	public String md() {
		try {
			String html = null;
			if ("rm".equals(content)) {
				html = content;
			}else {
				html = new Markdown4jProcessor().process(content);
			}
			addFile(name, html, ".html");
			setupListFiles();
			ActionContext.getContext().put("jsonfiles", jsonFiles);
			ActionContext.getContext().put("mdfiles", mdFiles);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "md";
	}

	private void setupListFiles() {
		String dir = ServletActionContext.getServletContext().getRealPath(
				"jsonfiles");
		File dirFile = new File(dir);
		if (!dirFile.exists()) {
			dirFile.mkdir();
		}

		File[] jfiles = dirFile.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				if (pathname.getName().endsWith(".json")) {
					return true;
				}
				return false;
			}
		});
		for (int i = 0; i < jfiles.length; i++) {
			File f = jfiles[i];
			jsonFiles.put(f.getName(), "jsonfiles/" + f.getName());
		}

		File[] mfiles = dirFile.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				if (pathname.getName().endsWith(".html")) {
					return true;
				}
				return false;
			}
		});
		for (int i = 0; i < mfiles.length; i++) {
			File f = mfiles[i];
			mdFiles.put(f.getName(), "jsonfiles/" + f.getName());
		}
	}

	private void addFile(String name, String content, String ext) {
		String dir = ServletActionContext.getServletContext().getRealPath(
				"jsonfiles");
		File dirFile = new File(dir);
		if (!dirFile.exists()) {
			dirFile.mkdir();
		}
		if (StringTool.isNotEmpty(name) && StringTool.isNotEmpty(content)) {
			FileOutputStream fos = null;
			try {
				if ("rm".equals(content)) {
					File existFile = new File(dirFile + "/" + name + ext);
					if (existFile.exists()) {
						existFile.delete();
					}
				}else {
					fos = new FileOutputStream(dirFile + "/" + name + ext);
					IOUtils.write(content, fos);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
