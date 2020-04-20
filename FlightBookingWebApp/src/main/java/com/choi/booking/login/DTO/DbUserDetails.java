package com.choi.booking.login.DTO;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class DbUserDetails extends User{
	
	private final UserVO vo;
	// Collection<GrantedAuthority> authorities：　権限情報を保存する
	public DbUserDetails(UserVO vo, Collection<GrantedAuthority> authorities) {
		super(vo.getFb_userid(), vo.getFb_userpwd(), true, true, true, true, authorities);
		this.vo = vo;
	}
	public UserVO getUser() {
		return vo;
	}
}
