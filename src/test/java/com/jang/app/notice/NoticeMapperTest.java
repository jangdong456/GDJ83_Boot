package com.jang.app.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class NoticeMapperTest {
	
	@Autowired
	private NoticeMapper noticeMapper;
	
	@Test
	void getListTest() throws Exception {
		List<NoticeVO> ar = noticeMapper.getList(1L);
		
		
//		for(NoticeVO noticeVOset: ar) {
//			noticeVOset.setBoard_num(3L);
//			noticeVOset.setBoard_text("test3");
//			noticeVOset.setBoard_title("test3");
//			noticeVOset.setBoard_writer("jang3");
//			noticeVOset.setCreate_date("2024-09-02 05:46:34.000");
//		}
			
		for(NoticeVO noticeVO: ar) {
			System.out.println(noticeVO.toString());		
		}	
		assertNotEquals(0, ar.size());	
	}
}
