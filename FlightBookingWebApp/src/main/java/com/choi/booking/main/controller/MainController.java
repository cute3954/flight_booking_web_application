package com.choi.booking.main.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping("/")
	private String showMain(Model model) {
		// 現在のリクエストに紐づくAuthenticationを取得する
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userid = auth.getName();
		model.addAttribute("userid", userid);
		return "main";
	}
}
