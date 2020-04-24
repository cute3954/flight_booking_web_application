package com.choi.booking.calendar.DTO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class CalendarService {
	
	public Map<String, Object> getOneDayList(int ddayYear, int ddayMonth, String ddayOption) {
		Map<String, Object> calendarMap = new HashMap<String, Object>();
		Calendar dday = Calendar.getInstance();
		dday.set(Calendar.DATE, 1);
		
		if (ddayOption.equals("prev")) {
			dday.set(ddayYear, ddayMonth, 1);
			dday.add(Calendar.MONTH, -1);
		} else if (ddayOption.equals("next")) {
			dday.set(ddayYear, ddayMonth, 1);
			dday.add(Calendar.MONTH, 1);
		}
		
		int firstWeek = dday.get(Calendar.DAY_OF_WEEK);
		int endDay = dday.getActualMaximum(Calendar.DATE);
		
		List<CalendarVO> calendarList = new ArrayList<CalendarVO>();
		
		int listSize = (firstWeek - 1) + endDay;
		if (listSize / 7 != 0) {
			listSize = listSize + (7 - (listSize % 7));
		}
		
		Calendar preMonth = Calendar.getInstance();
		preMonth.set(Calendar.MONTH, dday.get(Calendar.MONTH) - 1);
		int preLastDay = preMonth.getActualMaximum(Calendar.DATE);
		int beginSpace = preLastDay - (firstWeek - 2);
		int endSpace = 1;
		
		for (int i = 0; i < listSize; i++) {
			CalendarVO calendar = new CalendarVO();
			if (i < (firstWeek - 1)) {
				calendar.setDay(beginSpace);
				beginSpace++;
			} else if (i < ((firstWeek - 1) + endDay)) {
				calendar.setYear(dday.get(Calendar.YEAR));
				calendar.setMonth(dday.get(Calendar.MONTH) + 1);
				calendar.setDay((i + 1) - (firstWeek - 1));
				
				String date = calendar.getYear() + "-";
				if (calendar.getMonth() < 10) {
					date += "0";
				}
				date += calendar.getMonth() + "-";
				if (calendar.getDay() < 10) {
					date += "0";
				}
				date += calendar.getDay();
				
				calendar.setDate(date);

			} else {
				calendar.setDay(endSpace);
				endSpace++;
			}
			calendarList.add(calendar);
		}
		calendarMap.put("calendarList", calendarList);
		calendarMap.put("ddayYear", dday.get(Calendar.YEAR));
		calendarMap.put("ddayMonth", dday.get(Calendar.MONTH));
		
		return calendarMap;
	}
}
