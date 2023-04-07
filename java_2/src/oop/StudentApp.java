package oop;

public class StudentApp {
	public static void main(String[] args) {
		Student student1 = new Student(1000, "홍길동", 90, 90);
		Student student2 = new Student(2000, "임꺽정", 94, 98);
		Student student3 = new Student(3000, "전우치", 91, 80);
		Student student4 = new Student(4000, "일지매", 76, 82);
		Student student5 = new Student(5000, "장길산", 84, 86);
		/*
		student1.calcTot();
		student2.calcTot();
		student3.calcTot();
		student4.calcTot();
		student5.calcTot();
		 */
		student1.display();
		student2.display();
		student3.display();
		student4.display();
		student5.display();
		System.out.println("=========================================================================");
		student1.setKor(100);
		// student1.calcTot();
		student1.display();
		System.out.println("=========================================================================");
		Student[] students = new Student[5];
		students[0] = new Student(1000, "홍길동", 90, 90);
		students[1] = new Student(2000, "임꺽정", 94, 98);
		students[2] = new Student(3000, "전우치", 91, 80);
		students[3] = new Student(4000, "일지매", 76, 82);
		students[4] = new Student(5000, "장길산", 84, 86);
		for (int i = 0; i < students.length; i++) {
			if (students[i] != null) {
				students[i].display();
			}
		}
		System.out.println("=========================================================================");
		Student[] students2 = { 
				new Student(1000, "홍길동", 90, 90), 
				new Student(2000, "임꺽정", 94, 98),
				new Student(3000, "전우치", 91, 80), 
				new Student(4000, "일지매", 76, 82), 
				new Student(5000, "장길산", 84, 86) };
		//int total=0;
		for (Student student : students2) {
			student.display();
			//Student.total += student.getTot(); //객체 지향 프로그래밍에 맞지 않다!
			
			Student.setTotal(Student.getTotal() + student.getTot());
		}
		System.out.println("=========================================================================");
		System.out.println("총 합계 = " + Student.getTotal());

	}
}
