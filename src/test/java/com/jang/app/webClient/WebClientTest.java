package com.jang.app.webClient;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.jang.app.comments.PostVo;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@SpringBootTest
public class WebClientTest {
	
//	@Test
//	public void test1() {
//		//RestTemplate
//		RestTemplate restTemplate = new RestTemplate();
//		
//		// paramter 생성
//		// MultiValueMap: 키는 1나인데 값을 여러개 넣을 수 있음
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//		params.add("postId", "1");
//		
//		//요청 객체 생성
//		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String,String>>(params, null);
//		
//		//요청 전송 응답 처리
//		//  String.class : 응답받을 데이터 타입 적으라는 뜻 
//		ResponseEntity<PostVo []> res = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts", PostVo [].class);
//		PostVo [] result = res.getBody();
//		log.info("reuslt : {} " ,result.length);
//	
//	}
	
	@Test
	void WebClientTest() {
		WebClient webClient = WebClient.builder()
										.baseUrl("https://jsonplaceholder.typicode.com/")
										.build();
		
		Flux<PostVo> res = webClient.get() 
				 .uri("posts")
				 .retrieve()
				 .bodyToFlux(PostVo.class);
		
		PostVo postVO = res.blockFirst();
		
		res.subscribe(v -> {
			PostVo p = v;
			log.info("V : {} ", p);
		});
	}
}
