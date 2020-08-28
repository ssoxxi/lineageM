package lineageM.domain.dto;

import java.time.LocalDateTime;

import lineageM.domain.entity.JpaBoard;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor //default constructor
@Data //get/set생성
public class JpaDto {
	//db와 연결할 데이터들
	private Long no;
	private String subject;
	private int count;
	private String writer;
	private LocalDateTime reg_date;
	private String user_ip;
	private String content;
	
	public JpaDto(JpaBoard jpaBoard) {
		this.no = jpaBoard.getNo();
		this.subject = jpaBoard.getSubject();
		this.count = jpaBoard.getCount();
		this.writer = jpaBoard.getWriter();
		this.reg_date = jpaBoard.getReg_date();
		this.user_ip = jpaBoard.getUser_ip();
		this.content = jpaBoard.getContent();
	}
	
	//get/set메소드필요 ->@Data 해주면 자동 생성
	
	//entity 변환작업
	public JpaBoard toEntity() {
		return JpaBoard.builder()
				.subject(subject)
				.content(content)
				.user_ip(user_ip)
				.writer(writer)
				.build();
		/*return new JpaBoard(subject, writer, user_ip, content);*/
		// 순서에 의해 데이터가 망가지는 것을 방지하기 위해 builder패턴 사용
	}
}
