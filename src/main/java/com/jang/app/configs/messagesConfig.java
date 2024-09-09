package com.jang.app.configs;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class messagesConfig implements WebMvcConfigurer {
	
	@Bean
	LocaleResolver localeResolver() {
		//1. Session을 이용한 객체
		// resolver : message를 변환 시켜주는 얘이다.
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.KOREAN);
		
		//2. Cookie를 이용한 방법
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setDefaultLocale(Locale.KOREAN);
		//둘의 차이점 
		// session: 연결할 동안을 계속 쓸수있다. 로그아웃 할 때까지 사용가능
		// cookie : 시간을 설정 할 수 있어서 설정한 만큼 사용 할 수 있다.
		
		return cookieLocaleResolver;
	}
	
	// 인터셉터 만들기
	// 이미 있는 클래스를 사용할거라 객체를 만들어야함 -> @Bean 어노테이션 사용
	// 1. 인터셉터 객체만들기
	@Bean
	LocaleChangeInterceptor changeInterceptor() {
		LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
		changeInterceptor.setParamName("lang");  
		
		//parameter를 받아서 언어를 구분 할 것이다.
		// 어떤 url이 올때 -> url?lang=ko , url?lang=en, url?lang=jp 등등..
		return changeInterceptor;
	}

}
