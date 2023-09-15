package xyz.itwill.dao;

import java.util.List;
import java.util.Map;

import xyz.itwill.dto.Festival;

public interface ShopDAO {
	
	List<Festival> selectAllFestivalList(Map<String, Object> map);
	int selectAllFestivalCount();

}
