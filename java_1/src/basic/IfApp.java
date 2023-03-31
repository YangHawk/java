package basic;

public class IfApp {
	public static void main(String[] args) {
		int su = 90;
		if (su >= 50) {
			System.out.println("su = " + su);
		}
		System.out.println("================================================");
		int score = 80;
		if (score >= 60) {
			System.out.println("[결과]점수가 60 이상이므로 합격입니다.");
		}
		if (score < 60) {
			System.out.println("[결과]점수가 60점 미만이므로 불합격입니다.");
		}
		System.out.println("================================================");
		int score2 = 50;
		if (score2 >= 60) {
			System.out.println("[결과]점수가 60 이상이므로 합격입니다.");
		} else {
			System.out.println("[결과]점수가 60점 미만이므로 불합격입니다.");
		}
		System.out.println("================================================");
		int num = 9;
		if (num % 2 != 0) {
			System.out.println(num + " = 홀수");
		}

		if (num % 2 == 0) {
			System.out.println(num + " = 짝수");
		}
		System.out.println("================================================");
		int num2 = 8;
		if (num % 2 != 0) {
			System.out.println(num2 + " = 홀수");

		} else {
			System.out.println(num2 + " = 짝수");
		}
		System.out.println("================================================");
		char mun = 'O';
		if (mun >= 'A' && mun <= 'Z' || mun >= 'a' && mun <= 'z') {
			System.out.println("[결과] " + mun + "은 영문자이다!");
		} else {
			System.out.println("[결과] " + mun + "은 비영문자이다!");
		}
		System.out.println("================================================");
		boolean sw = true;
		if (sw == true) {
			System.out.println("현재 변수값은 [참]입니다.");
		} else {
			System.out.println("현재 변수값은 [거짓]입니다.");
		}
		if (sw) {
			System.out.println("현재 변수값은 [참]입니다.");
		} else {
			System.out.println("현재 변수값은 [거짓]입니다.");
		}
		if (!sw) {
			System.out.println("현재 변수값은 [참]입니다.");
		} else {
			System.out.println("현재 변수값은 [거짓]입니다.");
		}
		System.out.println("================================================");
		int jumsu = 61;
		if (jumsu >= 0 && jumsu <= 100) {
			System.out.println("[결과]0~100 범위의 정상적인 점수가 입력되었습니다.");

			String grade = "";
			if (jumsu >= 90 && jumsu <= 100) {
				grade = "A";
			} else if (jumsu >= 80 && jumsu <= 89) {
				grade = "B";
			} else if (jumsu >= 70 && jumsu <= 79) {
				grade = "C";
			} else if (jumsu >= 60 && jumsu <= 69) {
				grade = "D";
			} else if (jumsu >= 0 && jumsu <= 59) {
				grade = "F";
			}
			System.out.println("[결과]" + jumsu + "점 = " + grade + "학점");
		} else {
			System.out.println("[결과]0~100 범위를 벗어난 비정상적인 점수가 입력되었습니다.");
		}

		if (jumsu >= 0 && jumsu <= 100) {
			System.out.println("[결과]0~100 범위의 정상적인 점수가 입력되었습니다.");

			String grade = "";
			if (jumsu >= 90) {
				grade = "A";
			} else if (jumsu >= 80) {
				grade = "B";
			} else if (jumsu >= 70) {
				grade = "C";
			} else if (jumsu >= 60) {
				grade = "D";
			} else {
				grade = "F";
			}
			System.out.println("[결과]" + jumsu + "점 = " + grade + "학점");
		} else {
			System.out.println("[결과]0~100 범위를 벗어난 비정상적인 점수가 입력되었습니다.");
		}

		System.out.println("================================================");

	}

}