package lineageM.services;

import java.util.List;

import lineageM.domain.dto.BoardDto;

public interface BoardService {

	List<BoardDto> selectList();

	void insert(BoardDto dto);

	BoardDto detail(int no);

	void delete(int no);

	void edit(BoardDto dto);

}
