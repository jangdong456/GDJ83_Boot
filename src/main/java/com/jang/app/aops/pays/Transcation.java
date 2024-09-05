package com.jang.app.aops.pays;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Transcation {
	
	@AfterThrowing("execution(* com.jang.app.*.*.set*(..))")
	public void rollBack() {
		
	}
}
