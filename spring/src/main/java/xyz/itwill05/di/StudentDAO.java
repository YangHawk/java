package xyz.itwill05.di;

import java.util.List;

//학생 정보를 처리하는 DAO 클래스가 반드시 상속받아야 하는 인터페이스
//▶ 객체 간 결합도를 낮춰 유지 보수의 효율성 ↑
public interface StudentDAO {
	int insertStudent(Student student);

	int updateStudent(Student student);

	int deleteStudent(int num);

	Student selectStudent(int num);

	List<Student> selectStudentList();
}
