package basic;

public class SwitchApp {
	public static void main(String[] args) {
		int choice = 1;
		switch (choice) {
		case 1:
			System.out.println("수성으로 이동합니다.");
		case 2:
			System.out.println("금성으로 이동합니다.");
		case 3:
			System.out.println("화성으로 이동합니다.");
		}
		System.out.println("==================================================");
		choice = 4;
		switch (choice) {
		case 1:
			System.out.println("수성으로 이동합니다.");
		case 2:
			System.out.println("금성으로 이동합니다.");
		case 3:
			System.out.println("화성으로 이동합니다.");
		default:
			System.out.println("지구로 이동합니다.");
		}
		System.out.println("==================================================");
		choice = 1;
		switch (choice) {
		case 1:
			System.out.println("수성으로 이동합니다.");
			break;
		case 2:
			System.out.println("금성으로 이동합니다.");
			break;
		case 3:
			System.out.println("화성으로 이동합니다.");
			break;
		}
		System.out.println("==================================================");
		int jumsu = 100;
		if (jumsu >= 0 && jumsu <= 100) {
			String grade;
			switch (jumsu / 10) {
			case 10:
			case 9:
				grade = "A";
				break;
			case 8:
				grade = "B";
				break;
			case 7:
				grade = "C";
				break;
			case 6:
				grade = "D";
				break;
			default:
				grade = "F";

			}

			System.out.println("점수 = " + jumsu + ", 학점 = " + grade);
		} else {
			System.out.println("[에러]0~100 범위를 벗어난 비정상적인 점수가 입력 되었습니다.");

		}
		System.out.println("==================================================");

		String kor = "둘";
		String eng = "";

		switch (kor) {
		case "하나":
			eng = "One";
			break;
		case "둘":
			eng = "Two";
			break;
		case "셋":
			eng = "Three";
			break;
		}

		System.out.println("[결과]" + kor + " = " + eng);
	}
}