package inheritance;

public class AcademyPerson {
	private int num;
	private String name;

	public AcademyPerson() {
		// TODO Auto-generated constructor stub
	}

	public AcademyPerson(int num, String name) {
		super();
		this.num = num;
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void display() {
		System.out.println("번호 = " + num);
		System.out.println("이름 = " + name);
	}
}
