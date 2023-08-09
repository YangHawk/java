package xyz.itwill10.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PointBoard {
	private int idx;
	private String writer;
	private String subject;
}
