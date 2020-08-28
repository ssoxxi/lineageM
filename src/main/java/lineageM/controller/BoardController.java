package lineageM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lineageM.domain.dto.BoardDto;
import lineageM.services.BoardService;

@Controller
public class BoardController {
	
	@Autowired //lombok인경우 @Autowired말고 위에 @RequiredArgsContructor해주고 final붙여줌
	private BoardService boardService;
	
	@GetMapping("/board/list")//요청된 url경로
	public ModelAndView board(ModelAndView mv) {
		//db에서 데이터 읽어오기 ->Service필요
		List<BoardDto> boardList=boardService.selectList(); //data리턴
		mv.setViewName("/board/list");//html경로(resources/templates/board)
		mv.addObject("boardList",boardList);//페이지로 가지고 갈 data
		return mv; 
	}
	
	@GetMapping("/board/write") //요청된 url
	public String write() {
		return "/board/write"; //페이지 이동 (.html)
	}
	
	@PostMapping("/board/write")
	public String write(BoardDto dto) {
		boardService.insert(dto);
		return "redirect:/board/list";//요청주소를 /board/list로 변경해서 재요청
	}
	
	@GetMapping("/board/{no}")//  /board/2 , /board/1, /board/3
	public ModelAndView detail(@PathVariable int no, ModelAndView mv) {
		// no의 게시글정보를 갖고와서 detail페이지에 보내서 보여준다.
		mv.setViewName("/board/detail");///board/detail.html
		
		BoardDto detail=boardService.detail(no);
		mv.addObject("dto", detail);//페이지로 넘어갈 detail data
		return mv;
	}
	
	@PostMapping("/board/edit")
	public String edit(BoardDto dto) {
		//요청된 data를 db로 보내서 수정처리
		boardService.edit(dto); //수정 결과 받아오기
		return "redirect:/board/"+dto.getNo();
	}
	
	@GetMapping("/board/delete/{no}")
	public String delete(@PathVariable int no) {
		boardService.delete(no);
		return "redirect:/board/list";
	}
}
