package com.jang.app.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jang.app.aops.main.Start;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@Autowired
	private Start start;
	
	@GetMapping("/")
	public String home() throws Exception {
		
		start.go();
		
		return "index";	
	}
}
