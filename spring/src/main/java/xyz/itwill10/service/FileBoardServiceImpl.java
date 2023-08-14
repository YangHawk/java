package xyz.itwill10.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import xyz.itwill10.dao.FileBoardDAO;
import xyz.itwill10.dto.FileBoard;

@Service
@RequiredArgsConstructor
public class FileBoardServiceImpl implements FileBoardService {
	private final FileBoardDAO fileBoardDAO;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void addFileBoard(FileBoard fileBoard) {
		fileBoardDAO.insertFileBoard(fileBoard);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void removeFileBoard(int idx) {
		/*
		if (fileBoardDAO.selectFileBoard(idx) == null) {
			throw new Exception();
		}
		 */
		fileBoardDAO.deleteFileBoard(idx);
	}

	@Override
	public FileBoard getFileBoard(int idx) {
		return fileBoardDAO.selectFileBoard(idx);
	}

	// 매개 변수로 요청 페이지 번호를 전달받아 게시글 목록과 페이지 번호 관련 객체를 Map 객체의 엔트리로 추가하여 반환하는 메소드
	@Override
	public Map<String, Object> getFileBoardList(int pageNum) {
		// FILEBOARD 테이블에 저장된 모든 게시글의 갯수를 검색하여 반환하는 DAO 클래스의 메소드
		int totalBoard = fileBoardDAO.selectFileBoardCount();
		// 하나의 페이지에 출력될 게시글의 갯수를 저장
		int pageSize = 5;
		// 하나의 블록에 출력될 페이지의 갯수를 저장
		int blockSize = 5;

		pageNum = 2;

		return null;
	}
}
