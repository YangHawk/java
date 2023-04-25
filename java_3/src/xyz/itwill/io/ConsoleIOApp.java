package xyz.itwill.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

public class ConsoleIOApp {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("이름 입력 >>");

		String name = in.readLine();

		System.out.print("태어난 년도 입력 >>");

		int birthYear = Integer.parseInt(in.readLine());

		int age = Calendar.getInstance().get(Calendar.YEAR) - birthYear + 1;

		System.out.println("[결과]" + name + "의 나이는" + age + "살 입니다.");
	}
}
