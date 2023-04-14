package xyz.itwill.lang;

import java.util.Scanner;

public class SystemApp {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("정수값 입력 >> ");
		int num = scanner.nextInt();

		if (num == 0) {
			System.out.println("[메세지]프로그램을 강제로 종료합니다.");
			System.exit(0);

		}
		long startTime = System.currentTimeMillis();

		for (int i = 1; i <= num; i++) {
			System.out.println(i + "번째 실행되는 명령");
		}

		long endTime = System.currentTimeMillis();

		System.out.println("실행시간 = " + (endTime - startTime) + "ms");

		System.gc();

		scanner.close();
	}
}
