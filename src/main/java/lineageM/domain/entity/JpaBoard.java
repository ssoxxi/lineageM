package lineageM.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicUpdate //update 쿼리 실행시 null인 컬럼을 제외시킴
//update 쿼리시 변경되지 않은 컬럼도 제외시킴
//insert 쿼리시 null로 되어있는 컬럼 제외시킴
@Getter //엔티티는 db라고 생각하므로 getter만 있으면 됨( set잘못 건들면 data변동생김)
@NoArgsConstructor //lombok사용하고 있으므로 생성자 세팅
@EntityListeners(AuditingEntityListener.class)// listener대기(생성시 자동반영)
@Table(name = "jpa_board")
@Entity //db만들기
public class JpaBoard {
	//컬럼 생성
	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //자동 반영
	private Long no;
	
	@Column(length = 500,nullable = false) //컬럼 길이정보, not null
	private String subject;
	
	private int count;
	
	private String writer;
	
	@CreatedDate //날짜 생성하여 리스너를 통해 자동 반영
	@Column(nullable = false)
	private LocalDateTime reg_date; 
	@Column(nullable = false)
	private String user_ip;
	@Column(columnDefinition = "TEXT", nullable = false) //oracle은 CLOB
	private String content;
	
	@Builder  //생성자 생성 방법( 빌더 자동으로 만들어주고 빌더패턴 클래스 생성)
	public JpaBoard(String subject, String writer, String user_ip, String content) {
		//자동생성되는 컬럼 제외
		this.subject = subject;
		this.writer = writer;
		this.user_ip = user_ip;
		this.content = content;
	}
	
	public JpaBoard update(String subject, String content) {
		this.subject=subject;
		this.content=content;
		return this;
	}
	
	public JpaBoard countIncrement() {
		this.count++;
		return this;
	}
 	
}
