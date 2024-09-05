package com.jang.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
// @EnableAspectJAutoProxy 생략가능 : 안될 때 고려 해야함
public class Gdj83BootApplication {

	public static void main(String[] args) {
		SpringApplication.run(Gdj83BootApplication.class, args);
	}

}
