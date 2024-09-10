package com.jang.app.lamda;

@FunctionalInterface // 1나의 메서드만 허용 가능한 어노테이션
public interface Myfunction {
	
	public int calc(int num1, int num2) throws Exception;
		
}
