package xyz.itwill.app;

import java.util.Date;
import java.util.Scanner;

public class CalcAgeApp {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("이름 입력 >>");
		String name = scanner.nextLine();

		System.out.println("태어난 년도 >>");
		int birthYear = scanner.nextInt();

		Date now = new Date();

		@SuppressWarnings("deprecation")
		int currentYear = now.getYear() + 1900;

		// int age = 2023 - birthYear + 1;
		int age = currentYear - birthYear + 1;
		
		System.out.println("[결과]" + name + "님의 나이는 " + age + "살 입니다.");

		scanner.close();
	}
}
