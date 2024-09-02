package com.jang.app.notice;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// @AllArgsConstructor : 매개변수가 있는 생성자
// @NoArgsConstructor : 매개변수가 없는 생성자
// @ToString : 멤버변수 출력해줌
// @Data : @Getter + @Setter + @NoArgsConstructor + @ToString 합친거


//DTO : Data Transfer Object
//VO : Value Object(읽기 전용임)
// 둘의 차이점 :  vo는 불변값 및 읽기 전용 -> setter를 안만듬 -> return값으로 사용(수정X)
//              dto는 : Getter/Setter 자유롭게 사용(수정O)

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
@Data
public class NoticeVO {
	private Long boardNum;
	private String boardWriter;
	private String boardTitle;
	private String boardText;
	private Date createDate;

}
