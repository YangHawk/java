package xyz.itwill.util;

import java.util.List;

public class StudentManagerApp {
	public static void main(String[] args) {
		StudentManager manager = new StudentManager();

		manager.insertStudent(new Student(1000, "홍길동"));
		manager.insertStudent(new Student(2000, "임꺽정"));
		manager.insertStudent(new Student(3000, "전우치"));
		manager.insertStudent(new Student(4000, "일지매"));

		if (manager.insertStudent(new Student(5000, "장길산"))) {
			System.out.println("[메세지]학생정보를 성공적으로 삽입하였습니다.");
		} else {
			System.out.println("[메세지]이미 저장된 학번의 학생정보이므로 삽입되지 않았습니다.");
		}
		System.out.println("==============================================================");

		Student searchStudent = manager.selectStudent(2000);
		if (searchStudent != null) {
			System.out.println(searchStudent);
		} else {
			System.out.println("[메세지]해당 학번의 학생정보를 찾을 수 없습니다.");
		}
		System.out.println("==============================================================");

		searchStudent.setName("임걱정");

		if (manager.updateStudent(searchStudent)) {
			System.out.println("[메세지]학생정보를 성공적으로 변경하였습니다.");
		} else {
			System.out.println("[메세지]해당 학번의 학생정보를 찾을 수 없습니다.");
		}
		System.out.println("==============================================================");

		if (manager.deleteStudent(4000)) {
			System.out.println("[메세지]학생정보를 성공적으로 삭제 하였습니다.");
		} else {
			System.out.println("[메세지]해당 학번의 학생정보를 찾을 수 없습니다.");
		}
		System.out.println("==============================================================");

		List<Student> studentList = manager.selectStudentList();

		for (Student student : studentList) {
			System.out.println(student); // toString() = @Override 되어있다!
		}
		System.out.println("==============================================================");

	}
}
