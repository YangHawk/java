package xyz.itwill.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dao.ExpectDAO;
import xyz.itwill.dto.Expect;
import xyz.itwill.dto.Festival;
import xyz.itwill.exception.FestivalinfoNotFoundException;

@Service
@RequiredArgsConstructor
public class ExpectServiceImpl implements ExpectService {
	private final ExpectDAO expectDAO;
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void addExpect(Expect expect) {
		expectDAO.insertExpect(expect);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void modifyExpect(Expect expect) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Expect getExpect(int idx) {
		return expectDAO.selectExpect(idx);
	}
	
	@Override
	public Expect getExpectAccountId(String accountId, int festivalIdx) {
		return expectDAO.selectExpectAccountId(accountId, festivalIdx);
	}

	@Override
	public List<Expect> getExpectList(int festivalIdx) {
		return expectDAO.selectExpectList(festivalIdx);
	}

	@Override
	public List<Expect> getRandomExpectList() {
		return expectDAO.selectRandomExpectList();
	}

	@Override
	public void removeExpect(int idx) {
		expectDAO.deleteExpect(idx);
	}
	
	@Override
	public Double getStarAvg(int festivalIdx){
		return expectDAO.selectStarAvg(festivalIdx); 
	}
}
