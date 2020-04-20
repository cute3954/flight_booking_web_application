package com.choi.booking.login.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.choi.booking.login.DTO.UserVO;

@Mapper
public interface LoginMapper {
	public UserVO findById(String userid);
}
