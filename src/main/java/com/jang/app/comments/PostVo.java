package com.jang.app.comments;

import lombok.Data;

@Data
public class PostVo {
	private Long userId;
	private Long id;
	private String title;
	private String body;
}
