package xyz.itwill.mapper;

import java.util.List;
import java.util.Map;

import xyz.itwill.dto.Festival;

public interface IndexMapper {
	Festival selectFestival(int idx);
		
	List<Festival> selectFestivalList(Map<String, Object> map);
}
