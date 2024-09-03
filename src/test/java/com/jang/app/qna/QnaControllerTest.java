package com.jang.app.qna;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate.Param;

@SpringBootTest
@AutoConfigureMockMvc
class QnaControllerTest {
	
//	private WebApplicationContext ctx;
	
	@Autowired
	private MockMvc mockMvc;
	
//	@Test
//	void test() {
//		//mockMvc 객체를 만들어줘야함
//		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
//	}
	
	@Test
	void addTest() throws Exception {
		mockMvc.perform(
				post("/qna/add")
				.param("boardWriter", "test7")
				.param("boardTitle", "test7")
				.param("boardText", "test7")
				.param("Step", "0")
				.param("Depth", "0")
				)
				.andExpect(status().isOk())
				.andDo(print());
	}
	
//	@Test
//	void getListTest() throws Exception {
//		
//		// 원래 Map은 키 중복 허용 하지 않지만 ex) a,1 => a,2
//		// MultiValueMap은 키 중복을 허용 한다. ex) a,1=>a,2 -> a {1,2}
//		// 파라미터값이 여러개 일 때 있으므로 만들어졌다.
//		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//		map.add("page", "1");
//		map.add("kind", "k1");
//		map.add("search", "2");
//		
//		mockMvc.perform(
//					get("/qna/list")
//					.params(map)
//					
////					.param("page", "1")
////					.param("kind", "k1")
////					.param("search", "2")
//				)
//				.andDo(print());
//		;
//		
//	}
	
//	@Test
//	public void getDetailTest() throws Exception {
//		
//		mockMvc.perform
//			(
//				get("/qna/detail")
//				.param("boardNum", "110")
//			)
//			.andExpect(status().isOk())
//			.andDo(print());
//		
//	}

}
