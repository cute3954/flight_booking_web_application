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
	
	// @Bean: メソッド単位で付。　@Configurationクラスに使用。
	// フォームの値を比較するDBから取得したいパスワードは暗号化されているので
	// フォームの値も暗号化するために利用
	@Bean
	public PasswordEncoder passwordEncoder() {
		// bcrypt: 認証で使用するパスワードを安全にハッシュ化するアルゴリズム。
		//				60文字のハッシュ値を生成する。
		// 参照：　https://tomokazu-kozuma.com/how-bcryptpasswordencoder-is-used-in-spring-security/
		return new BCryptPasswordEncoder();
	}
	
	/* 全体に対するセキュリティ設定 */
	// 特定パス（image, css, javascriptなど静的リソース）のみセキュリティ設定を無効にする
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(
											"/images/**",
											"/css/**",
											"/javascript/**"
											);
	}
	
	/* httpリクエスト関連のセキュリティ設定 */
	// URLごとに異なるセキュリティ設定を行う
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// ログイン設定
		http
			.authorizeRequests()
				.antMatchers("/**").permitAll() // 全ての画面ですべてのユーザーがアクセス可（認証不要）
				.anyRequest().authenticated() // 認証済みユーザーのみがリクエスト可
				.and()
			// ログイン
			.formLogin() // form認証が必要なことを定義
				.loginPage("/login") 
				.loginProcessingUrl("/sign_in") // フォームのSubmitURL、このURLへリクエストが送られると認証処理が実行される
				.usernameParameter("userid") // リクエストパラメータのname属性を明示
				.passwordParameter("userpwd")
				.defaultSuccessUrl("/main") // ログインに成功した場合に移動するページのURLを指定
				.permitAll() // ログイン画面はすべてのユーザーがアクセス可（認証不要）
				.and()
			// ログアウト
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login");
	}
	
	/* 認証方法の実装の設定 */
	// UserDetailsService、passwordEncoderをセットする
	@Autowired
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 認証するユーザー
		auth
				.userDetailsService(userDetailsService)
				// 入力値をbcryptでハッシュ化した値でパスワード認証を行う
            	.passwordEncoder(passwordEncoder());
    }
}
