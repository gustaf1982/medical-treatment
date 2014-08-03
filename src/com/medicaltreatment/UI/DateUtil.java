package com.medicaltreatment.UI;

import java.util.Date;

public class DateUtil {
	public static String getNormalTime(int id) {
		
		String time = null;
		int hour = 7 + (int)(id / 2);
		if (hour >= 24) {
			hour -= 24;
		}
		String minute = "00";
		if ((id % 2) == 1) 
			minute = "30";
		time = "- " + String.valueOf(hour) + ":" + minute;
		
		return time;		
	}
	public static String getBloodUpType(String bloodup) {
		String temp = null;
		if (bloodup.length() == 5 || bloodup.length() == 6) {
			temp = bloodup.substring(0, 3) + "/" + bloodup.substring(3);
		} else if (bloodup.length() == 4) {
			temp = bloodup.substring(0, 2) + "/" + bloodup.substring(2);
		} else {
			temp = bloodup;
		}
		return temp;
	}
	public static String getCurrentDate() {
		
		String currentdate = null;
		String[] weekday = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Date date = new Date();
		int month = date.getMonth() + 1;
		int dt = date.getDate();
		currentdate = month + "月" + dt + "日";
		return currentdate;
		
	}
	public static String getCurrentDay() {
		String day = null;
		String[] weekday = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Date date = new Date();
		int dy = date.getDay();
		day = weekday[dy];
		return day;		
	}
}
