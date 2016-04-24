package com.libo.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.security.MD5Encoder;

public class StringTool {
	
	private static MessageDigest mMessageDigest = null;
	
	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式  
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式  
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式  
    private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符  
	
	static {
		try {
			mMessageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	//首字母转小写
	public static String toLowerCaseFirstOne(String s) {
		if (Character.isLowerCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder())
					.append(Character.toLowerCase(s.charAt(0)))
					.append(s.substring(1)).toString();
	}

	// 首字母转大写
	public static String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder())
					.append(Character.toUpperCase(s.charAt(0)))
					.append(s.substring(1)).toString();
	}
	
	public static boolean isEmpty(String string) {
		return StringUtils.isEmpty(string);
	}
	
	public static boolean isNotEmpty(String string) {
		return StringUtils.isNotEmpty(string);
	}
	
	 /** 
     * @param htmlStr 
     * @return 
     *  删除Html标签 
     */  
    public static String delHTMLTag(String htmlStr) {  
    	
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);  
        Matcher m_script = p_script.matcher(htmlStr);  
        htmlStr = m_script.replaceAll(""); // 过滤script标签  
  
        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);  
        Matcher m_style = p_style.matcher(htmlStr);  
        htmlStr = m_style.replaceAll(""); // 过滤style标签  
  
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);  
        Matcher m_html = p_html.matcher(htmlStr);  
        htmlStr = m_html.replaceAll(""); // 过滤html标签  
  
        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);  
        Matcher m_space = p_space.matcher(htmlStr);  
        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签  
        htmlStr = htmlStr.trim();
        htmlStr = htmlStr.replaceAll("&nbsp;", "");  
        
        return htmlStr.trim(); // 返回文本字符串  
    }  
    
	public static String MD5(String string) {
		if (string == null) {
			return null;
		}
		return MD5(string.getBytes());
	}
	
	public static String MD5(byte[] bytes) {
		if (mMessageDigest != null && bytes != null) {
			synchronized (StringTool.class) {
				mMessageDigest.update(bytes);
				return bytesToHexString(mMessageDigest.digest());
			}
		}
		return null;
	}
	
	
	/**
	 * 获取文件的MD5码
	 * 
	 * @param file
	 * @return
	 */
	public static String getMD5(File file) {
		if (mMessageDigest != null) {
			FileInputStream fis = null;
			MappedByteBuffer byteBuffer = null;
			try {
				fis = new FileInputStream(file);
				FileChannel channel = fis.getChannel();
				byteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0,
						file.length());
				synchronized (StringTool.class) {
					mMessageDigest.update(byteBuffer);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (byteBuffer != null) {
					byteBuffer.clear();
				}
			}
			return bytesToHexString(mMessageDigest.digest());
		}
		return null;
	}
	
	private static String bytesToHexString(byte[] bytes) {
		if (bytes != null) {
			StringBuffer stringBuffer = new StringBuffer();
			for (int i = 0; i < bytes.length; i++) {
				int v = bytes[i] & 0xFF;
				String hv = Integer.toHexString(v);
				if (hv.length() < 2) {
					stringBuffer.append(0);
				}
				stringBuffer.append(hv);
			}
			return stringBuffer.toString();
		}
		return null;
	}
	
	
}
