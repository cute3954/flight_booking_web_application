package com.choi.booking.register.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.choi.booking.login.DTO.UserVO;

@Mapper
public interface RegisterMapper {
	public void addUser(UserVO vo);
}
