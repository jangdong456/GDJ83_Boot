package com.jang.app.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jang.app.util.Pager;

import lombok.extern.slf4j.Slf4j;



@Controller
@Slf4j
@RequestMapping("/qna/*")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@GetMapping("list")
	public String getList(Pager pager, Model model) throws Exception {
		List<QnaVO> ar = qnaService.getList(pager);
		
		model.addAttribute("list",ar);
		model.addAttribute("pager",pager);
		
		log.info("pager: {} : {}", pager, "담기나요?");
		
		return "redirect:../";
	}
}
