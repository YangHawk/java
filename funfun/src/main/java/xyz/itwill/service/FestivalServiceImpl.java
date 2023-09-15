package xyz.itwill.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dao.ExpectDAO;
import xyz.itwill.dao.FestivalDAO;
import xyz.itwill.dto.Festival;
import xyz.itwill.exception.FestivalinfoNotFoundException;
import xyz.itwill.util.Pager;

@Service
@RequiredArgsConstructor
public class FestivalServiceImpl implements FestivalService {
	private final FestivalDAO festivalDAO;
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void addFestival(Festival festival) {
		festivalDAO.insertFestival(festival);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void modifyFestival(Festival festival) throws FestivalinfoNotFoundException {
		festivalDAO.updateFestival(festival);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void modifyFestivalState(Festival festival) throws FestivalinfoNotFoundException {
		festivalDAO.updateFestivalState(festival);
	}

	@Override
	public Festival getFestival(int idx) throws FestivalinfoNotFoundException {
		
		return festivalDAO.selectFestival(idx);
	}

	@Override
	public Map<String, Object> getFestivalList(int pageNum, int pageSize, String selectKeyword) {
		int totalBoard = festivalDAO.selectFestivalCount(selectKeyword);
		int blockSize = 10;
		Pager pager = new Pager(pageNum, totalBoard, pageSize, blockSize);
		
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("startRow", pager.getStartRow());
		pageMap.put("endRow", pager.getEndRow());
		pageMap.put("selectKeyword", selectKeyword);
		
		List<Festival> festivalList = festivalDAO.selectFestivalList(pageMap);
		
		Map<String, Object> festivalMap = new HashMap<String, Object>();
		festivalMap.put("festivalList", festivalList);
		festivalMap.put("pager", pager);
		
		return festivalMap;
	}
	
	//펀딩 달성률을 계산하기 위한 메소드
	@Override
	public double calcAchievementPercentage(int idx) throws FestivalinfoNotFoundException {
		Festival festival = getFestival(idx);
		
		if (festival == null) {
			throw new FestivalinfoNotFoundException("존재하지 않는 영화제 입니다.");
		}
		if (festival.getTarget() <= 0 || festival.getCollected() <=0) {
			return 0.0;
		}
		
		double achievementPercentage =  ((double)festival.getCollected() / festival.getTarget()) * 100;
		achievementPercentage = Math.round(achievementPercentage * 100) / 100.0;
		
		return achievementPercentage;
	}
	
	public void calcAchievementPercentageList(List<Festival> festivalList) {
		
	}

	@Override
	public List<Festival> getOngoingFestival() throws FestivalinfoNotFoundException {
		return festivalDAO.selectOngoingFestival();
	}
	
	
	@Override
	public Map<String, Object> getAllFestivalList(int pageNum, int pageSize, String selectKeyword) {
		int totalBoard = festivalDAO.selectAllFestivalCount(selectKeyword);
		int blockSize = 10;
		Pager pager = new Pager(pageNum, totalBoard, pageSize, blockSize);
		
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("startRow", pager.getStartRow());
		pageMap.put("endRow", pager.getEndRow());
		pageMap.put("selectKeyword", selectKeyword);
		
		List<Festival> festivalList = festivalDAO.selectAllFestivalList(pageMap);
		
		Map<String, Object> festivalMap = new HashMap<String, Object>();
		festivalMap.put("festivalList", festivalList);
		festivalMap.put("pager", pager);
		
		return festivalMap;
	}
	
	@Override
	public Map<String, Object> getOngoingFestivalList(int pageNum, int pageSize, String selectKeyword) {
		int totalBoard = festivalDAO.selectOngoingFestivalCount(selectKeyword);
		int blockSize = 10;
		Pager pager = new Pager(pageNum, totalBoard, pageSize, blockSize);
		
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("startRow", pager.getStartRow());
		pageMap.put("endRow", pager.getEndRow());
		pageMap.put("selectKeyword", selectKeyword);
		
		List<Festival> festivalList = festivalDAO.selectOngoingFestivalList(pageMap);
		
		Map<String, Object> festivalMap = new HashMap<String, Object>();
		festivalMap.put("festivalList", festivalList);
		festivalMap.put("pager", pager);
		
		return festivalMap;
	}

	@Override
	public Map<String, Object> getUpcomingFestivalList(int pageNum, int pageSize, String selectKeyword) {
		int totalBoard = festivalDAO.selectUpcomingFestivalCount(selectKeyword);
		int blockSize = 10;
		Pager pager = new Pager(pageNum, totalBoard, pageSize, blockSize);
		
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("startRow", pager.getStartRow());
		pageMap.put("endRow", pager.getEndRow());
		pageMap.put("selectKeyword", selectKeyword);
		
		List<Festival> festivalList = festivalDAO.selectUpcomingFestivalList(pageMap);
		
		Map<String, Object> festivalMap = new HashMap<String, Object>();
		festivalMap.put("festivalList", festivalList);
		festivalMap.put("pager", pager);
		
		return festivalMap;
	}

	@Override
	public Map<String, Object> getSponsorFestivalList(int pageNum, int pageSize, String selectKeyword) {
		int totalBoard = festivalDAO.selectAllFestivalCount(selectKeyword);
		int blockSize = 10;
		Pager pager = new Pager(pageNum, totalBoard, pageSize, blockSize);
		
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("startRow", pager.getStartRow());
		pageMap.put("endRow", pager.getEndRow());
		pageMap.put("selectKeyword", selectKeyword);
		
		List<Festival> festivalList = festivalDAO.selectSponsorFestivalList(pageMap);
		
		Map<String, Object> festivalMap = new HashMap<String, Object>();
		festivalMap.put("festivalList", festivalList);
		festivalMap.put("pager", pager);
		
		return festivalMap;
	}

	@Override
	public Map<String, Object> getCollectedFestivalList(int pageNum, int pageSize, String selectKeyword) {
		int totalBoard = festivalDAO.selectAllFestivalCount(selectKeyword);
		int blockSize = 10;
		Pager pager = new Pager(pageNum, totalBoard, pageSize, blockSize);
		
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("startRow", pager.getStartRow());
		pageMap.put("endRow", pager.getEndRow());
		pageMap.put("selectKeyword", selectKeyword);
		
		List<Festival> festivalList = festivalDAO.selectCollectedFestivalList(pageMap);
		
		Map<String, Object> festivalMap = new HashMap<String, Object>();
		festivalMap.put("festivalList", festivalList);
		festivalMap.put("pager", pager);
		
		return festivalMap;
	}

	@Override
	public List<Festival> getIndexMainFestival() {
		return festivalDAO.selectIndexMainFestival();
	}
	
	@Override
	public List<Festival> getIndexExpectFestival() {
		return festivalDAO.selectIndexExpectFestival();
	}
	
	@Override
	public void autoModifyFestivalState() {
		festivalDAO.autoUpdateFestivalState();
	}

	@Override
	public void modifyFestivalIndexShow1(List<Integer> checkedMainFestivalList) {
			festivalDAO.updateFestivalIndexShow1To0();
            festivalDAO.updateFestivalIndexShow1To1(checkedMainFestivalList);
	}

	@Override
	public void modifyFestivalIndexShow2(List<Integer> checkedExpectFestivalList) {
		festivalDAO.updateFestivalIndexShow2To0();
		festivalDAO.updateFestivalIndexShow2To1(checkedExpectFestivalList);
	}

	@Override
	public Map<String, Object> getMainFestivalList(int pageNum) {
		int totalBoard = festivalDAO.selectMainFestivalCount();
		int pageSize = 10;
		int blockSize = 10;
		Pager pager = new Pager(pageNum, totalBoard, pageSize, blockSize);
		
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("startRow", pager.getStartRow());
		pageMap.put("endRow", pager.getEndRow());
		
		List<Festival> festivalList = festivalDAO.selectMainFestivalList(pageMap);
		
		Map<String, Object> festivalMap = new HashMap<String, Object>();
		
		festivalMap.put("festivalList", festivalList);
		festivalMap.put("pager", pager);
		
		return festivalMap;
	}

	@Override
	public Map<String, Object> getExpectFestivalList(int pageNum) {
		int totalBoard = festivalDAO.selectExpectFestivalCount();
		int pageSize = 10;
		int blockSize = 10;
		Pager pager = new Pager(pageNum, totalBoard, pageSize, blockSize);
		
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("startRow", pager.getStartRow());
		pageMap.put("endRow", pager.getEndRow());
		
		List<Festival> festivalList = festivalDAO.selectExpectFestivalList(pageMap);
		
		Map<String, Object> festivalMap = new HashMap<String, Object>();
		
		festivalMap.put("festivalList", festivalList);
		festivalMap.put("pager", pager);
		
		return festivalMap;
	}
	
	@Override
	public List<Festival> getApprovedFestivalList(String accountId) {
		List<Festival> approvedList = festivalDAO.selectApprovedFestivalList(accountId);
		return approvedList;
	}

	@Override
	public List<Festival> getPendingFestivalList(String accountId) {
		List<Festival> pendingList = festivalDAO.selectPendingFestivalList(accountId);
		return pendingList;
	}

	@Override
	public List<Festival> getRejectedFestivalList(String accountId) {
		List<Festival> rejectedList = festivalDAO.selectRejectedFestivalList(accountId);
		return rejectedList;
	}
	
}
