package com.jang.app.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jang.app.qna.QnaMapper;
import com.jang.app.qna.QnaService;
import com.jang.app.qna.QnaVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TestSchedule {
	
	@Autowired
	private QnaMapper qnaMapper;
	
	//@Scheduled(fixedDelayString = "1000", initialDelay = 2000)
	public void test1() throws Exception {
		log.error("Schedule Test");
	}
	
	//@Scheduled(fixedRate = 2000L, initialDelay = 5000L) // 메서드가 실행되고 5초뒤에 메서드 실행 후 2초간격 주기적으로 실행
	public void test2() throws Exception {
		log.error("=====Schedule Test======");
	}
	
//	@Scheduled(cron = "*/5 * * * * *")
	public void test3() throws Exception {
		log.error("Schedule Test");
		QnaVO qnaVO = new QnaVO();
		qnaVO.setBoardWriter("test111");
		qnaVO.setBoardTitle("test111");
		qnaVO.setBoardText("test111");
		
		qnaMapper.add(qnaVO);
		qnaMapper.refUpdate(qnaVO);
	}
 }
