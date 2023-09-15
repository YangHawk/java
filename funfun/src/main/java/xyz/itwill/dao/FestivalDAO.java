package xyz.itwill.dao;

import java.util.List;
import java.util.Map;

import xyz.itwill.dto.Festival;

public interface FestivalDAO {
	//등록자 권한을 가진 사용자가 영화제 정보를 등록할 때 사용될 메소드
	int insertFestival(Festival festival);
		
	//등록자 권한을 가진 사용자가 영화제 정보를 수정할 때 사용될 메소드
	int updateFestival(Festival festival);
		
	//관리자 권한을 가진 사용자가 영화제 상태(승인전,진행예정, 진행중, 종료, 삭제)를 변경할 때 사용될 메소드
	int updateFestivalState(Festival festival);
		
	//하나의 영화제 정보를 검색할 때 사용될 메소드
	Festival selectFestival(int idx);
	
	int selectFestivalCount(String selectKeyword);
	int selectAllFestivalCount(String selectKeyword);
	int selectOngoingFestivalCount(String selectKeyword);
	int selectUpcomingFestivalCount(String selectKeyword);
		
	// 관리자가 영화제를 검색할 때 사용!
	List<Festival> selectFestivalList(Map<String, Object> map);
	
	List<Festival> selectAllFestivalList(Map<String, Object> map);

	List<Festival> selectOngoingFestivalList(Map<String, Object> map);

	List<Festival> selectUpcomingFestivalList(Map<String, Object> map);

	List<Festival> selectSponsorFestivalList(Map<String, Object> map);

	List<Festival> selectCollectedFestivalList(Map<String, Object> map);
		
	//진행중인 영화제를 검색할 때 사용
	List<Festival> selectOngoingFestival();
		
	//index 메인페이지의 마감임박 영화제 3개를 출력하기 위해 사용될 메소드
	List<Festival> selectIndexMainFestival();
	
	int autoUpdateFestivalState();

	int updateFestivalIndexShow1To0();
	int updateFestivalIndexShow2To0();
	int updateFestivalIndexShow1To1(List<Integer> checkedMainFestivalList);
	int updateFestivalIndexShow2To1(List<Integer> checkedExpectFestivalList);
	
	List<Festival> selectMainFestivalList(Map<String, Object> map);
	int selectMainFestivalCount();
	
	List<Festival> selectIndexExpectFestival();
	
	List<Festival> selectExpectFestivalList(Map<String, Object> map);
	int selectExpectFestivalCount();
	
	//등록자가 나의 영화제 페이지에서 등록현황을 확인할 때 사용!
	List<Festival> selectApprovedFestivalList(String accountId);
	List<Festival> selectPendingFestivalList(String accountId);
	List<Festival> selectRejectedFestivalList(String accountId);
}
