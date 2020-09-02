package lineageM.services;

import java.util.List;
import java.util.Vector;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import lineageM.domain.dto.EventListDto;
import lineageM.domain.dto.EventRequestDto;
import lineageM.domain.dto.EventResponseDto;
import lineageM.domain.entity.Event;
import lineageM.domain.entity.EventRepository;

@Service
public class EventServiceImpl implements EventService {
	@Autowired
	private EventRepository repository;
	
	@Override
	public void save(EventRequestDto dto) {
		repository.save(dto.toEntity());
	}
	
	@Override
	public List<EventListDto> listAll() {
		Sort sort=Sort.by(Direction.ASC, "no");
		List<Event> result=repository.findAll(sort);
		
		List<EventListDto> list=new Vector<>();
		
		for(Event event:result) {
			EventListDto dto=new EventListDto(event);
			list.add(dto);
		}
		
		return list;
	}

	@Override
	public List<EventResponseDto> listAllByUsed() {
		List<Event> result=repository.findAllByUsed("on");
		
		List<EventResponseDto> list=new Vector<>();
		
		for(Event event:result) {
			EventResponseDto dto=new EventResponseDto(event);
			list.add(dto);
		}
		return list;
	}

	@Transactional //한 단위임을 의미
	@Override
	public void used(long no) {
		//db는 repository한테~
		//일단은 수정을 위해 수정할 목록 불러오기
		//수정이 필요하면 map()로 수정하기
		repository.findById(no)
		.map(entity->entity.usedToggle()).orElse(null);
		
	}

	@Override
	public void delete(long no) {
		repository.deleteById(no);
	}

}
