package lineageM.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lineageM.domain.dto.NewsDto;
import lineageM.mapper.NewsMapper;

@Service
public class NewsServiceImpl implements NewsService {
	@Autowired
	private NewsMapper newsMapper;
	
	@Override
	public List<NewsDto> selectList() {
		return newsMapper.selectNewsList();
	}

	@Override
	public void insert(NewsDto dto) {
		newsMapper.insert(dto);
	}

	@Override
	public NewsDto detail(int no) {
		return newsMapper.detail(no);
	}

	@Override
	public void edit(NewsDto dto) {
		newsMapper.edit(dto);
		
	}

	@Override
	public void delete(int no) {
		newsMapper.delete(no);
	}

	
}
