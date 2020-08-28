package lineageM.domain.dto;

import java.util.List;
import java.util.Vector;

import lineageM.domain.entity.Event;
import lombok.Data;

@Data
public class EventRequestDto {
	String l_text;
	String t_text;
	String b_text;
	
	List<String> urls=new Vector<>();//url
	
	public Event toEntity() {
		return Event.builder()
				.l_text(l_text)
				.t_text(t_text)
				.b_text(b_text)
				.l_url(urls.get(0))
				.t_url(urls.get(1))
				.b_url(urls.get(2))
				.build();
	}
}
