package com.choi.booking.register.DTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choi.booking.login.DTO.UserVO;
import com.choi.booking.register.mapper.RegisterMapper;

@Service
@Transactional
public class RegisterUserServiceImpl {

	@Autowired
	RegisterMapper registerMapper;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	public void registerUser (UserVO vo) {	
			vo.setFb_userpwd(passwordEncoder.encode(vo.getFb_userpwd()));
			registerMapper.addUser(vo);
	}
}
