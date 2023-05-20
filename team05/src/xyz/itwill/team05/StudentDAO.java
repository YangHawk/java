package xyz.itwill.team05;

import java.time.LocalDate;
import java.util.List;

public interface StudentDAO {

	// 학생 정보를 전달받아 입실 처리하는 메소드
	int insertALog(StudentDTO student);

	// 학생 정보를 전달받아 퇴실 처리하는 메소드
	int updateALog(StudentDTO student);

	// 학생 정보를 전달받아 출결 기록을 확인하는 메소드
	List<ALogDTO> showALog(StudentDTO student);

	// 입실 관련 오류를 방지하는 메소드
	boolean checkIn(StudentDTO student, LocalDate currentDate);

	// 퇴실 관련 오류를 방지하는 메소드
	boolean checkOut(StudentDTO student, LocalDate currentDate);

	// 학생 정보를 전달받아 정상 상태로 처리하는 메소드
	int updateStatusNormal(StudentDTO student);

	// 학생 정보를 전달받아 지각 상태로 처리하는 메소드
	int updateStatusLate(StudentDTO student);

	// 학생 정보를 전달받아 조퇴 상태로 처리하는 메소드
	int updateStatusEarlyLeave(StudentDTO student);

	// 학생 정보를 전달받아 결석 상태로 처리하는 메소드
	int updateStatusAbsent(StudentDTO student);

	// 마지막으로 출석한 날짜를 전달받아, 결석한 날짜들의 행을 삽입하는 메소드
	int insertStatusAbsent(StudentDTO student);

}
