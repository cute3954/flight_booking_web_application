package com.choi.booking.mypage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.choi.booking.flight.DTO.FlightVO;
import com.choi.booking.flight.DTO.MyBookingListVO;

@Mapper
public interface MypageMapper {
	public int getBookingCount(MyBookingListVO mvo);
	public List<FlightVO> checkMyBookingList(MyBookingListVO mvo);
	public void cancelBooking(MyBookingListVO mvo);
}
