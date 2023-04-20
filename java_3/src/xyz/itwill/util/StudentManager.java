package xyz.itwill.util;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {
	private List<Student> studentList = new ArrayList<Student>();

	private int getStudentIndex(int num) {
		int index = -1;

		for (int i = 0; i < studentList.size(); i++) {
			if (studentList.get(i).getNum() == num) {
				index = i;
				break;
			}
		}
		return index;
	}

	public boolean insertStudent(Student student) {

		if (getStudentIndex(student.getNum()) != -1) {
			return false;
		}
		/*
		for (Student stu : studentList) {
			if (stu.getNum() == student.getNum()) {
				return false;
			}
		}
		*/
		studentList.add(student);
		return true;
	}

	public boolean updateStudent(Student student) {
		int index = getStudentIndex(student.getNum());
		if (index == -1) {
			return false;
		}
		studentList.set(index, student);
		return true;
	}

	public boolean deleteStudent(int num) {
		int index = getStudentIndex(num);
		if (index == -1) {
			return false;
		}
		studentList.remove(index);
		return true;
	}

	public Student selectStudent(int num) {
		int index = getStudentIndex(num);
		if (index == -1) {
			return null;
		}
		return studentList.get(index);
	}

	public List<Student> selectStudentList() {
		return studentList;
	}

}
