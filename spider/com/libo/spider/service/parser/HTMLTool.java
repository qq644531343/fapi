package com.libo.spider.service.parser;

import com.libo.tools.StringTool;

public class HTMLTool {
	
	public static String filterForPuttyString(String string) {
		if (StringTool.isEmpty(string)) {
			return null;
		}
		String plainText = StringTool.delHTMLTag(string);
		plainText = plainText.replaceAll("生意社：", "");
		plainText = plainText.replaceAll("生意社", "");
		return plainText;
	}
	
	/**
	 * 获取url中被横线分割的id
	 * @param originUrl
	 * @return
	 */
	public static String getSeperatedString(String originUrl) {
		
		String cid = null;
		if (StringTool.isNotEmpty(originUrl)) {
			String[] strings = originUrl.split("-");
			if (strings != null && strings.length >= 3) {
				cid = strings[1];
			}
		}
		return  cid;
	}
}
