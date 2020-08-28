package lineageM.services;

import java.util.List;

import lineageM.domain.dto.NewsDto;

public interface NewsService {

	List<NewsDto> selectList();

	void insert(NewsDto dto);

	NewsDto detail(int no);

	void edit(NewsDto dto);

	void delete(int no);


}
