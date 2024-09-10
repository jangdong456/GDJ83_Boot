package com.jang.app.members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("add")
	public void add() throws Exception {
		
	}
	
	@PostMapping("add")
	public String add(MemberVO memberVO) throws Exception {
		int result = memberService.add(memberVO);
		
		return "redirect:../";
		
	}
	
	@GetMapping("login")
	public void login() throws Exception {
		
	}
	
	// security 설정으로 인해서 login 성공시 form을 설정을 해놓아서 거기로 url이 타짐
	// filter or interceptor처럼 security도 server로 가기전에 실행이 된다.
//	@PostMapping("login")
//	public String login(MemberVO memberVO, HttpSession session) throws Exception {
//		memberVO = memberService.detail(memberVO);
//		
//		if(memberVO != null) {
//			session.setAttribute("member", memberVO);
//		}
//		
//		return "redirect:../";
//	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) throws Exception {
		session.invalidate();
		return "redirect:../";
	}
}
