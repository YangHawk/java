package xyz.itwill.service;

import java.util.List;
import java.util.Map;

import xyz.itwill.dto.Wish;

public interface WishService {
	//위시 추가
	void addWish(Wish wish);
	//위시 삭제
	void removeWish(int festivalIdx, String accountId);
	//영화제 페이지에서 위시수를 출력시키기 위한 메소드
	Wish getWishCount(int festivalIdx);
	//영화제 페이지에서 이 영화제를 위시한 적이 있는지 확인하기 위한 메소드
	Wish getMyWishFestival(int festivalIdx, String accountId);
	//위시리스트 페이지에서 본인의 위시목록을 가져오기 위한 메소드
	List<Wish> getMyWishList(String accountId);
	
	Map<String, Object> getMyWishList(int pageNum, String accountId);
}
