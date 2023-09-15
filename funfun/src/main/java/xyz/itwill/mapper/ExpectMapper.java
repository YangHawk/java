package xyz.itwill.mapper;

import java.util.List;

import xyz.itwill.dto.Expect;

public interface ExpectMapper {
	//사용자가 기대평 작성시 사용될 메소드 
	int insertExpect(Expect expect);
	
	//사용자가 게시글을 삭제하거나 값을 수정할 때 사용될 메소드
	int updateExpect(Expect expect);
	
	//게시글 하나를 검색할 때 사용할 메소드
	Expect selectExpect(int idx);
	
	//로그인 회원이 기대평을 작성했는지 검색하는 메소드
	Expect selectExpectAccountId(String accountId, int festivalIdx);
	
	//해당 영화제의 기대평들을 검색할 때 사용할 메소드
	List<Expect> selectExpectList(int festivalIdx);
	
	//메인화면에 기대평이 3개까지 출력되도록 하기 위한 메소드
	List<Expect> selectRandomExpectList();
	
	//추가 - 0910
	int deleteExpect(int idx);
	
	//추가 - 0911
	Double selectStarAvg(int festivalIdx);
}
