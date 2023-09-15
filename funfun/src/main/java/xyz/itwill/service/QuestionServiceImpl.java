package xyz.itwill.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dao.QuestionDAO;
import xyz.itwill.dto.Question;
import xyz.itwill.util.Pager;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
	private final QuestionDAO questionDAO;

	@Override
	public void addQuestion(Question question) {
		questionDAO.insertQuestion(question);
	}

	@Override
	public void modifyQuestionByUser(Question question) {
		questionDAO.updateQuestionByUser(question);
	}

	@Override
	public void modifyQuestionByAdmin(Question question) {
		questionDAO.updateQuestionByAdmin(question);
	}

	@Override
	public void modifyQuestionCount(int idx) {
		questionDAO.updateQuestionCount(idx);
	}

	@Override
	public void removeQuestion(int idx) {
		questionDAO.deleteQuestion(idx);
	}

	@Override
	public Question getQuestion(int idx) {
		return questionDAO.selectQuestion(idx);
	}

	@Override
	public Map<String, Object> getQuestionList(int pageNum, int pageSize, String selectKeyword) {
		int totalBoard = questionDAO.selectQuestionCount(selectKeyword);
		int blockSize = 10;
		Pager pager = new Pager(pageNum, totalBoard, pageSize, blockSize);

		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("startRow", pager.getStartRow());
		pageMap.put("endRow", pager.getEndRow());
		pageMap.put("selectKeyword", selectKeyword);

		List<Question> questionList = questionDAO.selectQuestionList(pageMap);

		Map<String, Object> questionMap = new HashMap<String, Object>();
		questionMap.put("questionList", questionList);
		questionMap.put("pager", pager);

		return questionMap;
	}

	@Override
	public Map<String, Object> getMyQuestionList(int pageNum, String accountId) {
		int totalBoard = questionDAO.selectQuestionCount(accountId);
		int blockSize = 5;
		int pageSize = 10;
		Pager pager = new Pager(pageNum, totalBoard, pageSize, blockSize);

		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("startRow", pager.getStartRow());
		pageMap.put("endRow", pager.getEndRow());
		pageMap.put("accountId", accountId);

		List<Question> questionList = questionDAO.selectMyQuestionList(pageMap);

		Map<String, Object> questionMap = new HashMap<String, Object>();
		questionMap.put("questionList", questionList);
		questionMap.put("pager", pager);

		return questionMap;
	}

	@Override
	public void removeQuestionByAdmin(int idx) {
		questionDAO.deleteQuestionByAdmin(idx);
	}

	@Override
	public void addSecretQuestion(Question question) {
		questionDAO.insertSecretQuestion(question);

	}

	@Override
	public Question getPreNextidx(int idx) {
		return questionDAO.selectPreNextidx(idx);
	}
}
