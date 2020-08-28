package lineageM.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import lineageM.domain.dto.NewsDto;


@Mapper
public interface NewsMapper {

	List<NewsDto> selectNewsList();

	void insert(NewsDto dto);

	NewsDto detail(int no);

	void edit(NewsDto dto);

	void delete(int no);
	
}
