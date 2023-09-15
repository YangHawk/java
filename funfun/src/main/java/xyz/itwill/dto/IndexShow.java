package xyz.itwill.dto;

import java.util.List;

import lombok.Data;

@Data
public class IndexShow {
	private List<Integer> checkedMainFestivalList;
	private List<Integer> checkedExpectFestivalList;
}
