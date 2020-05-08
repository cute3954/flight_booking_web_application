package com.choi.booking.flight.DTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choi.booking.flight.mapper.FlightMapper;

@Service
public class FlightServiceImpl {

	@Autowired
	FlightMapper flightMapper;
	
	public List<FlightVO> getSearchResult(FlightVO fvo) {	
		return flightMapper.getSearchResult(fvo);
	}
	
	// 予約リストに追加
	public void insertBookingList(MyBookingListVO mvo) {
		flightMapper.insertBookingList(mvo);
	}
	
	// 予約したチケットの情報を取得
	public List<FlightVO> getMyBookingList(MyBookingListVO mvo) {
		return flightMapper.getMyBookingList(mvo);
	}
}
