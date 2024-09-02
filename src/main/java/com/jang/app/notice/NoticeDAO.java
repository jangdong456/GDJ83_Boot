package com.jang.app.notice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeDAO {
	
	public List<NoticeVO> getList() throws Exception; 
	
}
