package nested;

public class AnonymousApp {
	public static void main(String[] args) {
		// Anonymous anonymous = new Anonymous();

		Anonymous anonymous = new Anonymous() {

			@Override
			public void display() {
				System.out.println("익명의 내부클래스의 오버라이드 메소드 호출");

			}
		};

		anonymous.display();
	}
}
