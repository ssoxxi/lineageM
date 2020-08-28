package lineageM.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import lineageM.domain.dto.BoardDto;


@Mapper
public interface BoardMapper {
	//jsp: namespace.id 
	// spring: mapper 인터페이스 경로와 xml의 namespace와 일치시켜주면 됨
	List<BoardDto> selectBoardList();
	//BoardMapper selectBoardList는 mapper.xml의 id와 일치

	void insert(BoardDto dto);

	BoardDto detail(int no);

	void delete(int no);

	void edit(BoardDto dto);
}
