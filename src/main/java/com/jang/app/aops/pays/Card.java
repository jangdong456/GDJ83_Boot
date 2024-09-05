package com.jang.app.aops.pays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Aspect //설정
public class Card {
	
	@Around("execution(* com.jang.app.aops.transfers.Transfer.take*(..))")
	public Object CardCheck(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("=== Before 카드 체크 ===");
		log.info("=== Args : {} ",joinPoint.getArgs());
		
		Object obj = joinPoint.proceed(); // point-cut
		// 지금 joinPoint는 @Around("execution(* com.jang.app.aops.transfers.Transfer.take*())") 통해서 
		// 알수가 있다.
		log.info("=== Return : {}", obj);
		log.info("=== After 카드 체크 ===");
		
		return obj;
	}
}
