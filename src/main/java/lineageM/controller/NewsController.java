package lineageM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lineageM.domain.dto.NewsDto;
import lineageM.services.NewsService;

@Controller
public class NewsController {
	@Autowired
	private NewsService newsService;
	
	@GetMapping("/news/list")
	public ModelAndView news(ModelAndView mv) {
		List<NewsDto> newsList=newsService.selectList();
		mv.setViewName("/news/list");
		mv.addObject("newsList",newsList);
		return mv;
	}
	
	@GetMapping("/news/write")
	public String write() {
		return "/news/write";
	}
	
	@PostMapping("/news/write")
	public String write(NewsDto dto) {
		newsService.insert(dto);
		return "redirect:/news/list";
	}
	
	@GetMapping("/news/{no}")
	public ModelAndView detail(@PathVariable int no, ModelAndView mv) {
		mv.setViewName("/news/detail");
		NewsDto detail=newsService.detail(no);
		mv.addObject("dto", detail);
		return mv;
	}
	
	@PostMapping("/news/edit")
	public String edit(NewsDto dto) {
		newsService.edit(dto);
		return "redirect:/news/"+dto.getNo();
	}
	
	@GetMapping("/news/delete/{no}")
	public String delete(@PathVariable int no) {
		newsService.delete(no);
		return "redirect:/news/list";
	}
}
