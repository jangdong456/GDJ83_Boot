package com.jang.app.notice;

import java.sql.Date;

import lombok.Data;

@Data
public class NoticeVO {
	
	private Long board_num;
	private String board_wirter;
	private String board_title;
	private String board_text;
	private Date create_date;
	
}
