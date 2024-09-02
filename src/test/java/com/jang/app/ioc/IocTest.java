package com.jang.app.ioc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jang.app.robot.Robot;

@SpringBootTest
public class IocTest {
	
	@Autowired
	private Robot robot;
	
	@Test
	void test( ) {
		System.out.println("Test Case");
		robot.getRobotArm().punch();
		assertNotNull(robot);
		
	}
}
