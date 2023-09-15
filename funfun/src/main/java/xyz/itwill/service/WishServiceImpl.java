package xyz.itwill.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dao.WishDAO;
import xyz.itwill.dto.Wish;
import xyz.itwill.util.Pager;

@Service
@RequiredArgsConstructor
public class WishServiceImpl implements WishService {
	private final WishDAO wishDAO;
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void addWish(Wish wish) {
		wishDAO.insertWish(wish);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void removeWish(int festivalIdx, String accountId) {
		wishDAO.deleteWish(festivalIdx, accountId);
	}

	@Override
	public Wish getWishCount(int festivalIdx) {
		return wishDAO.selectWishCount(festivalIdx);
	}
	
	@Override
	public Wish getMyWishFestival(int festivalIdx, String accountId) {
		return wishDAO.selectMyWishFestival(festivalIdx, accountId);
	}

	@Override
	public List<Wish> getMyWishList(String accountId) {
		return wishDAO.selectMyWishList(accountId);
	}

	@Override
	public Map<String, Object> getMyWishList(int pageNum, String accountId) {
		int totalBoard = wishDAO.selectMyWishCount(accountId);
		int blockSize = 5;
		int pageSize = 10;
		Pager pager = new Pager(pageNum, totalBoard, pageSize, blockSize);
		
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("startRow", pager.getStartRow());
		pageMap.put("endRow", pager.getEndRow());
		pageMap.put("accountId", accountId);
		
		List<Wish> wishList = wishDAO.selectWishList(pageMap);
		
		Map<String, Object> wishMap = new HashMap<String, Object>();
		wishMap.put("wishList", wishList);
		wishMap.put("pager", pager);
		
		return wishMap;
	}
}
