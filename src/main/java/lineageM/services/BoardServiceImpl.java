package lineageM.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lineageM.domain.dto.BoardDto;
import lineageM.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;//
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public List<BoardDto> selectList() {
		// db연결
		return boardMapper.selectBoardList();
	}

	@Override
	public void insert(BoardDto dto) {
		dto.setUser_ip(request.getRemoteAddr());
		//mapper에게 일 시키기
		boardMapper.insert(dto);
	}

	@Override
	public BoardDto detail(int no) {
		return boardMapper.detail(no);
	}

	@Override
	public void delete(int no) {
		boardMapper.delete(no);
	}

	@Override
	public void edit(BoardDto dto) {
		boardMapper.edit(dto);
	}
	
}
