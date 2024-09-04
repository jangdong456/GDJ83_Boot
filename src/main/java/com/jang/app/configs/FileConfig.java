package com.jang.app.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//설정 class:  @Configuration 주면 스프링이 설정 파일인줄알고 여기서부터 읽는다. -> xml 하고 비슷
@Configuration

//WebMVC Configure 구현 해야함 : Web 설정에 관련된 것들
public class FileConfig implements WebMvcConfigurer{
	
	@Value("${app.url.path}")
	private String url;
	@Value("${app.upload.location}")
	private String file;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 이런 URL(자원)이 들어왔을때 file value에서 찾아라 file: D:/upload/ 
		// <resourece mapping="/files/**" location="D:/upload/"/>
		// addResourceHandler() : 어떤 url이 왔을때 contorller 안보내고 여기서 설정한 url로 매핑할거냐 라는 코드 작성
		// addResourceLocations() : 실제 파일이 저장된 곳을 가르킴
		registry.addResourceHandler(url)
				.addResourceLocations(file); 
	}
}
