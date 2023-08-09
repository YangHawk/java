package xyz.itwill10.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import xyz.itwill10.dao.PointBoardDAO;
import xyz.itwill10.dao.PointUserDAO;
import xyz.itwill10.dto.PointBoard;
import xyz.itwill10.dto.PointUser;

@Service
@RequiredArgsConstructor
public class PointBoardServiceImpl implements PointBoardService {
	private final PointUserDAO pointUserDAO;
	private final PointBoardDAO pointBoardDAO;

	// 매개 변수로 게시글을 전달받아 POINTBOARD 테이블에 저장된 게시글을 삽입하고 게시글 작성자에 대한 회원 정보를 POINTUSER 테이블에서 검색하여 반환하는 메소드
	// ▶ POINTUSER 테이블에 저장된 회원 정보 중 게시글 작성자에 대한 회원 정보의 포인트(POINT 컬럼값)를 증가되도록 변경 처리
	// @Transactional: TransactionManager 객체에 의하여 트랜잭션 처리 기능을 제공받기 위한 어노테이션
	// rollbackFor 속성: 예외 클래스(Class 객체)를 속성값으로 설정 - 예외가 발생되면 롤백 처리
	@Transactional(rollbackFor = Exception.class)
	@Override
	public PointUser addPointBoard(PointBoard board) throws Exception {
		pointBoardDAO.insertPointBoard(board); // 1. 게시글을 삽입
		
		// 게시글 작성자에 대한 회원 정보가없는 경우 인위적 예외 발생
		// ▶ 예외가 발생된 경우 하단에 작성된 명령은 실행되지 않고 메소드가 강제 종료
		if (pointUserDAO.selectPointUser(board.getWriter()) == null) {
			throw new Exception("게시글 작성자가 존재하지 않습니다.");
		}
		
		pointUserDAO.updatePlusPointUser(board.getWriter()); // 2. 게시글 작성자를 전달하여 회원 정보 중 포인트를 증가
		
		return pointUserDAO.selectPointUser(board.getWriter()); // 3. 게시글 작성자의 회원 정보를 검색하여 반환
	}

	// 매개 변수로 게시글을 전달받아 POINTBOARD 테이블에 저장된 게시글을 삭제하고 게시글 작성자에 대한 회원 정보를 POINTUSER 테이블에서 검색하여 반환하는 메소드
	// ▶ POINTUSER 테이블에 저장된 회원 정보 중 게시글 작성자에 대한 회원 정보의 포인트(POINT 컬럼값)를 감소되도록 변경 처리
	@Transactional(rollbackFor = Exception.class)
	@Override
	public PointUser removePointBoard(int idx) throws Exception {
		PointBoard board = pointBoardDAO.selectPointBoard(idx); // 1. 게시글 검색
		
		// 글 번호에 대한 게시글이 검색되지 않은 경우 인위적 예외 발생
		if (board == null) {
			throw new Exception("게시글이 존재하지 않습니다..");
		}
		
		pointBoardDAO.deletePointBoard(idx); // 2. 게시글 삭제
		
		// 글 번호에 대한 게시글이 검색되지 않은 경우 인위적 예외 발생
		if (pointUserDAO.selectPointUser(board.getWriter()) == null) {
			throw new Exception("게시글 작성자가 존재하지 않습니다.");
		}
		
		pointUserDAO.updateMinusPointUser(board.getWriter()); // 3. 게시글 작성자를 전달하여 회원 정보 중 포인트를 감소

		return pointUserDAO.selectPointUser(board.getWriter()); // 4. 게시글 작성자의 회원 정보를 검색하여 반환
	}

	// POINTBOARD 테이블에 저장된 모든 게시글을 검색하여 반환하는 메소드
	@Override
	public List<PointBoard> getPointBoardList() {
		return pointBoardDAO.selectPointBoardList();
	}
}
