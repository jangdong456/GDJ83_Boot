package com.jang.app.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jang.app.util.FileManager;
import com.jang.app.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QnaService {
	
	@Autowired
	private QnaMapper qnaMapper;
	@Autowired
	private FileManager fileManager;
	@Value("${app.upload}")
	private String upload;	
	@Value("${board.qna}")
	private String name;
	
	public List<QnaVO> getList(Pager pager) throws Exception {
		pager.makeRow();
		return qnaMapper.getList(pager);
	}
	
	public int add(QnaVO qnaVO, MultipartFile [] attaches) throws Exception {
		log.info("==========Insert Before BoardNum: {}", qnaVO.getBoardNum());
//		int result = qnaMapper.add(qnaVO);
		log.info("==========Insert After BoardNum: {}", qnaVO.getBoardNum());
//		result = qnaMapper.refUpdate(qnaVO);
		log.info("==========Insert After Ref: {}", qnaVO.getRef());
		
		//파일 HDD에 저장 후 DB에 정보를 추가 | 경로 : D:/upload/
		// 배열이니까 for문 사용
		for(MultipartFile mf: attaches) {
			// 파일을 안올렸을 경우 for 실행문 종료 하기 위해서 이코드를 씀
			if(mf==null || mf.isEmpty()) {
				continue;
			}
			String fileName = fileManager.fileSave(upload+name, mf); // D:/upload/qna
			log.info("저장된 파일명 : {}",fileName);
		}
		
		return 0;
	}
	
	public QnaVO getDetail(QnaVO qnaVO) throws Exception {
		return qnaMapper.getDetail(qnaVO);
	}
}
