package com.choi.booking.flight.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.choi.booking.flight.DTO.FlightVO;

@Mapper
public interface FlightMapper {
	public List<FlightVO> getSearchResult(FlightVO fvo);
}
