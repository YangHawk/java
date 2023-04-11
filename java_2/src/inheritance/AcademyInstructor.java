package inheritance;

public class AcademyInstructor extends AcademyPerson {
	private String subject;

	public AcademyInstructor() {
		// TODO Auto-generated constructor stub
	}

	public AcademyInstructor(int num, String name, String subject) {
		super(num, name);
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public void display() {
		System.out.println("학생번호= " + getNum());
		System.out.println("학생이름= " + getName());
		System.out.println("강의과목= " + subject);

	}

}
