package oop;

public class Student {
	
	private int num;
	private String name;
	private int kor, eng, tot;

	private static int total; // ← 이탤릭체

	public Student() {
		// TODO Auto-generated constructor stub
	}

	public Student(int num, String name, int kor, int eng) {
		super();
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		//tot = kor + eng;
		calcTot();
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

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
		calcTot();
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
		calcTot();
	}

	public int getTot() {
		return tot;
	}

	public void setTot(int tot) {
		this.tot = tot;
	}
	
	private void calcTot() {
		tot = kor + eng;
	}

	public void display() {
		System.out.println("[" + name + "]님의 성적>> ");
		System.out.println("국어 = " + kor + ", 영어 = " + eng + ", 총점 = " + tot);

	}

	public static int getTotal() {
		return total; // ← Student 생략 가능하다! 클래스 생략 가능, 왜?
	}

	public static void setTotal(int total) {
		Student.total = total;
	}
}
