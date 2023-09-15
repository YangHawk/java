package xyz.itwill.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Expect;
import xyz.itwill.mapper.ExpectMapper;

@Repository
@RequiredArgsConstructor
public class ExpectDAOImpl implements ExpectDAO {
	private final SqlSession sqlSession;
	
	@Override
	public int insertExpect(Expect expect) {
		return sqlSession.getMapper(ExpectMapper.class).insertExpect(expect);
	}

	@Override
	public int updateExpect(Expect expect) {
		return sqlSession.getMapper(ExpectMapper.class).updateExpect(expect);
	}

	@Override
	public Expect selectExpect(int idx) {
		return sqlSession.getMapper(ExpectMapper.class).selectExpect(idx);
	}
	//추가
	@Override
	public Expect selectExpectAccountId(String accountId, int festivalIdx) {
		return sqlSession.getMapper(ExpectMapper.class).selectExpectAccountId(accountId, festivalIdx);
	}

	@Override
	public List<Expect> selectExpectList(int festivalIdx) {
		return sqlSession.getMapper(ExpectMapper.class).selectExpectList(festivalIdx);
	}

	@Override
	public List<Expect> selectRandomExpectList() {
		return sqlSession.getMapper(ExpectMapper.class).selectRandomExpectList();
	}

	@Override
	public int deleteExpect(int idx) {
		return sqlSession.getMapper(ExpectMapper.class).deleteExpect(idx);
	}

	@Override
	public Double selectStarAvg(int festivalIdx) {
		Double starAvg = sqlSession.getMapper(ExpectMapper.class).selectStarAvg(festivalIdx);
		if(starAvg == null) {
			starAvg = 0.0;
		}
		return starAvg;
	}


}
