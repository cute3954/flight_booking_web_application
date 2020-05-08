package com.choi.booking.mypage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.choi.booking.flight.DTO.FlightVO;
import com.choi.booking.flight.DTO.MyBookingListVO;
import com.choi.booking.login.DTO.DbUserDetails;
import com.choi.booking.login.DTO.UserVO;
import com.choi.booking.mypage.DTO.MypageServiceImpl;

@Controller
public class MyPageController {
	
	@Autowired
	private MypageServiceImpl mypageService;
	
	@RequestMapping("/mypage")
	public String myPage() {
		// 現在のリクエストに紐づく認証情報を取得
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userid = auth.getName();
		// ログインしてない場合、ログイン画面にリダイレクトさせる
		if (userid == "anonymousUser") {
			return "redirect:/login";
		}
		return "mypage/mypage";
	}
	
	/* 予約の確認画面 */
	@RequestMapping("/mypage/checkBooking")
	public ModelAndView checkBooking(@AuthenticationPrincipal DbUserDetails user,
														ModelAndView mav) {
		
		MyBookingListVO mvo = new MyBookingListVO();
		UserVO vo = user.getUser();
		mvo.setFb_userno(vo.getFb_userno());	
		
		int count = mypageService.getBookingCount(mvo);
		List<FlightVO> result = mypageService.checkMyBookingList(mvo);
		
		mav.addObject("bookingCount", count);
		mav.addObject("bookingList", result);
		mav.setViewName("/mypage/check_booking");
		
		return mav;
	}
	
	/* 予約変更画面 */
	// 上と全く同じ、、後程片付ける
	@RequestMapping("/mypage/changeBooking")
	public ModelAndView changeBooking(@AuthenticationPrincipal DbUserDetails user,
															ModelAndView mav) {
		
		MyBookingListVO mvo = new MyBookingListVO();
		UserVO vo = user.getUser();
		mvo.setFb_userno(vo.getFb_userno());	
		
		int count = mypageService.getBookingCount(mvo);
		List<FlightVO> result = mypageService.checkMyBookingList(mvo);
		
		mav.addObject("bookingCount", count);
		mav.addObject("bookingList", result);
		mav.setViewName("/mypage/change_booking");
		return mav;
	}
	
	/* 予約の取り消し */
	@RequestMapping("/mypage/cancelBooking")
	public String cancelBooking(@RequestParam("fb_flightno") int fb_flightno, 
												@AuthenticationPrincipal DbUserDetails user) {
		MyBookingListVO mvo = new MyBookingListVO();
		mvo.setFb_flightno(fb_flightno);
		
		UserVO vo = user.getUser();
		mvo.setFb_userno(vo.getFb_userno());	

		mypageService.cancelBooking(mvo);
		
		return "redirect:/mypage/changeBooking?fb_userno=" + vo.getFb_userno();
	}
}
