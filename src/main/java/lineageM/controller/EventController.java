package lineageM.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lineageM.domain.dto.EventRequestDto;
import lineageM.services.EventService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class EventController {
	
	@Autowired
	private EventService service;
	
	@GetMapping("/event/list")
	public String event() { 
		return "/event/list";//이동할 페이지 주소경로
	}
	
	@GetMapping("/event/reg")
	public String reg() {
		return "/event/reg";//이동할 페이지 주소경로
	}
	
	@PostMapping("/event/reg") //던진 data를 서버(db)에 저장
	public String reg(@RequestParam("files") MultipartFile[] files, EventRequestDto dto) throws IllegalStateException, IOException {
		//업로드할 위치 설정
		String path="D:/spring/workspace/lineageM/src/main/resources/static"+"/upload/event";
		File dir=new File(path);
		if(!dir.exists()) {//현재 디렉토리가 존재하지 않으면
			dir.mkdirs();//디렉토리 생성
			log.debug("dir생성");
		}
		for(MultipartFile mf :files) {
			String fileName=mf.getOriginalFilename();
			//dto에 파일이름 3개 저장
			dto.getUrls().add(fileName);
			//파일업로드
			mf.transferTo(new File(dir, fileName));
		}
		log.debug(dto);
		
		//완성된 dto정보를 db에 저장
		service.save(dto);
		return "/event/reg";//이동할 페이지 주소경로
	}
}
