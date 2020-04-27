package com.choi.booking.flight.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.choi.booking.flight.DTO.FlightVO;
import com.choi.booking.flight.DTO.MyBookingListVO;

@Mapper
public interface FlightMapper {
	public List<FlightVO> getSearchResult(FlightVO fvo);
	public void insertBookingList(MyBookingListVO mvo);
	public List<FlightVO> getMyBookingList(MyBookingListVO mvo);
}
