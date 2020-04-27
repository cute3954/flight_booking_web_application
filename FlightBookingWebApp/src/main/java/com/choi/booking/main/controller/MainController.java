package com.choi.booking.main.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping("/main")
	private String showMain(Model model) {
		// 現在のリクエストに紐づく認証情報を取得
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userid = auth.getName();
		
		// ログインしてない場合、ログイン画面にリダイレクトさせる
		if (userid == "anonymousUser") {
			return "redirect:/login";
		}
		model.addAttribute("message", "Hello," + userid);
		return "main";
	}
}
