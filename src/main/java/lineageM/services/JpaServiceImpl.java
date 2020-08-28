package lineageM.services;

import java.util.List;
import java.util.Optional;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lineageM.domain.dto.JpaDto;
import lineageM.domain.entity.JpaBoard;
import lineageM.domain.entity.JpaBoardRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class JpaServiceImpl implements JpaService{
	
	@Autowired
	private JpaBoardRepository repository; //db처리할 것 담당
	
	@Override
	public List<JpaDto> list() {
		//db에서 가지고 와서
		List<JpaBoard> result=repository.findAll(); 
		//전부가지고 와서 List<JpaDto>에 담아 return
		//jpaBoard로 가져올 수 있도록 dto에 생성자 수정필요
		List<JpaDto> list=new Vector<>();
		for(JpaBoard jpaBoard : result) {
			// dto에서 board로 갈아태우기
			JpaDto dto= new JpaDto(jpaBoard);
			list.add(dto); //담아주기
		}
		return list;
	}

	@Override
	public void save(JpaDto dto) {
		// dto를 entity로 변환해야함
		repository.save(dto.toEntity());
	}

	@Transactional
	@Override
	public JpaDto getDetail(Long no) {
		/*
		Optional<JpaBoard> op=repository.findById(no);
		if(op.isPresent()) {
			JpaBoard jpaBoard=op.get();
			return new JpaDto(jpaBoard);
		}else {
			return null;
		}
		*/
		//repository.findById(no) Optional 리턴시
		// JpaBoard entity가 나올것이라 단정하고 코딩할수있다.
		// null인경우에 처리는 orElse()
		
		JpaBoard result=repository.findById(no)
				.map(e->e.countIncrement())//수정이 필요한경우 
				.orElse(null);
		
		//result.countIncrement(); //조회수 증가 -->조회수라는 데이터가 수정된 것
		return new JpaDto(result);
		
	}
	

	@Transactional
	@Override
	public void edit(JpaDto dto) {
		
		//DB의 원래 데이터
		JpaBoard board=repository.findById(dto.getNo()).orElse(null);//db에서 id통해 불러옴
		board.update(dto.getSubject(), dto.getContent());
		log.debug("dto : "+dto);
		//repository.save( board );
		//
	}

	@Override
	public void delete(Long no) {
		repository.deleteById(no);
	}

}
