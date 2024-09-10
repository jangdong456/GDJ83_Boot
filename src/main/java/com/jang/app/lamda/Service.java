package com.jang.app.lamda;

public class Service {
	
	public void act() throws Exception {
		
		Myfunction minus = new Minus();
		minus.calc(0, 0);
		
		// overriding
		Myfunction mf = (int n1, int n2) ->  n1+n2;
		
		int result = mf.calc(3, 2);
		
		mf = (int n1, int n2) ->  n1*n2;
		
		result = mf.calc(3, 12);
	}
}
