package com.choi.booking.register.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.choi.booking.login.DTO.UserVO;
import com.choi.booking.register.DTO.RegisterUserServiceImpl;

@Controller
public class RegisterController{
	
	@Autowired
	private RegisterUserServiceImpl registerUserService;
	
	@GetMapping("/register")
	public String showRegisterForm() {
		return "register";
	}
	
	// @Transactional: クラスもしくはメソッド単位で付与。
	//							DBを更新する際のトランザクションを管理するアノテーション。
	@Transactional
	@PostMapping("/register")
	public String registerUser(String userid, String userpwd) {
		UserVO vo = new UserVO();
		vo.setFb_userid(userid);
		vo.setFb_userpwd(userpwd);
		registerUserService.registerUser(vo);
		return "redirect:/";
	}
}
