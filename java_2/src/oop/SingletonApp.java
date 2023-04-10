package oop;

public class SingletonApp {
	public static void main(String[] args) {
		// Singleton singleton1 = new Singleton(); → 생성자가 은닉화 되어 있어 에러
		// Singleton singleton2 = new Singleton(); → singleton1 과 singleton2는 서로 다른
		// 객체이다. 즉, 굳이 여러 개의 객체를 만들 필요가 없다.

		Singleton singleton1 = Singleton.getInstance();
		Singleton singleton2 = Singleton.getInstance();
		
		System.out.println("singleton1 = "+singleton1);
		System.out.println("singleton1 = "+singleton2);
	
		singleton1.display();
		singleton2.display();
		System.out.println("=========================================================================");
		Singleton.getInstance().display();
	}
}
