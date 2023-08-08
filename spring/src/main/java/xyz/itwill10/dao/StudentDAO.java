package xyz.itwill10.dao;

import java.util.List;

import xyz.itwill10.dto.Student;

public interface StudentDAO {
	int insertStuedent(Student student);

	List<Student> selectStudentList();
}
