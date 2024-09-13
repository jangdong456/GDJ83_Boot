package com.jang.app.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jang.app.util.Pager;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/qna/*")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@Value("${board.qna}")
	private String board;
	
	@ModelAttribute("board")
	public String getBoard() {
		return this.board;
	}
	
	@GetMapping("list")
	@CrossOrigin
	public List<QnaVO> getList(Pager pager) throws Exception {

		List<QnaVO> ar = qnaService.getList(pager);
		return ar;
	}
	
	@GetMapping("add")
	public void add(@ModelAttribute QnaVO qnaVO) throws Exception { // @ModelAttribute 원래 생략이 되어있는 상태이다. 
		
	}
	
	@PostMapping("add")
	public String add(@Valid QnaVO qnaVO, BindingResult bindingResult ,MultipartFile [] attaches) throws Exception {
		log.error("Writer 비어있음");
		if(bindingResult.hasErrors()) {
			return "qna/add";
		}
		
		// @Valid : 어느 파라미터에 검증 할지 앞에다 적용 -> 그러면 매개변수 받기전에 검증을 실행한다.
		// 검증의 결과물을 담는 객체 : BindingResult
		// @Valid 사용하면 바로뒤에 담는 객체를 선언 해야만 작동된다.
		
		int result = qnaService.add(qnaVO,attaches);
		return "redirect:./list";
		
	}
	
	@GetMapping("detail/{boardNum}/{name}")
	public QnaVO getDetial(@PathVariable(name = "boardNum") Long bn, @PathVariable String name, QnaVO qnaVO, Model model) throws Exception {
		log.info("BoardNum : {}", bn);
		log.info("Name : {}", name);
		
		qnaVO = qnaService.getDetail(qnaVO);
		
		return qnaVO;
	}
	
	// 프로젝트시 qnaFileVO 상속받게끔 해야 편하게 할 수 있음
	@GetMapping("fileDown")
	public String fileDown(QnaFileVO qnaFileVO, Model model) throws Exception {
		qnaFileVO = qnaService.getFileDetail(qnaFileVO);
		model.addAttribute("file", qnaFileVO);
		
		return "fileDownView";
	}
}
