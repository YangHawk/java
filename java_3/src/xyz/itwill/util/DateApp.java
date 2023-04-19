package xyz.itwill.util;

import java.util.Date;

public class DateApp {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Date now = new Date();

		System.out.println("now.toString() = " + now.toString());
		System.out.println("now = " + now);

		String[] day = { "일", "월", "화", "수", "목", "금", "토" };

		String printDate = (now.getYear() + 1900) + "년 " + (now.getMonth() + 1) + "월 " + now.getDate() + "일 "
				+ day[now.getDay()] + "요일";
		System.out.println("현재 = " + printDate);

		// long currentTime = now.getTime();
		long currentTime = System.currentTimeMillis();

		Date wantDate = new Date(116, 4, 1);

		System.out.println("wantDate = " + wantDate);

		long wantTime = wantDate.getTime();

		System.out.println("연산결과(일) = " + (currentTime - wantTime) / (1000 * 86400));

	}
}
