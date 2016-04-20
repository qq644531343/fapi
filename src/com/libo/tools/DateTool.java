package com.libo.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTool {
	
	private static DateFormat df =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static DateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static Date dateFromString(String time) {
		if (!(time instanceof String) || time.length() == 0) {
			return null;
		}
		try {
			if (time.length() == 10) {
				return dayFormat.parse(time);
			}
			return df.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	} 
	
	public static String stringFromDate(Date date) {
		return df.format(date);
	}
	
	public static String stringDayFromDate(Date date) {
		return dayFormat.format(date);
	}
	
	public static Date dateFromMillis(long millis) {
		return new Date(millis);
	}
}
