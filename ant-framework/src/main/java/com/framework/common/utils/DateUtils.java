package com.framework.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期处理
 */
public class DateUtils {
	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	public final static String DATE_TIME_UTC_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	public final static String DATE_TIME_UTC_PATTERN2 = "yyyy-MM-dd'T'HH:mm:ss.SSS";
	public static String formatYMD(Date date) {
		return format(date, DATE_PATTERN);
	}

	public static String formatYMDHMS(Date date) {
		return format(date, DATE_TIME_PATTERN);
	}

	public static String formatUTCYMDHMS(Date date) {
		return format(date, DATE_TIME_UTC_PATTERN,TimeZone.getTimeZone("UTC"));
	}

	public static String format(Date date) {
		return format(date, DATE_TIME_PATTERN);
	}

	public static String format(Date date, String pattern) {
		return format(date, pattern, TimeZone.getDefault());
	}

	public static String format(Date date, String pattern, TimeZone timeZone) {
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			df.setTimeZone(timeZone);
			return df.format(date);
		}
		return null;
	}

	public static String formatStringUTCtoGTSYMDHMS(String str)throws Exception {
		Date d =  parse(str, DATE_TIME_PATTERN,TimeZone.getTimeZone("UTC"));
		return formatYMDHMS(d);
	}

	public static String formatStringUTCtoGTSYMDHMS2(String str)throws Exception {
		Date d =  parse(str, DATE_TIME_UTC_PATTERN2,TimeZone.getTimeZone("UTC"));
		return formatYMDHMS(d);
	}

	public static String formatStringUTCtoGTSYMD(String str)throws Exception {
		Date d =  parse(str, DATE_TIME_UTC_PATTERN,TimeZone.getTimeZone("UTC"));
		return formatYMD(d);
	}

	public static Date parseUTCYMDHMS(String str)throws Exception {
		return parse(str, DATE_TIME_UTC_PATTERN,TimeZone.getTimeZone("UTC"));
	}

	public static Date parse(String str)throws Exception {
		return parse(str, DATE_PATTERN, TimeZone.getDefault());
	}
	public static Date parse(String str, String pattern)throws Exception {
		return parse(str, pattern, TimeZone.getDefault());
	}
	public static Date parse(String str, String pattern, TimeZone timeZone)throws Exception {
		if (str != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			df.setTimeZone(timeZone);
			return df.parse(str);
		}
		return null;
	}
}
