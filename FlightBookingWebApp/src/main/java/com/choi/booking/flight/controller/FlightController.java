package com.choi.booking.flight.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.choi.booking.flight.DTO.FlightServiceImpl;
import com.choi.booking.flight.DTO.FlightVO;

@Controller
public class FlightController {
	
	@Autowired
	private FlightServiceImpl flightService;
	
	@RequestMapping("/flight/searchlist")
	public ModelAndView searchFlightTickets(@ModelAttribute("flightTicketList") FlightVO fvo, ModelAndView mav) {
		List<FlightVO> result = flightService.getSearchResult(fvo);
		mav.addObject("flightList", result);
		// 往復の場合
		if (fvo.getIsroundtrip()) {	
			String flightFromEng = fvo.getFb_flightfrom_eng();
			String flightToEng = fvo.getFb_flightto_eng();
			Timestamp flightDateReturn = fvo.getFb_flightdate_return();
			fvo.setFb_flightfrom_eng(flightToEng);
			fvo.setFb_flightto_eng(flightFromEng);
			fvo.setFb_flightdate(flightDateReturn);
			
			List<FlightVO> result_return = flightService.getSearchResult(fvo);
			mav.addObject("flightList_return", result_return);
		}
		mav.addObject("isRoundTrip", fvo.getIsroundtrip());
		mav.setViewName("/searchList/flight_searchList");
		return mav;
	}
}
