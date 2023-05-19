package xyz.itwill.team05;

import java.util.List;

import xyz.itwill.student.StudentDTO;

//1. 학생이 로그인을 하면 자동으로 ALOG 테이블에서 학생의 상태를 변경하는 메소드
//2. 학생이 본인 정보  ALOG / STATUS 를 열람(SELECT)할 수 있게 검색만
//3. 학생이 STUDENT 테이블에서 본인 정보만 검색

public interface StudentDAO {

// student 객체(STUDENTDTO 객체)를 불러와 거기서 학번을 전달받아 ALOG 에 추가
int insertALog(StudentDTO student);

// studetn 객체 퇴실!
int updateALog(StudentDTO student);

// 내 정보만 보기
List<StudentDTO> showALog(StudentDTO student);

}
