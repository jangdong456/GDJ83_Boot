package com.jang.app.robot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//xml 대신에 java를 이용해서 객체 생성
@Configuration //xml  역할을 하는 어노테이션
public class RobotConfig {
	
	// <beam class=""> 과 동일
	@Bean("ra")
	RobotArm makeArm() {
		return new RobotArm();
	}
	
	@Bean
	Robot makeRobot() {
		Robot robot = new Robot();
		robot.setRobotArm(makeArm());
		return robot;
	}
}
