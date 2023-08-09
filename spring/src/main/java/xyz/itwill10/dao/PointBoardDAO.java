package xyz.itwill10.dao;

import java.util.List;

import xyz.itwill10.dto.PointBoard;

public interface PointBoardDAO {
	int insertPointBoard(PointBoard pointBoard);

	int deletePointBoard(int idx);

	PointBoard selectPointBoard(int idx);

	List<PointBoard> selectPointBoardList();

}
