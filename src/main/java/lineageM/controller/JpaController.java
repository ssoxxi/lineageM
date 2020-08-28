package lineageM.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lineageM.domain.dto.JpaDto;
import lineageM.services.JpaService;
import lombok.extern.log4j.Log4j2;

@Log4j2 //log찍기 위함
@Controller
public class JpaController {
	@Autowired //singleton
	private JpaService service;//서비스 객체 등록
	//private final JpaService jpaService; 하려면 @RequiredArgsConstructor를 해줘야함
	
	@GetMapping("/jpa/list")
	public String jpaList(Model model) {
		//db에서 리스트 가져와서 /jpa/list.html 페이지로 보내기
		List<JpaDto> list=service.list();//JpaDto에다가 받아오기
		//따로 JpaDto하는 이유는 엔티티는 실제 db이고 전달은 dto로 한다(data 무결성)
		model.addAttribute("jpaList", list); //request객체라고 보면 됨
		//list변수에 담은 data를 가지고 올 때 jpaList로 가져옴(request 보관함에 넣어놓는 것)
		//가져올 때도 jpaList를 키라고 생각하고 가져오면 됨
		return "/jpa/list";
	}
	
	@GetMapping("/jpa/write")
	public String write() {
		return "/jpa/write";
	}
	@PostMapping("jpa/write")
	public String write(JpaDto dto, HttpServletRequest request) {
		log.info("dto: "+dto);//println과 같은 역할로 로그찍어냄
		//서버에 올리고 log파일에 log를 찍어서 확인 가능
		//db에 dto저장하기
		dto.setUser_ip(request.getRemoteAddr());//ip 세팅
		service.save(dto);
		//dto 저장 후 페이지 이동
		return "redirect:/jpa/list";
	}
	
	@GetMapping("/jpa/{no}")// /jpa/1, /jpa/2, /jpa/3
	public String detail(@PathVariable Long no, Model model) {
		//no에 해당하는 상세정보 가져오기
		JpaDto detail=service.getDetail(no);//dto에 한 개 가져옴
		//상세정보 저장(attribute) 
		model.addAttribute("dto", detail); //detail정보를 dto이름으로 저장
		//페이지 이동
		return "/jpa/detail";
	}
	
	@PostMapping("/jpa/edit")
	public String edit(JpaDto dto) {
		//no, subject, content
		//service에 넘기기
		service.edit(dto);
		//수정 후 상세 페이지 이동
		return "redirect:/jpa/"+dto.getNo();
	}
	
	@GetMapping("/jpa/delete/{no}")
	public String delete(@PathVariable Long no) {
		service.delete(no);
		return "redirect:/jpa/list";
	}
}
