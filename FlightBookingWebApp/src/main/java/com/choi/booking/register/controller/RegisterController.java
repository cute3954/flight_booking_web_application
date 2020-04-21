package com.choi.booking.register.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.choi.booking.login.DTO.UserVO;
import com.choi.booking.register.DTO.RegisterUserServiceImpl;

@Controller
public class RegisterController{
	
	@Autowired
	private RegisterUserServiceImpl registerUserService;
	
	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("registerForm", new UserVO());
		return "register/register";
	}
	
	// @Transactional: クラスもしくはメソッド単位で付与。
	//							DBを更新する際のトランザクションを管理するアノテーション。
	@Transactional
	@PostMapping("/register")
	public String registerUser(@Validated @ModelAttribute("registerForm") UserVO vo
											, BindingResult result) {
		if (result.hasErrors()) {
			return "register/register";
		}
		registerUserService.registerUser(vo);
		return "redirect:/";
	}
}
