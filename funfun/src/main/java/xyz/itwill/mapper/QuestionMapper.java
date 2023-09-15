package xyz.itwill.mapper;

import java.util.List;
import java.util.Map;

import xyz.itwill.dto.Question;

public interface QuestionMapper {
	int insertQuestion(Question question);
	int insertSecretQuestion(Question question);

	int updateQuestionByUser(Question question);
	
	int updateQuestionByAdmin(Question question);
	
	int updateQuestionCount(int idx);

	int deleteQuestion(int idx);
	int deleteQuestionByAdmin(int idx);

	Question selectQuestion(int idx);

	int selectQuestionCount(String selectKeyword);

	List<Question> selectQuestionList(Map<String, Object> map);
	
	int selectMyQuestionCount(String accountId);
	
	List<Question> selectMyQuestionList(Map<String, Object> map);
	
	Question selectPreNextidx(int idx);
}
