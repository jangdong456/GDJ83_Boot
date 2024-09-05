package com.jang.app.aops.transfers;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jang.app.aops.pays.Card;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Transfer {
	
	@Autowired
	private Card card;
	
	public String takeBus(int num) {
		log.info("=== 버스 타기 ===");
		return "bus";
	}
	
	public void takeSubway(Long m, String name) {	
		log.info("=== 지하철 타기 ===");	
	}
	
	public void walk() {
		log.info("=== 걸어 가기 ===");
	}
	
}
