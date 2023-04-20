package xyz.itwill.util;

import java.util.Calendar;
import java.util.Scanner;

public class WantCalendarApp {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Calendar calendar = Calendar.getInstance();

		System.out.println("년 입력 >>");
		int year = scanner.nextInt();
		System.out.println("월 입력 >>");
		int month = scanner.nextInt();

		scanner.close();

		calendar.set(year, month-1, 1);

		int week = calendar.get(Calendar.DAY_OF_WEEK);

		System.out.println("      " + calendar.get(Calendar.YEAR) + "년 " + (calendar.get(Calendar.MONTH) + 1) + "월 ");
		System.out.println("=======================");
		System.out.println(" 일 월 화 수 목 금 토 ");
		System.out.println("=======================");

		for (int i = 1; i < week; i++) {
			System.out.print("   ");
		}

		for (int i = 1; i <= calendar.getActualMaximum(Calendar.DATE); i++) {
			if (i <= 9) {
				System.out.print("  " + i);
			} else {
				System.out.print(" " + i);
			}

			week++;

			if (week % 7 == 1) {
				System.out.println();
			}
		}

	}
}
