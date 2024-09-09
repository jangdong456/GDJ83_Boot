package com.jang.app.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jang.app.interceptors.AdminCheckInterceptor;
import com.jang.app.interceptors.LoginInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	
	@Autowired
	private LoginInterceptor loginInterceptor;
	@Autowired
	private AdminCheckInterceptor adminCheckInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 어떤 URL이 왔을때 어떤 Interceptor를 실행 할 것이냐?
		// 어떤 url : /qnq/list -> LoginInterceptor : 로그인 한 사람만 /qna/list 진입 할 수 있게 하기
		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/qna/*")
				.excludePathPatterns("/qna/list");	
		
		// 관리자 
		registry.addInterceptor(adminCheckInterceptor) 
				.addPathPatterns("/admin/*");				
	}
}
