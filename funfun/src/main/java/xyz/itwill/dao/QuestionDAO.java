package xyz.itwill.dao;

import java.util.List;
import java.util.Map;

import xyz.itwill.dto.Question;

public interface QuestionDAO {
	// 질문글 추가
	int insertQuestion(Question question);

	int insertSecretQuestion(Question question);

	// 질문글 수정-회원
	int updateQuestionByUser(Question question);

	// 댓글 수정-관리자
	int updateQuestionByAdmin(Question question);

	// 질문글 삭제
	int deleteQuestion(int idx);

	int deleteQuestionByAdmin(int idx);

	// 질문글 조회수
	int updateQuestionCount(int idx);

	// 특정 질문글 상세 조회
	Question selectQuestion(int idx);

	// 전체 질문글 개수 조회
	int selectQuestionCount(String selectKeyword);

	// 질문글 목록 조회
	List<Question> selectQuestionList(Map<String, Object> map);

	int selectMyQuestionCount(String accountId);

	List<Question> selectMyQuestionList(Map<String, Object> map);

	// 다음글 이전글 추가
	Question selectPreNextidx(int idx);
}
