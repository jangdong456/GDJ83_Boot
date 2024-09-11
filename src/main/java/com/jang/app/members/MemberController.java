package com.jang.app.members;

import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jang.app.vaildate.MemberAddGroup;
import com.jang.app.vaildate.MemberUpdateGroup;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("check")
	public void check() throws Exception {
		
	}
	
	@GetMapping("update")
	public String update(HttpSession session, Model model) throws Exception {
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		model.addAttribute("memberVO", memberVO);
		
		return "member/update";
	}
	
	@PostMapping("update")
	public String update(@Validated(MemberUpdateGroup.class) MemberVO memberVO, BindingResult bindingResult) throws Exception {
		
		if(bindingResult.hasErrors()) {
			return "member/add";
			
		}
		
		return "redirect:./mypage";
	}
	
	@GetMapping("mypage")
	public void mypage(HttpSession session) throws Exception {
		Enumeration<String> en = session.getAttributeNames();
		while(en.hasMoreElements()) {
			String name = en.nextElement();
			log.info("Name : {} " , name); // SPRING_SECURITY_CONTEXT
		}
		SecurityContextImpl sc = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
//		log.info("SecurityContextImpl : {}", sc);
		
		SecurityContext context = SecurityContextHolder.getContext();
//		log.info("context : {}", context);
		
		Authentication authentication = context.getAuthentication();
		log.info("authentication: {}", authentication);
		
		MemberVO memberVO = (MemberVO)authentication.getPrincipal();
		log.info("MemberVO : {} ", memberVO);
		log.info("Name : {} ", authentication.getName());
	}
	
	@GetMapping("add")
	public void add(MemberVO memberVO) throws Exception {
//		model.addAttribute("memberVO", new MemberVO());
	}
	
	@PostMapping("add")
	public String add(@Validated(MemberAddGroup.class) MemberVO memberVO, BindingResult bindingResult) throws Exception {

		if(bindingResult.hasErrors()) {
			boolean check = memberService.memberValidate(memberVO, bindingResult);
			if(check) {
				return "member/add";
			}
//			return "member/add";
		}
			
		int result = memberService.add(memberVO);
		
		return "redirect:../";
		
	}
	
	@GetMapping("login")
	public String login(String message, Model model) throws Exception {
		model.addAttribute("message", message);
		
		SecurityContext context = SecurityContextHolder.getContext();
		log.info("context : {}", context);
		if(context == null) {
			return "/member/login";
		}
		
		String user = context.getAuthentication().getPrincipal().toString();
		
		if(user.equals("anonymousUser")) {
			return "member/login";
		}
		return "redirect:/";
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
