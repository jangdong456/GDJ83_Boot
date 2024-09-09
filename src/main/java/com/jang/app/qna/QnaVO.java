package com.jang.app.qna;

import java.sql.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class QnaVO {
	
	private Long boardNum;
	@NotBlank //(message="필수 입니다")
	//비어있으면 안된다 라는 어노테이션
	private String boardWriter;
	private String boardTitle;
	private String boardText;
	private Date createDate;
	private Long ref; 
	private Long step;
	private Long depth;
	private List<QnaFileVO> ar;
	
}
