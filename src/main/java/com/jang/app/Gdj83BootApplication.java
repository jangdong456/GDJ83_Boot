package com.jang.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableAspectJAutoProxy
@EnableScheduling
public class Gdj83BootApplication {

	public static void main(String[] args) {
		SpringApplication.run(Gdj83BootApplication.class, args);
	}

}
