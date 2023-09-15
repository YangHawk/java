package xyz.itwill.service;

import java.util.Map;

public interface ShopService {

	Map<String, Object> getAllFestivalList(int pageNum, int pageSize, String selectKeyword);
}
