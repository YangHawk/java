package xyz.itwill.util;

import java.util.Calendar;

public class CalendarApp {
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();

		System.out.println("calendar.toString() = " + calendar.toString());
		System.out.println("calendar = " + calendar);

		String[] day = { "일", "월", "화", "수", "목", "금", "토" };

		String printDate = calendar.get(Calendar.YEAR) + "년 " + (calendar.get(Calendar.MONTH) + 1) + "월 "
				+ calendar.get(Calendar.DATE) + "일 " + day[calendar.get(Calendar.DAY_OF_WEEK) - 1] + "요일";

		System.out.println("현재 날짜 = " + printDate);
	}
}
