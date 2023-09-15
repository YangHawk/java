package xyz.itwill.service;

import java.util.List;

import xyz.itwill.dto.Expect;

public interface ExpectService {
	void addExpect(Expect expect);
			
	void modifyExpect(Expect expect);
			
	Expect getExpect(int idx);
	
	Expect getExpectAccountId(String accountId, int festivalIdx);
			
	List<Expect> getExpectList(int festivalIdx);
	
	List<Expect> getRandomExpectList();
	
	void removeExpect(int idx);
	
	Double getStarAvg(int festivalIdx);
}
