package xyz.itwill.team05;

import java.time.LocalDate;
import java.util.List;

public interface StudentDAO {

	// 로그인한 학생 정보를 전달받아 ALOG 테이블에 행을 삽입하고 입실 처리하는 메소드
	int insertALog(StudentDTO student);

	// 로그인한 학생 정보를 전달받아 ALOG 테이블에 행을 변경하고 퇴실 처리하는 메소드
	int updateALog(StudentDTO student);

	// 로그인한 학생 정보를 전달받아 ALOG 테이블에서 해당 학생의 행을 반환받는 메소드
	List<ALogDTO> showALog(StudentDTO student);

	// 로그인한 학생 정보와 입실한 날짜를 전달받아 재입실을 방지하는 메소드
	boolean checkIn(StudentDTO student, LocalDate currentDate);

	// 로그인한 학생 정보와 입실한 날짜를 전달받아 재퇴실을 방지하는 메소드
	boolean checkOut(StudentDTO student, LocalDate currentDate);

	// 로그인한 학생 정보의 입실 퇴실 시간을 전달받아 ALOG 테이블에 행을 변경(출결 상태를 정상 처리)하는 메소드
	// 입실 시간: 9시 30분 이전 + 퇴실 시간: 6시 30분 이후
	int updateStatusNormal(StudentDTO student);

	// 로그인한 학생 정보의 입실 퇴실 시간을 전달받아 ALOG 테이블에 행을 변경(출결 상태를 지각 처리)하는 메소드
	// 입실 시간: 9시 30분 이후 + 퇴실 시간: 6시 30분 이후
	int updateStatusLate(StudentDTO student);

	// 로그인한 학생 정보의 입실 퇴실 시간을 전달받아 ALOG 테이블에 행을 변경(출결 상태를 조퇴 처리)하는 메소드
	// 입실 시간: 9시 30분 이전 + 퇴실 시간: 6시 30분 이전
	int updateStatusEarlyLeave(StudentDTO student);

	// 로그인한 학생 정보의 입실 퇴실 시간을 전달받아 ALOG 테이블에 행을 변경(출결 상태를 결석 처리)하는 메소드
	// 입실 시간: 9시 30분 이후 + 퇴실 시간: 6시 30분 이전
	int updateStatusAbsent(StudentDTO student);

	// 로그인한 학생 정보의 입실 퇴실 시간을 전달받아 ALOG 테이블에 행을 변경(출결 상태를 정상으로 처리)하는 메소드
	// 입실후에 퇴실을 하지않음
	int updateStatusAbsent2(StudentDTO student);

	// 로그인한 학생이 정보를 전달받고 해당하는 학생이 마지막으로 출석한 날짜와 현재 입실한 날짜의 기간을 검색하여 존재하지 않다면 ALOG
	// 테이블에 행을 삽입하고 행들의 출결 상태를 결석으로 처리하는 메소드
	// 특정 날짜에 행이 없다면 결석 처리된 행을 삽입
	int insertStatusAbsent(StudentDTO student);

}
