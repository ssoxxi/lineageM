package lineageM.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.websocket.server.PathParam;

import org.aspectj.apache.bcel.classfile.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lineageM.domain.dto.EventListDto;
import lineageM.domain.dto.EventRequestDto;
import lineageM.services.EventService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class EventController {
	@Autowired //container에 service로 등록 되어 있음
	private EventService service;
	
	@GetMapping("/event/delete/{no}")
	public String delete(@PathVariable long no) {
		service.delete(no);
		return "redirect:/event/list";
	}
	
	@GetMapping("/event/used/{no}")
	public String used(@PathVariable long no) {
		service.used(no);
		return "redirect:/event/list";
	}
	
	
	//단순 페이지 이동
	@GetMapping("/event/list")
	public String event(Model model) {
		List<EventListDto> list = service.listAll();
		model.addAttribute("list",list);
		return "/event/list";//이동할 페이지 경로
	}
	
	@GetMapping("/event/reg")
	public String eventReg() {
		
		return "/event/reg";//이동할 페이지 경로
	}
	
	@PostMapping("/event/reg")//오버로딩
	public String eventReg(EventRequestDto dto,@RequestParam(name="files") MultipartFile[] mfs) throws IllegalStateException, IOException {
		for(MultipartFile mf: mfs) {
			//EventRequestDto 에 list<String> ulrs에 추가
			//파일 이름을 dto에 저장
			String fileName=mf.getOriginalFilename();
			dto.getUrls().add(fileName);
			//파일 업로드 
			//로컬환경
			String path="D:/spring/workspace/git/lineageM/src/main/resources/static"+"/upload/event";//파일 업로드 경로
			File dir=new File(path);
			if(!dir.exists()) {//디렉토리 존재하지 않으면
				dir.mkdirs();//디렉토리 생성
			}
			File file=new File(dir, fileName);//dir이름 다음에 file이름으로 구성
			mf.transferTo(file);
			//파일 업로드 완료
		}
		log.debug(dto); //db에 저장할 데이터
		service.save(dto); //저장해줘!
		
		return "/event/reg";
	}
}
