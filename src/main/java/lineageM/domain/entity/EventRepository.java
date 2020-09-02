package lineageM.domain.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lineageM.domain.dto.EventRequestDto;

@Repository 
public interface EventRepository extends JpaRepository<Event, Long> {

	List<Event> findAllByUsed(String string);

}
