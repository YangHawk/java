package oop;

public class Singleton {
	//private static Singleton _instance = new Singleton();
	private static Singleton _instance;

	private Singleton() {
		// TODO Auto-generated constructor stub
	}

	static {
		_instance = new Singleton();
	} //왜 위처럼 안하고 따로 static 블록 안에 이걸 해주는가? 이것은 초기화 블록이다.

	public static Singleton getInstance() {
		return _instance;
	} // ← Setter 메소드 따위는 없다! 객체를 반환하는 정적 메소드 이 메소드를
		// 호출하여 참조변수에 반환받은 객체를 저장하여 사용하는 것!

	public void display() {
		System.out.println("Singleton 클래스의 display() 메소드 호출");
	}

}
