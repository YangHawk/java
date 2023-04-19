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

		System.out.print("연산식 입력 >> ");

		String operation = scanner.nextLine().replace(" ", "");
		// System.out.println("operation = "+operation);
		String[] operatorArray = { "*", "/", "+", "-" };

		int index = -1;

		for (String operator : operatorArray) {
			// index = operation.indexOf(operator);
			index = operation.lastIndexOf(operator);
			if (index != -1)
				break;
		}

		if (index <= 0 || index > operation.length() - 1) {
			System.out.println("[에러]연산식을 잘못 입력하였습니다.");
			System.exit(0);
		}

		try {
			int num1 = Integer.parseInt(operation.substring(0, index));

			String operator = operation.substring(index, index + 1);

			int num2 = Integer.parseInt(operation.substring(index + 1));

			int result = 0;
			switch (operator) {
			case "*":
				result = num1 * num2;
				break;
			case "/":
				result = num1 / num2;
				break;
			case "+":
				result = num1 + num2;
				break;
			case "-":
				result = num1 - num2;
				break;
			}

		} catch (NumberFormatException e) {
			System.out.println("[에러]연산식에 숫자가 아닌 값이 입력되었습니다.");
		} catch (ArithmeticException e) {
			System.out.println("[에러]0으로 나눌 수 없습니다.");
		} catch (Exception e) {
			System.out.println("[에러]프로그램에 예기치 못한 오류가 발생하였습니다.");
		}
	}
}