package oop;

public class Singleton {
	private static Singleton _instance;

	private Singleton() {
		// TODO Auto-generated constructor stub
	}

	static {
		_instance = new Singleton();
	}

	public static Singleton getInstance() {
		return _instance;
	}

	public void display() {
		System.out.println("Singleton 클래스의 display() 메소드 호출");
	}

}
