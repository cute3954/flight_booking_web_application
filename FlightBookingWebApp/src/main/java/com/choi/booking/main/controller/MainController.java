package com.choi.booking.main.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.choi.booking.main.DTO.DateData;

@Controller
public class MainController {
	@RequestMapping("/main")
	private String showMain(Model model, DateData dateData) {
		// 現在のリクエストに紐づく認証情報を取得
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userid = auth.getName();
		
		// ログインしてない場合、ログイン画面にリダイレクトさせる
		if (userid == "anonymousUser") {
			return "redirect:/login";
		}
		
		Calendar cal = Calendar.getInstance();
		DateData calendarData = null;
		
		if (dateData.getDate().equals("") && dateData.getMonth().equals("")) {
			dateData = new DateData(String.valueOf(cal.get(Calendar.YEAR)), String.valueOf(cal.get(Calendar.MONTH)), String.valueOf(cal.get(Calendar.DATE)));
		}

		Map<String, Integer> todayInfo = dateData.today_info(dateData);
		List<DateData> dateList = new ArrayList<DateData>();
		
		for (int i = 1; i < todayInfo.get("startWeek"); i++) {
			calendarData = new DateData(null, null, null);
			dateList.add(calendarData);
		}
		
		for (int i = todayInfo.get("startDay"); i <= todayInfo.get("endDay"); i++) {
			calendarData = new DateData(String.valueOf(dateData.getYear()), String.valueOf(dateData.getMonth()), String.valueOf(i));
		}
		dateList.add(calendarData);
		
		int index = 7-dateList.size()%7;
		if (dateList.size()%7 != 0) {
			for (int i = 0; i < index; i++) {
				calendarData = new DateData(null, null, null);
				dateList.add(calendarData);
			}
		}

		model.addAttribute("message", "Hello," + userid);
		model.addAttribute("dateList", dateList);
		model.addAttribute("todayInfo", todayInfo);
		return "main";
	}
}
