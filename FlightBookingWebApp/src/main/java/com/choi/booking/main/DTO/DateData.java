package com.choi.booking.main.DTO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/* 航空券検索用のカレンダー情報 */
public class DateData {
	String year = "";
	String month = "";
	String date = "";
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public Map<String, Integer> today_info(DateData dateData) {
		Map<String, Integer> todayData = new HashMap<String, Integer>();
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(dateData.getYear()), Integer.parseInt(dateData.getMonth()), 1);
		
		int startDay = cal.getMinimum(Calendar.DATE);
		int endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// 今月が何曜日から開始されているか
		int startWeek = cal.get(Calendar.DAY_OF_WEEK);
		
		Calendar todayCal = Calendar.getInstance();
		SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
		SimpleDateFormat msdf = new SimpleDateFormat("M");
		
		int today_year = Integer.parseInt(ysdf.format(todayCal.getTime()));
		int today_month = Integer.parseInt(msdf.format(todayCal.getTime()));

		int search_year = Integer.parseInt(dateData.getYear());
		int search_month = Integer.parseInt(dateData.getMonth()) + 1;
		
		int today = -1;
		if (today_year == search_year && today_month == search_month) {
			SimpleDateFormat dsdf = new SimpleDateFormat("dd");
			today = Integer.parseInt(dsdf.format(todayCal.getTime()));
		}
		
		search_month = search_month - 1;
		Map<String, Integer> before_after_calendar = before_after_calendar(search_year, search_month);
		
		todayData.put("startWeek", startWeek);
		todayData.put("startDay", startDay);
		todayData.put("endDay", endDay);
		todayData.put("today", today);
		todayData.put("search_year", search_year);
		todayData.put("search_month", search_month+1);
		todayData.put("before_year", before_after_calendar.get("before_year"));
		todayData.put("before_month", before_after_calendar.get("before_month"));
		todayData.put("after_year", before_after_calendar.get("after_year"));
		todayData.put("after_month", before_after_calendar.get("after_month"));
		
		return todayData;
	}
	
	public Map<String, Integer> before_after_calendar(int search_year, int search_month) {
		Map<String, Integer> before_after_data = new HashMap<String, Integer>();
		int before_year = search_year;
		int before_month = search_month - 1;
		int after_year = search_year;
		int after_month = search_month + 1;
		
		if (before_month < 0) {
			before_month = 11;
			before_year = search_year - 1;
		} else if (after_month > 11) {
			after_month = 0;
			after_year = search_year + 1;
		}
		
		before_after_data.put("before_year", before_year);
		before_after_data.put("before_month", before_month);
		before_after_data.put("after_year", after_year);
		before_after_data.put("after_month", after_month);
		
		return before_after_data;
	}
	
	public DateData(String year, String month, String date) {
		if ((month != null && month != "") && (date != null && date != "")) {
			this.year = year;
			this.month = month;		
		}
	}
}
