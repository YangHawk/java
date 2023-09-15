package xyz.itwill.service;

import java.util.Map;

import xyz.itwill.dto.Question;

public interface QuestionService {
	void addQuestion(Question question);
	void addSecretQuestion(Question question);

	void modifyQuestionByUser(Question question);

	void modifyQuestionByAdmin(Question question);

	void modifyQuestionCount(int idx);

	void removeQuestion(int idx);
	void removeQuestionByAdmin(int idx);
	
	Question getQuestion(int idx);

	Map<String, Object> getQuestionList(int pageNum, int pageSize, String selectKeyword);
	
	Map<String, Object> getMyQuestionList(int pageNum, String accountId);
	Question getPreNextidx(int idx);
}
