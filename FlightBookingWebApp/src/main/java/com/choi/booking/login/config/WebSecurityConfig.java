package com.choi.booking.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/* Spring Security、認証・認可についての設定を記述したクラス */

//@Configuration: 設定を記述するクラスに付与。
//							このクラスは@Beanを付与したメソッドで構成される事になる。
@Configuration
@EnableWebSecurity // Spring Securityの基本設定
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	// @Autowired: フィールド単位で付与する
	@Autowired
	UserDetailsService userDetailsService;
	
	// @Bean: メソッド単位で付。　@Confiqurationクラスに使用。
	// フォームの値を比較するDBから取得したいパスワードは暗号化されているので
	// フォームの値も暗号化するために利用
	@Bean
	public PasswordEncoder passwordEncoder() {
		// bcrypt: 認証で使用するパスワードを安全にハッシュ化するアルゴリズム。
		//				60文字のハッシュ値を生成する。
		// 参照：　https://tomokazu-kozuma.com/how-bcryptpasswordencoder-is-used-in-spring-security/
		return new BCryptPasswordEncoder();
	}
	
	// configureメソッド(WebSecurity)
	// 静的ファイル（image, css, javascript）を利用する際のリクエストまで弾いてしまわないための設定を行う。
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(
											"/images/**",
											"/css/**",
											"/javascript/**"
											);
	}
	
	// configureメソッド(HttpSecurity)
	// 認証・認可の処理の中でhttpリクエスト関連の部分についての設定を記述するためのメソッド
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// ログイン設定
		http
			.formLogin()
				.loginPage("/login") // ログインページはコントローラを経由しないのでViewNameとの紐付けが必要
				.loginProcessingUrl("/sign_in") // フォームのSubmitURL、このURLへリクエストが送られると認証処理が実行される
				.usernameParameter("userid") // リクエストパラメータのname属性を明示
				.passwordParameter("userpwd")
				.defaultSuccessUrl("/") // ログインに成功した場合に移動するページのURLを指定
				.permitAll();
		// 会員登録機能実装時に追加
		http
			.authorizeRequests()
				.antMatchers("/register").permitAll() // 会員登録画面ですべてのユーザーがアクセス可
				.anyRequest().authenticated(); // 全てのURLリクエストは認証されているユーザーしかアクセスできないという記述
	}
	
	// AuthenticationProviderとかの設定。
	// UserDetailsService、passwordEncoderをセットする
	@Autowired
    void configureAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {
        // 認証するユーザー
		auth.userDetailsService(userDetailsService)
				// 入力値をbcryptでハッシュ化した値でパスワード認証を行う
            	.passwordEncoder(passwordEncoder());
    }
}
