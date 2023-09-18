package xyz.itwill.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Question;
import xyz.itwill.mapper.QuestionMapper;

@Repository
@RequiredArgsConstructor
public class QuestionDAOImpl implements QuestionDAO {
	private final SqlSession sqlSession;

	@Override
	public int insertQuestion(Question question) {
		return sqlSession.getMapper(QuestionMapper.class).insertQuestion(question);
	}

	@Override
	public int updateQuestionByUser(Question question) {
		return sqlSession.getMapper(QuestionMapper.class).updateQuestionByUser(question);
	}

	@Override
	public int updateQuestionByAdmin(Question question) {
		return sqlSession.getMapper(QuestionMapper.class).updateQuestionByAdmin(question);
	}

	@Override
	public int updateQuestionCount(int idx) {
		return sqlSession.getMapper(QuestionMapper.class).updateQuestionCount(idx);
	}

	@Override
	public int deleteQuestion(int idx) {
		return sqlSession.getMapper(QuestionMapper.class).deleteQuestion(idx);
	}

	@Override
	public Question selectQuestion(int idx) {
		return sqlSession.getMapper(QuestionMapper.class).selectQuestion(idx);
	}

	@Override
	public int selectQuestionCount(String selectKeyword) {
		return sqlSession.getMapper(QuestionMapper.class).selectQuestionCount(selectKeyword);
	}

	@Override
	public List<Question> selectQuestionList(Map<String, Object> map) {
		return sqlSession.getMapper(QuestionMapper.class).selectQuestionList(map);
	}

	@Override
	public int selectMyQuestionCount(String accountId) {
		return sqlSession.getMapper(QuestionMapper.class).selectMyQuestionCount(accountId);
	}

	@Override
	public List<Question> selectMyQuestionList(Map<String, Object> map) {
		return sqlSession.getMapper(QuestionMapper.class).selectMyQuestionList(map);
	}

	@Override
	public int deleteQuestionByAdmin(int idx) {
		return sqlSession.getMapper(QuestionMapper.class).deleteQuestionByAdmin(idx);
	}

	@Override
	public int insertSecretQuestion(Question question) {
		return sqlSession.getMapper(QuestionMapper.class).insertSecretQuestion(question);
	}

	@Override
	public Question selectPreNextidx(int idx) {
		return sqlSession.getMapper(QuestionMapper.class).selectPreNextidx(idx);
	}
}
