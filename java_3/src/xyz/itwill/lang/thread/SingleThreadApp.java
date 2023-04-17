package xyz.itwill.lang.thread;

public class SingleThreadApp {
	public static void main(String[] args) {
		System.out.println("SingleThreadApp 클래스의 main() 메소드 시작");

		System.out.println("[" + Thread.currentThread().getName() + "] " + "스레드에 의해 main() 메소드의 명령 실행");

		new SingleThread().display();

		System.out.println("SingleThreadApp 클래스의 main() 메소드 종료");
		System.out.println("=============================================================");

		for (char i = 'A'; i <= 'Z'; i++) {
			System.out.print(i);
		}
		new SingleThread().display();
	}
}
