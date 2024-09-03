package com.jang.app.util;

import lombok.Data;

@Data
public class Pager {
	
	private Long perPage=10L;
	private Long startRow;
	private Long page=1L;
	private String kind;
	private String search;
	
	public void makeRow() {
		// page가 1이면 0
		// page가 2이면 10
		// page가 3이면 20
		// perPage : 화면에 보여주는 row 갯수
		// page : 12345 페이지 번호를 나타내는 파라미터 값
		this.startRow = (page-1)*perPage;
	}
	
	//어쩔수 없이 setter getter의 코드를 변경해야 할 경우 작성해줘야함
	
	//getter 작성법 -> 내부에 있는걸 외부로 줘야하기 때문에 반환값을 생각하자 String 
	public String getKind() {
		if(this.kind == null) {
			this.kind="k1";
		}
		return this.kind;
	}
	
	public String getSearch() {
		if(this.search == null) {
			this.search="";
		}
		return this.search;
	}
	
}
