package xyz.itwill.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DayCalculateApp {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		//Date now = new Date();
		
		Date birthday =null; // Date birthday = 이렇게 하면 try { } 끝나면 변수 소멸! 
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		//String printDate = dateFormat.format(now);

		while (true) {

			try {

				System.out.print("생년월일 입력 >> ");
				
				/*
				String birthday = scanner.nextLine();
				Date printBirthday = dateFormat.parse(birthday);
				*/
				
				birthday = dateFormat.parse(scanner.nextLine());
				
				
				
				//long birthTime = printBirthday.getTime();
				
				//long birthTime = birthday.getTime();
				
				//long currentTime = System.currentTimeMillis();

				System.out.println("연산결과(일) = " + (System.currentTimeMillis() - birthday.getTime()) / (1000 * 86400));

			} catch (ParseException e) {

				System.out.println("에러! yyyy-MM-dd 형식에 맞게 입력하세요.");

				scanner.nextLine();

				continue;

			}
			break;
		}
		scanner.close();
	}
}
