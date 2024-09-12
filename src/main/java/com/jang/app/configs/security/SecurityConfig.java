package com.jang.app.configs.security;

import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.jang.app.members.MemberUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private SecurityLoginSuccessHandler successHandler;
	@Autowired
	private SecurityLoginFailHandler loginFailHandler;
	@Autowired
	private MemberUserService memberUserService;
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		// Security 에서 무시할 것들을 설정하기
		return web -> web
						.ignoring()
						//requestMatchers : fornt에서 사용되는 것들을 무시 , 저장되거나 사용되는 경로를적는다
						.requestMatchers("/images/**")
						.requestMatchers("/css/**")
						.requestMatchers("/js/**")
						.requestMatchers("/vendor/**") // 부트스트랩 테마 사용시 가져오면 vendor라는 폴더가 있을수 있다.
						.requestMatchers("/favicon/**");
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception{
		String message = URLEncoder.encode("로그인 실패", "UTF-8");
		
		security
				.cors()
				.and()
				.csrf()
				.disable();
		
		// 권한에 관련된 설정
		security.authorizeHttpRequests(
					(authorizeRequest) ->
						authorizeRequest
							.requestMatchers("/").permitAll() // permitAll() : 모든 허용 이라는 뜻
							.requestMatchers("/qna/list").permitAll()
							.requestMatchers("/qna/*").authenticated() // authenticated() : 인증된 사람만 url허용가능 -> 로그인된사람만
							.requestMatchers("/notice/list", "/notice/detail").permitAll()
							.requestMatchers("/notice/*").hasRole("ADMIN") //hasRole() : 설정했던 값이 있는 사람만 허용 
							.requestMatchers("/manager/*").hasAnyRole("manager", "admin") // hasAnyRole() : 둘중에 하나만 있으면 허용
							.requestMatchers("/member/add", "/member/login", "/member/check").permitAll()
							.requestMatchers("/member/*").authenticated()
							
							.anyRequest().permitAll()
					);
		
		//form login 관련 설정
		security.formLogin(
				//개발자가 만든 로그인 페이지 사용
					login -> login.loginPage("/member/login") // loginPage() : 개발자가 만든 login form 등록
//								.defaultSuccessUrl("/") //defaultSuccessUrl() : 로그인이 성공했을때 보내지는 url
								.successHandler(successHandler)// 추가적인 작업을 하고싶을 때 -> handler 만들고 여기로 불러온다.
								// 한글 안나올시 encode 해주어야한다 위에 message 변수에 작업해놈
//								.failureUrl("/member/login?message="+message) // failureUrl() : 로그인이 실패시 보내지는 url 및 message 전달하기
								// 핸들러를 이용한 로그인 실패 메세지 띄우기
								.failureHandler(loginFailHandler)
								// .usernameParameter("id") // 파라미터이름이 username이 아니라 id로 사용한 경우
								// .passwordParameter("pw") 파라미터이름이  password아니고 pw로 사용한 경우 알려줘야한다.
								.permitAll()
				);
		// logout 설정 
		security.logout(
						logout ->
							logout
								// 로그아웃 url지정 2가지 방법
								// 1. logoutUrl() : 로그아웃 URL 지정
								.logoutUrl("/member/logout") 
								.logoutSuccessHandler(null)
								// 2. logoutRequestMatcher() : 로그아웃 url을 할 거냐, 객체타입이라 객체를 만들어야함
//								.logoutRequestMatcher(new AntPathRequestMatcher("member/logout"))
								.logoutSuccessUrl("/") // logoutSuccessUrl() : 로그아웃 하고 어느 url로 갈지 정해주는 함수
								.invalidateHttpSession(true)// invalidateHttpSession():세션만료 밎 유지하는 함수 -> true: 세션만료, flase: 세션만료 시키지 않겠다
//								.deleteCookies("쿠키명") // deleteCookies("쿠키명"): cookies 삭제 메서드 
								
				)
		// rememberMe : 자동 로그인?
		.rememberMe(
					remember ->
						remember
							//Parameter 이용
							.rememberMeParameter("rememberMe")
							//토큰의 유효시간
							.tokenValiditySeconds(60)
							// token에 생성시 값, 필수값, 개발자가 값 설정한다,
							.key("rememberme")
							// 인증절차(로그인) 진행할 UserDetailService
							.userDetailsService(memberUserService)
							//로그인이 성공했을 경우 진행할 Handler
							.authenticationSuccessHandler(successHandler)
							.useSecureCookie(false)		
				)
		//동시세션
		.sessionManagement(
				sessionManger ->
					sessionManger
						// maximumSessions(1) : sessions이 1개 
						// // maximumSessions(-1) : 무한개
						.maximumSessions(1)
						// false : 기존사용자를 만료 시킴 , true : 새로운 사용자를 만료 시킴 -> 인증 실패 유도하는 코드
						.maxSessionsPreventsLogin(false)
						// session이 만료 되었을 경우 redirect할 url 
						.expiredUrl("/member/check")
				
				)
		
		// Social Login
		.oauth2Login(
					oauth2 ->
						oauth2.userInfoEndpoint(
									user -> user.userService(memberUserService)
								)
				);
		
		return security.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();	
	}
}
