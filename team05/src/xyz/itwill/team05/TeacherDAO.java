package xyz.itwill.team05;

import java.util.List;

public interface TeacherDAO {
	
	// 학생정보를 전달받아 STUDENT 테이블에 삽입하고 삽입행의 갯수를 반환하는 메소드
	int insertStudent(StudentDTO student);

	// 학생정보를 전달받아 STUDENT 테이블에 저장된 학생정보를 변경하고 변경행의 갯수를 반환하는 메소드
	int updateStudent(StudentDTO student);

	// 학번을 전달받아 STUDENT 테이블에 저장된 학생정보를 삭제하고 삭제행의 갯수를 반환하는 메소드
	int deleteStudent(int no);

	// 학번을 전달받아 STUDENT 테이블에 저장된 해당 학번의 학생정보를 검색하여 반환하는 메소드
	// => 단일행은 값 또는 DTO 객체 반환
	StudentDTO selectStudent(int no);

	// 이름을 전달받아 STUDENT 테이블에 저장된 해당 이름의 학생정보를 검색하여 반환하는 메소드
	// => 다중행은 List 객체 반환
	List<StudentDTO> selectNameStudentList(String name);

	// STUDENT 테이블에 저장된 모든 학생정보를 검색하여 반환하는 메소드
	List<StudentDTO> selectAllStudentList();
	
	// 학생 이름을 입력하여 입력된 학생의 출결 현황을 검색하여 반환하는 메소드
	List<ALogDTO> selectNameAlogList(String name);
	
	// 날짜를 입력하여 입력된 날짜의 모든 학생의 출결 현황을 검색하여 반환하는 메소드
	List<ALogDTO> selectDateALogList(String logInTime);
}