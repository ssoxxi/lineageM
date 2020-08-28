package lineageM.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //optional(명확히 하기 위해 사용)
public interface JpaBoardRepository extends JpaRepository<JpaBoard, Long>{//<연결할 것, 기본키타입>
	//CRUD에 대한 쿼리처리해주는 곳
	
}
