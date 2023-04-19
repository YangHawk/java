package xyz.itwill.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatApp {
	public static void main(String[] args) {
		Date now = new Date();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 E요일");

		String printDate = dateFormat.format(now);

		System.out.println("현재 날짜 = " + printDate);

		dateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");

		System.out.println("현재 = " + dateFormat.format(now));

		String want = "2000-01-01 00:00:00";

		try {
			Date wantDate = dateFormat.parse(want);
			System.out.println("wantDate = " + wantDate);
		} catch (ParseException e) {

			System.out.println("[에러]형식에 맞게 날짜와 시간을 입력하여 주십시오.");
		}

	}
}
