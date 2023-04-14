package xyz.itwill.lang;

import java.util.Scanner;

public class PersonNumberApp {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String number;

		while (true) {
			System.out.print("주민번호 입력 >> ");
			// number=scanner.nextLine().trim();
			number = scanner.nextLine().replace(" ", "");
			// if (number.length() == 14 && number.charAt(6) == '-')
			if (number.length() == 14 && number.indexOf("-") == 6)
				break;
			System.out.println("[에러]형식에 맞게 주민번호를 입력해 주세요.");

		}

		scanner.close();
		System.out.println("=============================================================");

		String separation = number.substring(7, 8);
		// System.out.println("separation = " + separation);

		String birthday = "";
		if (separation.equals("1") || separation.equals("2")) {
			birthday += "19";
		} else if (separation.equals("3") || separation.equals("4")) {
			birthday += "20";
		} else {
			birthday += "18";
		}

		birthday += number.substring(0, 2) + "년";
		birthday += number.substring(2, 4) + "월";
		birthday += number.substring(4, 6) + "일";

		System.out.println("생년월일 = " + birthday);

		String gender = "";
		if (separation.equals("1") || separation.equals("3") || separation.equals("0")) {
			gender = "남자";
		} else if (separation.equals("2") || separation.equals("4") || separation.equals("0")) {
			gender = "여자";
		} else {
			gender = "외국인";
		}
		System.out.println("성별 = " + gender);
	}
}
