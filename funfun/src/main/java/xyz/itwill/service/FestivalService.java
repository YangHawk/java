package xyz.itwill.service;

import java.util.List;
import java.util.Map;

import xyz.itwill.dto.Festival;
import xyz.itwill.exception.FestivalinfoNotFoundException;

public interface FestivalService {
	void addFestival(Festival festival);

	void modifyFestival(Festival festival) throws FestivalinfoNotFoundException;

	void modifyFestivalState(Festival festival) throws FestivalinfoNotFoundException;

	Festival getFestival(int idx) throws FestivalinfoNotFoundException;

	Map<String, Object> getFestivalList(int pageNum, int pageSize, String selectKeyword);

	double calcAchievementPercentage(int idx) throws FestivalinfoNotFoundException;
	
	Map<String, Object> getAllFestivalList(int pageNum, int pageSize, String selectKeyword);
	
	Map<String, Object> getOngoingFestivalList(int pageNum, int pageSize, String selectKeyword);

	Map<String, Object> getUpcomingFestivalList(int pageNum, int pageSize, String selectKeyword);

	Map<String, Object> getSponsorFestivalList(int pageNum, int pageSize, String selectKeyword);

	Map<String, Object> getCollectedFestivalList(int pageNum, int pageSize, String selectKeyword);
	
	List<Festival> getOngoingFestival() throws FestivalinfoNotFoundException;
	
	List<Festival> getIndexMainFestival();
	List<Festival> getIndexExpectFestival();
	
	void autoModifyFestivalState();
	
	void modifyFestivalIndexShow1(List<Integer> checkedMainFestivalList);
	void modifyFestivalIndexShow2(List<Integer> checkedExpectFestivalList);
	
	Map<String, Object> getMainFestivalList(int pageNum);
	Map<String, Object> getExpectFestivalList(int pageNum);
	
	List<Festival> getApprovedFestivalList(String accountId);
	List<Festival> getPendingFestivalList(String accountId);
	List<Festival> getRejectedFestivalList(String accountId);
}
