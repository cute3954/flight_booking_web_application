package com.choi.booking.flight.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.choi.booking.flight.DTO.FlightServiceImpl;
import com.choi.booking.flight.DTO.FlightVO;
//import com.mashape.unirest.http.HttpResponse;
//import com.mashape.unirest.http.JsonNode;
//import com.mashape.unirest.http.Unirest;
//import com.mashape.unirest.http.exceptions.UnirestException;

@Controller
public class FlightController {
	
	@Autowired
	private FlightServiceImpl flightService;
	
	@RequestMapping("/flight_searchlist")
	public ModelAndView searchFlightTickets(@ModelAttribute("flightTicketList") FlightVO fvo, ModelAndView mav) {
		
		/* Skyscanner Flight Search api */
//		try {
//			HttpResponse<JsonNode> response = Unirest.get("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browseroutes/v1.0/US/USD/en-US/SFO-sky/ORD-sky/2020-05-03?inboundpartialdate=2020-06-01")
//					.header("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
//					.header("x-rapidapi-key", "d0b0eee8d6msh68f7dc52b6e8a8ap12ebffjsn6728882448f2")
//					.asJson();
//			System.out.println(response.getStatus());
//			System.out.println(response.getBody());
//			
//		} catch (UnirestException e) {
//			e.printStackTrace();
//		}
		System.out.println(fvo.getIsroundtrip());
		List<FlightVO> result = flightService.getSearchResult(fvo);
		mav.addObject("flightList", result);
		mav.addObject("isRoundTrip", fvo.getIsroundtrip());
		mav.setViewName("/searchList/flight_searchList");
		return mav;
	}
}
