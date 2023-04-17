package xyz.itwill.lang.thread;

public class MultiThreadApp {
	public static void main(String[] args) throws InterruptedException {
		
		/*
		MultiThreadOne one = new MultiThreadOne();
		one.start();
		one.start();
		*/
		
		new MultiThreadOne().start();
		new MultiThreadOne().start();
		
		/*
		MultiThreadTwo two = new MultiThreadTwo();
		two.start(); ← 객체가 아니니까 이렇게 사용할 수 없는거야!
		*/
		
		// Thread thread = new Thread(new MultiThreadTwo()).start(); Thread 매개 변수!

		new Thread(new MultiThreadTwo()).start();

		for (char i = 'A'; i <= 'Z'; i++) {
			System.out.print(i);

			Thread.sleep(500);
		}
	}
}
