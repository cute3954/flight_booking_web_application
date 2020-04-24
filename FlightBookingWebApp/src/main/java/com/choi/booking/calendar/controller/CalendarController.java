package com.choi.booking.calendar.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.choi.booking.calendar.DTO.CalendarService;

@Controller
public class CalendarController {
	
	@Autowired
	private CalendarService calendarService;
	
	@RequestMapping("/calendar")
	@ResponseBody
	public ModelAndView showCalendar(@RequestParam(value="ddayYear", defaultValue = "0") int ddayYear,
														@RequestParam(value="ddayMonth", defaultValue = "0") int ddayMonth,
														@RequestParam(value="ddayOption", defaultValue = "default") String ddayOption) {
			
		ModelAndView mav = new ModelAndView("jsonView");
		Map<String, Object> map = calendarService.getOneDayList(ddayYear, ddayMonth, ddayOption);
		
		mav.addObject("calendarList", map.get("calendarList"));
		mav.addObject("ddayYear", map.get("ddayYear"));
		mav.addObject("ddayMonth", map.get("ddayMonth"));
	
		return mav;
	}
}
