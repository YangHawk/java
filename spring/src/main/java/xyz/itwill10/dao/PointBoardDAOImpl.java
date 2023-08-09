package xyz.itwill10.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import xyz.itwill10.dto.PointBoard;
import xyz.itwill10.dto.PointUser;
import xyz.itwill10.mapper.PointBoardMapper;
import xyz.itwill10.mapper.PointUserMapper;

@Repository
@RequiredArgsConstructor
public class PointBoardDAOImpl implements PointBoardDAO {
	private final SqlSession sqlSession;

	@Override
	public int insertPointBoard(PointBoard pointBoard) {
		return sqlSession.getMapper(PointBoardMapper.class).insertPointBoard(pointBoard);
	}

	@Override
	public int deletePointBoard(int idx) {
		return sqlSession.getMapper(PointBoardMapper.class).deletePointBoard(idx);
	}

	@Override
	public PointBoard selectPointBoard(int idx) {
		return sqlSession.getMapper(PointBoardMapper.class).selectPointBoard(idx);
	}

	@Override
	public List<PointBoard> selectPointBoardList() {
		return sqlSession.getMapper(PointBoardMapper.class).selectPointBoardList();
	}

}
