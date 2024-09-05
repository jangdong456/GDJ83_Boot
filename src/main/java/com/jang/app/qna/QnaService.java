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

		int result = qnaMapper.add(qnaVO);

		result = qnaMapper.refUpdate(qnaVO);
		
		if(result ==1) {
			throw new Exception();
		}
		
		//파일 HDD에 저장 후 DB에 정보를 추가 | 경로 : D:/upload/
		// 배열이니까 for문 사용
		for(MultipartFile mf: attaches) {
			// 파일을 안올렸을 경우 for 실행문 종료 하기 위해서 이코드를 씀
			if(mf==null || mf.isEmpty()) {
				continue;
			}
			String fileName = fileManager.fileSave(upload+name, mf); // D:/upload/qna
			
			QnaFileVO qnaFileVO = new QnaFileVO();
			qnaFileVO.setFileName(fileName);
			qnaFileVO.setOriName(mf.getOriginalFilename());
			qnaFileVO.setBoardNum(qnaVO.getBoardNum());
			
			result = qnaMapper.addFile(qnaFileVO);
		}
		
		return result;
	}
	
	public QnaVO getDetail(QnaVO qnaVO) throws Exception {
		return qnaMapper.getDetail(qnaVO);
	}
	
	public QnaFileVO getFileDetail(QnaFileVO qnaFileVO) throws Exception {
		return qnaMapper.getFileDetail(qnaFileVO);
	}
}
