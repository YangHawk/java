package xyz.itwill.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Festival;
import xyz.itwill.mapper.FestivalMapper;

@Repository
@RequiredArgsConstructor
public class FestivalDAOImpl implements FestivalDAO {
	private final SqlSession sqlSession;
	
	@Override
	public int insertFestival(Festival festival) {
		return sqlSession.getMapper(FestivalMapper.class).insertFestival(festival);
	}

	@Override
	public int updateFestival(Festival festival) {
		return sqlSession.getMapper(FestivalMapper.class).updateFestival(festival);
	}

	@Override
	public int updateFestivalState(Festival festival) {
		return sqlSession.getMapper(FestivalMapper.class).updateFestivalState(festival);
	}

	@Override
	public Festival selectFestival(int idx) {
		return sqlSession.getMapper(FestivalMapper.class).selectFestival(idx);
	}

	@Override
	public int selectFestivalCount(String selectKeyword) {
		return sqlSession.getMapper(FestivalMapper.class).selectFestivalCount(selectKeyword);
	}
	@Override
	public int selectAllFestivalCount(String selectKeyword) {
		return sqlSession.getMapper(FestivalMapper.class).selectAllFestivalCount(selectKeyword);
	}

	@Override
	public int selectOngoingFestivalCount(String selectKeyword) {
		return sqlSession.getMapper(FestivalMapper.class).selectOngoingFestivalCount(selectKeyword);
	}

	@Override
	public int selectUpcomingFestivalCount(String selectKeyword) {
		return sqlSession.getMapper(FestivalMapper.class).selectUpcomingFestivalCount(selectKeyword);
	}

	@Override
	public List<Festival> selectFestivalList(Map<String, Object> map) {
		return sqlSession.getMapper(FestivalMapper.class).selectFestivalList(map);
	}

	@Override
	public List<Festival> selectOngoingFestival() {
		return sqlSession.getMapper(FestivalMapper.class).selectOngoingFestival();
	}

	@Override
	public List<Festival> selectIndexMainFestival() {
		return sqlSession.getMapper(FestivalMapper.class).selectIndexMainFestival();
	}


	@Override
	public List<Festival> selectAllFestivalList(Map<String, Object> map) {
		return sqlSession.getMapper(FestivalMapper.class).selectAllFestivalList(map);
	}
	
	@Override
	public List<Festival> selectOngoingFestivalList(Map<String, Object> map) {
		return sqlSession.getMapper(FestivalMapper.class).selectOngoingFestivalList(map);
	}

	@Override
	public List<Festival> selectUpcomingFestivalList(Map<String, Object> map) {
		return sqlSession.getMapper(FestivalMapper.class).selectUpcomingFestivalList(map);
	}

	@Override
	public List<Festival> selectSponsorFestivalList(Map<String, Object> map) {
		return sqlSession.getMapper(FestivalMapper.class).selectSponsorFestivalList(map);
	}

	@Override
	public List<Festival> selectCollectedFestivalList(Map<String, Object> map) {
		return sqlSession.getMapper(FestivalMapper.class).selectCollectedFestivalList(map);
	}
	
	@Override
	public int autoUpdateFestivalState() {
		return sqlSession.getMapper(FestivalMapper.class).autoUpdateFestivalState();
	}

	@Override
	public int updateFestivalIndexShow1To0() {
		return sqlSession.getMapper(FestivalMapper.class).updateFestivalIndexShow1To0();
	}

	@Override
	public int updateFestivalIndexShow2To0() {
		return sqlSession.getMapper(FestivalMapper.class).updateFestivalIndexShow2To0();
	}

	@Override
	public int updateFestivalIndexShow1To1(List<Integer> checkedMainFestivalList) {
		return sqlSession.getMapper(FestivalMapper.class).updateFestivalIndexShow1To1(checkedMainFestivalList);
	}

	@Override
	public int updateFestivalIndexShow2To1(List<Integer> checkedExpectFestivalList) {
		return sqlSession.getMapper(FestivalMapper.class).updateFestivalIndexShow2To1(checkedExpectFestivalList);
	}
	
	@Override
	public List<Festival> selectMainFestivalList(Map<String, Object> map) {
		return sqlSession.getMapper(FestivalMapper.class).selectMainFestivalList(map);
	}

	@Override
	public int selectMainFestivalCount() {
		return sqlSession.getMapper(FestivalMapper.class).selectMainFestivalCount();
	}
	
	@Override
	public List<Festival> selectIndexExpectFestival() {
		return sqlSession.getMapper(FestivalMapper.class).selectIndexExpectFestival();
	}

	@Override
	public List<Festival> selectExpectFestivalList(Map<String, Object> map) {
		return sqlSession.getMapper(FestivalMapper.class).selectExpectFestivalList(map);
	}

	@Override
	public int selectExpectFestivalCount() {
		return sqlSession.getMapper(FestivalMapper.class).selectExpectFestivalCount();
	}

	

	@Override
	public List<Festival> selectApprovedFestivalList(String accountId) {
		return sqlSession.getMapper(FestivalMapper.class).selectApprovedFestivalList(accountId);
	}

	@Override
	public List<Festival> selectPendingFestivalList(String accountId) {
		return sqlSession.getMapper(FestivalMapper.class).selectPendingFestivalList(accountId);
	}

	@Override
	public List<Festival> selectRejectedFestivalList(String accountId) {
		return sqlSession.getMapper(FestivalMapper.class).selectRejectedFestivalList(accountId);
	}

	
}
