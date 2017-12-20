package com.dbquery.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatetimeUtil {
	public static final String DEFAULT_FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";

	public static String toDefaultDateString(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(DEFAULT_FORMAT_STRING);
		format.format(date);
		return format.format(date);
	}

	public static String getCurDateString() {
		return toDefaultDateString(new Date());
	}

	public static String dateFormate(String dateTime) {
		String regEx = "^\\d{2}/\\d{2}/\\d{4}  \\d:\\d{2}$";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(dateTime);
		if (m.find()) {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
			try {
				Date date = format.parse(dateTime);
				format = new SimpleDateFormat(DEFAULT_FORMAT_STRING);
				return format.format(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				return dateTime;
			}

		}
		return dateTime;
	}

	public static Date string2Date(String datetime) {
		if (datetime != null && datetime.length() > 0) {
			SimpleDateFormat format = new SimpleDateFormat(DEFAULT_FORMAT_STRING);
			try {
				return format.parse(datetime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public long fromDateStringToLong(String inVal) { // 此方法计算时间毫秒
		Date date = null; // 定义时间类型
		SimpleDateFormat inputFormat = new SimpleDateFormat(DEFAULT_FORMAT_STRING);
		try {
			date = inputFormat.parse(inVal); // 将字符型转换成日期型
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date.getTime(); // 返回毫秒数
	}

	public static String formatDate(Date date, String regEx) {
		DateFormat format = new SimpleDateFormat(regEx);
		return format.format(date);
	}

	public static Date parseDate(String date, String regEx) {
		DateFormat format = new SimpleDateFormat(regEx);
		try {
			return format.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date getRealDate(Date date, String regEx) {
		DateFormat format = new SimpleDateFormat(regEx);
		String dateStr = format.format(date);
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

}
