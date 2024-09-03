package com.jang.app.qna;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.print.Pageable;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jang.app.util.Pager;

@SpringBootTest
public class QnaMapperTest {
	
	@Autowired
	private QnaMapper qnaMapper;
	
	@Test
	void getDetailTest() throws Exception {
		QnaVO qnaVO = new QnaVO();
		qnaVO.setBoardNum(110L);
		qnaVO = qnaMapper.getDetail(qnaVO);
		
		assertNotNull(qnaVO);
	}
	
	@Test
	void getListTest() throws Exception {
		Pager pager1 = new Pager();
		
		pager1.setPage(1L);
		pager1.setKind("k1");
		pager1.setSearch("2");
		pager1.makeRow();
		
		List<QnaVO> ar = qnaMapper.getList(pager1);
		

		assertNotEquals(0, ar.size());
	}
	
	//@Test
//	void addTest() throws Exception {
//		
//		for(int i=3; i<110; i++) {			
//			QnaVO qnaVO = new QnaVO();
//			qnaVO.setBoardText("c" +i);
//			qnaVO.setBoardTitle("t" +i);
//			qnaVO.setBoardWriter("w" +i);
//			qnaVO.setRef((long)i);
//			qnaVO.setStep(0L);
//			qnaVO.setDepth(0L);
//			
//			int result = qnaMapper.add(qnaVO);
//			
//			if(i%10 == 0) {
//				Thread.sleep(500);
//			}
//		}
//	}
	
	//Insert test 
	
	// TDD 테스트 주도 방식 : 일부러 에러를 계속 발생시키면서 개발하는 방법
}
