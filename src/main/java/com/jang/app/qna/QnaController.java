package com.jang.app.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	public void getList(Pager pager, Model model) throws Exception {
		List<QnaVO> ar = qnaService.getList(pager);
		
		model.addAttribute("list",ar);
		model.addAttribute("pager",pager);
		
		log.info("pager: {} : {}", pager, "담기나요?");
		
	
	}
	
	@GetMapping("add")
	public void add() throws Exception {
		
	}
	
	@PostMapping("add")
	public String add(QnaVO qnaVO) throws Exception {
		
		int result = qnaService.add(qnaVO);
		return "redirect:./list";
		
	}
	
	@GetMapping("detail")
	public void getDetial(QnaVO qnaVO, Model model) throws Exception {
		qnaVO = qnaService.getDetail(qnaVO);
		model.addAttribute("vo", qnaVO);
		
	}
}
