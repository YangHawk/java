package xyz.itwill.exception;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CalcApp {
	public CalcApp() throws InputMismatchException, ArithmeticException {
		Scanner scanner = new Scanner(System.in);

		System.out.print("첫번째 정수값 입력 >> ");
		int num1 = scanner.nextInt();

		System.out.print("두번째 정수값 입력 >> ");
		int num2 = scanner.nextInt();

		System.out.println("[결과]" + num1 + " / " + num2 + " = " + (num1 / num2));

		scanner.close();
	}

	public static void main(String[] args) {
		try {
			new CalcApp();
		}
		/*
		catch (InputMismatchException e) {
			System.out.println("[에러]정수값만 입력 가능합니다.");
		} catch (ArithmeticException e) {
			System.out.println("[에러]0으로 나눌 수 없습니다.");
		}
		*/
		catch (InputMismatchException | ArithmeticException e) {
			System.out.println("[에러]형식에 맞게 입력하시오.");
		}
		catch (Exception e) {
			System.out.println("[에러]프로그램에 예기치 못한 오류가 발생하였습니다.");
		}
	}
}
