package com.jang.app.configs.security;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.jang.app.members.MemberMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecurityLoginFailHandler implements AuthenticationFailureHandler{
	
	// 여기서 Mapper의 를 사용하고 싶을때
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		// 로그인 비밀번호가 5번 틀렸을때 핸들러 사용해서 구현 할 수 있다.
		// db에 틀린 횟수를 업데이트 시키고 5번 틀리면 그 때 비번찾기 밎 등등 기능 구현 가능
	
		// 또는 세부정보 주려고, 아이디가 틀렸는지 pw가 틀렸는지 메세지를 다르게 주고 싶을때 사용
		String message = "로그인 실패";
		
		log.error("Exception : {}", exception);
		// id 틀렸을때 발생하는 exception : InternalAuthenticationServiceException 
		// pw 틀렸을때 발생하는 exception : BadCredentialsException : 자격 증명이 실패 했습니다.
		
		// 둘다 다른 exception이 발생된다.
		
		// MemberVO에서 isEnable()  false 일시 exception 
		     // id 틀렸을 때 : InternalAuthenticationServiceException: UserDetailsService returned null, which is an interface contract violation
			 // pw 틀렸을 때 : DisabledException: 유효하지 않은 사용자입니다.
				
		// MemberVo에서 isAccountNonExpired메서드를 false 일시 exception 
			// id 틀렸을 때 : AccountExpiredException : 사용자 계정의 유효 기간이 만료 되었습니다.
			// pw 틀렸을 때 : AccountExpiredException: 사용자 계정의 유효 기간이 만료 되었습니다.
		
		// MemberVo에서 isAccountNonLocked() false 일시 exception 
			// id 틀렸을 때 : InternalAuthenticationServiceException
			// pw 틀렸을 때 : LockedException: 사용자 계정이 잠겨 있습니다.
		
		// isCredentialsNonExpired() false 일시 exception 
			// id 틀렸을 때 : InternalAuthenticationServiceException
			// pw 틀렸을 때 : CredentialsExpiredException
		
		//instanceof : 데이터 타입 비교
		if(exception instanceof BadCredentialsException) {
			//비번 틀림
			message = "비밀번호를 확인";
		}
		
		if(exception instanceof InternalAuthenticationServiceException) {
			// id가 틀림
			message = "없는 ID 입니다";
		}
		
		if(exception instanceof AuthenticationException) {
			// isAccountNonExpired false 시
			// 계정 유효기간이 만료
			message = "만료된 계정입니다 관리자에게 문의하세요";
		}
		
		if(exception instanceof LockedException) {
			// isAccountNonLocked false 시
			// 계정 잠김
			message = "계정이 잠겼습니다 관리자에게 문의하세요";
		}
		
		if(exception instanceof CredentialsExpiredException) {
			// isCredentialsNonExpired false 시
			// 비번 유효기간 만료
			message = "비밀번호의 유효기간이 끝났습니다 관리자에게 문의하세요";
		}
		
		if(exception instanceof DisabledException) {
			// isEnabled false 시
			message = "휴면계정입니다 관리자에게 문의하세요";
		}
		message = URLEncoder.encode(message, "UTF-8");
		response.sendRedirect("/member/login?message=" + message);
	}
	
}
