package com.choi.booking.mypage.DTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choi.booking.flight.DTO.FlightVO;
import com.choi.booking.flight.DTO.MyBookingListVO;
import com.choi.booking.mypage.mapper.MypageMapper;

@Service
public class MypageServiceImpl {
	
	@Autowired
	MypageMapper mypageMapper;
	
	public int getBookingCount(MyBookingListVO mvo) {
		return mypageMapper.getBookingCount(mvo);
	}
	// 予約したチケットの情報を全部取得
	public List<FlightVO> checkMyBookingList(MyBookingListVO mvo) {
		return mypageMapper.checkMyBookingList(mvo);
	}
	public void cancelBooking(MyBookingListVO mvo) {
		mypageMapper.cancelBooking(mvo);
	}
}
