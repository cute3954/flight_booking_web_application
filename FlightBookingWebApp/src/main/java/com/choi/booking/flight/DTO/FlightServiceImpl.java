package com.choi.booking.flight.DTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choi.booking.flight.mapper.FlightMapper;

@Service
@Transactional
public class FlightServiceImpl {

	@Autowired
	FlightMapper flightMapper;
	
	public List<FlightVO> getSearchResult(FlightVO fvo) {	
		return flightMapper.getSearchResult(fvo);
	}
}