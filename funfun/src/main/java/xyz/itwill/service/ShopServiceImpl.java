package xyz.itwill.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dao.ShopDAO;
import xyz.itwill.dto.Festival;
import xyz.itwill.util.Pager;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {
	private final ShopDAO shopDAO;

	@Override
	public Map<String, Object> getAllFestivalList(int pageNum, int pageSize, String selectKeyword) {
		int totalBoard = shopDAO.selectAllFestivalCount();
		int blockSize = 10;
		Pager pager = new Pager(pageNum, totalBoard, pageSize, blockSize);
		
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("startRow", pager.getStartRow());
		pageMap.put("endRow", pager.getEndRow());
		pageMap.put("selectKeyword", selectKeyword);
		
		List<Festival> festivalList = shopDAO.selectAllFestivalList(pageMap);
		
		Map<String, Object> festivalMap = new HashMap<String, Object>();
		festivalMap.put("festivalList", festivalList);
		festivalMap.put("pager", pager);
		
		return festivalMap;
	}

}
