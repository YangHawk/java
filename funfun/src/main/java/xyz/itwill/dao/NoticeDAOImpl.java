package xyz.itwill.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Notice;
import xyz.itwill.mapper.NoticeMapper;

@Repository
@RequiredArgsConstructor
public class NoticeDAOImpl implements NoticeDAO{
	private final SqlSession sqlSession;

	@Override
	public int insertNotice(Notice notice) {
		return sqlSession.getMapper(NoticeMapper.class).insertNotice(notice);
	}

	@Override
	public int updateNotice(Notice notice) {
		return sqlSession.getMapper(NoticeMapper.class).updateNotice(notice);
	}

	@Override
	public int deleteNotice(int idx) {
		return sqlSession.getMapper(NoticeMapper.class).deleteNotice(idx);
	}
	
	@Override
	public int updateNoticeCount(int idx) {
		return sqlSession.getMapper(NoticeMapper.class).updateNoticeCount(idx);
	}

	@Override
	public Notice selectNotice(int idx) {
		return sqlSession.getMapper(NoticeMapper.class).selectNotice(idx);
	}

	@Override
	public List<Notice> selectNoticeList(Map<String, Object> map) {
		return sqlSession.getMapper(NoticeMapper.class).selectNoticeList(map);
	}

	@Override
	public int selectNoticeCount(String selectKeyword) {
		return sqlSession.getMapper(NoticeMapper.class).selectNoticeCount(selectKeyword);
	}

	@Override
	   public Notice selectPreNextNoticeidx(int idx) {
	      return sqlSession.getMapper(NoticeMapper.class).selectPreNextNoticeidx(idx);
	   }
}
