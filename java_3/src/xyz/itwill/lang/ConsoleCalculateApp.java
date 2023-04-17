package xyz.itwill.lang;

import java.util.Scanner;

//키보드로 사칙 연산식을 입력받아 연산결과를 계산하여 출력하는 프로그램 작성
//ex) 연산식 입력 >> 20 + 10
//    [결과]30
// => 입력 연산식에서 사용 가능한 연산자는 사칙 연산자(*,/,+,-)만 허용
// => 형식에 맞지 않는 연산식이 입력된 경우 에러 메세지 출력 후 프로그램 종료
// => 입력 연산식에 공백 입력이 가능하도록 처리
public class ConsoleCalculateApp {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		/* 첫번 째 방법..!
		String ill;
		String ee;
		String hap;
		int yeonsan;

		System.out.print("연산식 입력 >> ");
		ill = scanner.nextLine().trim();
		hap = scanner.nextLine().trim();
		ee = scanner.nextLine().trim();

		if (hap.equals("+") || hap.equals("-") || hap.equals("*") || hap.equals("/")) {
			System.out.println("연산 성공!");
		} else {
			System.out.println("에러! 제대로 된 연산자를 입력하세요!");
			return;
		}

		if (hap.equals("+")) {
			yeonsan = Integer.parseInt(ill) + Integer.parseInt(ee);
		} else if (hap.equals("-")) {
			yeonsan = Integer.parseInt(ill) - Integer.parseInt(ee);
		} else if (hap.equals("*")) {
			yeonsan = Integer.parseInt(ill) * Integer.parseInt(ee);
		} else {
			yeonsan = Integer.parseInt(ill) / Integer.parseInt(ee);
		}

		System.out.println("[결과]" + yeonsan)
		*/
		
		String yeonsan;
		
		System.out.print("연산식 입력 >> ");
		yeonsan = scanner.nextLine().replace(" ", "");
		
	}
}