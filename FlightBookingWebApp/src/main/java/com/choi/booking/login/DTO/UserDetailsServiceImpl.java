package com.choi.booking.login.DTO;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.choi.booking.login.mapper.LoginMapper;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	LoginMapper loginMapper;
	
	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
		// 自動生成されたメソッド・スタブ
		// 結果が買ってこない時があるので、Optionalを受ける。
		// mapperでuseridを検索
		// ofNullable: nullかもしれない値(nullable)を持つOptionalオブジェクトを返す。
		UserVO vo = Optional.ofNullable(loginMapper.findById(userid))
				// orElseThrow: Optionalオブジェクトが保持する値を返す。
				//						保持する値がnullの場合は指定した例外はスローされる。
				.orElseThrow(()-> new UsernameNotFoundException("user not found"));
		// 見つかったユーザーを返す。
		return new DbUserDetails(vo, getAuthorities(vo));
	}	
	
	private Collection<GrantedAuthority> getAuthorities (UserVO vo) {
		return AuthorityUtils.createAuthorityList("ROLE_USER");
	}
}
