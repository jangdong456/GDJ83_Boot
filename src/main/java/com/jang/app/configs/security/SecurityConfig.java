package com.jang.app.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		// Security 에서 무시할 것들
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
							.requestMatchers("/member/add", "/member/login").permitAll()
							.requestMatchers("/member/*").authenticated()
							.anyRequest().permitAll()
					);
		
		//form login 관련 설정
		security.formLogin(
					login -> login.loginPage("/member/login") // loginPage() : 개발자가 만든 login form 등록
								.defaultSuccessUrl("/") //defaultSuccessUrl() : 로그인이 성공했을때 보내지는 url
								.failureUrl("/member/login") // failureUrl() : 로그인이 실패시 보내지는 url
								// .usernameParameter("id") // 파라미터이름이 username이 아니라 id로 사용한 경우
								// .passwordParameter("pw") 파라미터이름이  password아니고 pw로 사용한 경우 알려줘야한다.
								.permitAll()
				);
		
		
		return security.build();
		
	}
}
