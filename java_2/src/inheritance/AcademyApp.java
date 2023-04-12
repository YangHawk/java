package inheritance;

public class AcademyApp {
	public static void main(String[] args) {
		/*
		 * AcademyStudent[] students = new AcademyStudent[300];
		 * AcademyInstructor[] instroctors = new AcademyInstructor[50];
		 * AcademyStaff[] staffs = new AcademyStaff[100];
		 */

		AcademyPerson[] persons = new AcademyPerson[5];

		persons[0] = new AcademyStudent(1000, "홍길동", "웹개발자 과정");
		persons[1] = new AcademyInstructor(2000, "임꺽정", "Java 과목");
		persons[2] = new AcademyStaff(3000, "전우치", "운영관리");
		persons[3] = new AcademyStudent(4000, "일지매", "웹디자인 과정");
		persons[4] = new AcademyStaff(5000, "장길산", "경영회계");

		for (AcademyPerson person : persons) {
			if (person instanceof AcademyStudent)

			{
				System.out.println(((AcademyStudent) person).getCourse() + "의 학생정보 >> ");
			} else if (person instanceof AcademyInstructor) {
				System.out.println(((AcademyInstructor) person).getSubject() + "의 강사정보 >> ");
			} else if (person instanceof AcademyStaff) {
				System.out.println(((AcademyStaff) person).getDepart() + "의 직원정보 >> ");
			}

			person.display();
			System.out.println("================================================");
		}

	}

}
