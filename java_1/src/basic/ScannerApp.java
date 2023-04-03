package basic;

import java.util.Scanner;

public class ScannerApp {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("이름 입력 >>");
		String name = scanner.nextLine();
		System.out.print("나이 입력 >>");
		int age = scanner.nextInt();

		System.out.println("[결과]" + name + "님의 나이는 " + age + "살 입니다.");

		scanner.close();
	}
}
