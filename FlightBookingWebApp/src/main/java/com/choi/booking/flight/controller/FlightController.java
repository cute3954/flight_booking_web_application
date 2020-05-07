package com.choi.booking.flight.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.choi.booking.flight.DTO.FlightServiceImpl;
import com.choi.booking.flight.DTO.FlightVO;
import com.choi.booking.flight.DTO.MyBookingListVO;
import com.choi.booking.login.DTO.DbUserDetails;
import com.choi.booking.login.DTO.UserVO;

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
			Date flightDateReturn = fvo.getFb_flightdate_return();
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
	
	/* 決済後の航空券予約画面 */
	@RequestMapping("/flight/booking-success")
	public ModelAndView bookingFlightTickets(@RequestParam("fb_flightno") int fb_flightno, 
																	@RequestParam("isroundtrip") boolean isroundtrip, 
																	@AuthenticationPrincipal DbUserDetails user,
																	ModelAndView mav) {
		FlightVO fvo = new FlightVO();
		MyBookingListVO mvo = new MyBookingListVO();
		mvo.setFb_flightno(fb_flightno);
		fvo.setIsroundtrip(isroundtrip);
		
		UserVO vo = user.getUser();
		mvo.setFb_userno(vo.getFb_userno());	
		
		// 往復を選んだ場合は帰り選択画面へ遷移
		if (fvo.getIsroundtrip()) {
			mav.setViewName("/searchList/flight_searchList_return");
		// 片道を選んだ場合は予約確認画面へ遷移
		} else {
			flightService.insertBookingList(mvo);
			List<FlightVO> result = flightService.getMyBookingList(mvo);
			mav.addObject("bookingList", result);
			mav.setViewName("/booking/bookingSuccess");
		}
		return mav;
	}
}
