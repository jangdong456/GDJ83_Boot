package com.jang.app.aops.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jang.app.aops.transfers.Transfer;

@Component
public class Start {
	
	@Autowired
	private Transfer transfer;
	
	public void go() {
		transfer.takeBus(30);
		transfer.takeSubway(5L, "hello");
		transfer.walk();
	}
}
