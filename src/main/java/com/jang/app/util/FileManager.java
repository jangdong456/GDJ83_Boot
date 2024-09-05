package com.jang.app.util;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {
	
	public String fileSave(String path, MultipartFile multipartFile) throws Exception {
		// 어디에 저장하고 fileName을 반환 시키는 목적을 가진 class 코드이다.
		
		File file = new File(path);
		
		// 현재 path 는  D:/upload/qna 경로를 말하고 있다.
		// file.exists() : 파일이 존재하는 여부를 알 수 있다.
		
		// 경로가 없으면 폴더를 만들어라
		if(!file.exists()) {
			
			//file에는 D:/upload/qna 담고있으니까 D:/upload/qna 경로의 폴더를 만든다.
			file.mkdirs();
		}
		
		//저장할 파일명 생성 | 파일명은 중복되면 안된다 ->  UUID.randomUUID().toString()
		// "_"+multipartFile.getOriginalFilename(); => 확장자를 알기위해서 코드를 씀
		String fileName = UUID.randomUUID().toString()+"_"+multipartFile.getOriginalFilename();
		
		//파일을 HDD에 저장
		file = new File(file, fileName);
		multipartFile.transferTo(file);
		
		//저장된 파일명을 리턴하자
		return fileName;
	}
}
