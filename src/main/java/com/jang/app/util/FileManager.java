package com.jang.app.util;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {
	
	public String fileSave(String path, MultipartFile multipartFile) throws Exception {
		// 어디에 저장 ??
		File file = new File(path);
		
		// 폴더가 없으면 만들어라
		if(!file.exists()) {
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
