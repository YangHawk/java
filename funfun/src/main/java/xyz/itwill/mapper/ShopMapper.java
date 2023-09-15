package xyz.itwill.mapper;

import java.util.List;
import java.util.Map;

import xyz.itwill.dto.Festival;

public interface ShopMapper {
	int selectAllFestivalCount();
	List<Festival> selectAllFestivalList(Map<String, Object> map);
	
}
