package xyz.itwill.team05;

import java.time.LocalDate;
import java.util.List;

//1. 학생이 로그인을 하면 자동으로 ALOG 테이블에서 학생의 상태를 변경하는 메소드
//2. 학생이 본인 정보  ALOG / STATUS 를 열람(SELECT)할 수 있게 검색만
//3. 학생이 STUDENT 테이블에서 본인 정보만 검색

public interface StudentDAO {

// student 객체(STUDENTDTO 객체)를 불러와 거기서 학번을 전달받아 ALOG 에 추가
	int insertALog(StudentDTO student);

// studetn 객체 퇴실!
	int updateALog(StudentDTO student);

// 내 정보만 보기
	List<ALogDTO> showALog(StudentDTO student);

	// student 버튼 눌렀을 때 입실 또는 퇴실을 못하게!
	// 만약 sno - 1이고 날짜가 05/19일 때 행이 있다면, 입실 버튼을 눌렀을 때 행이 추가가 못되게!
	// 1. 행을 검색해야 함
	// select * from alog where sno = ? and and trunc(logintime, 'DD') =
	// trunc(sysdate)

	boolean checkIn(StudentDTO student, LocalDate currentDate);

	boolean checkOut(StudentDTO student, LocalDate currentDate);

	// 학생이 퇴실을 했을 때 그 학생의 alog 행의 status 열이 update 되는 메소드
	int updateStatus(StudentDTO student);

}
