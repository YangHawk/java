package example;

import java.util.Scanner;

//키보드로 정수값을 입력받아 1~9 범위의 정수값을 곱한 결과를 출력하는 프로그램을 작성하세요.
//단,키보드로 입력된 정수값은 2~9 범위의 정수값만 허용하면 범위를 벗어난 정수값을 입력한 경우
//에러 메세지 출력 후 재입력하도록 프로그램 작성
//ex) 단 입력[2~9] >> 7
//    7 * 1 = 7
//    ...
//    7 * 9 = 63
public class GuGuDanExample {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		/* (방법 1)
		
		int num = 1;
		while (num < 2 || num > 9) {
			System.out.println("단 입력[2~9] >> ");
			num = scanner.nextInt();
			if (num < 2 || num > 9) {
				System.out.println("[에러]2~9 사이 정수값만 입력 가능합니다.");
			} else {
				for (int i = 2; i <= 9; i++) {
					System.out.println(num + " * " + i + " = " + num * i);
				}
			}

		}
		scanner.close();
		
		*/
		
		/* (방법 2)
		
		int num;
		do {
			System.out.println("단 입력[2~9] >> ");
			num=scanner.nextInt();
			if(num < 2 || num > 9 ) {
				System.out.println("[에러]2~9 사이 정수값만 입력 가능합니다.");
			}
		} while(num < 2 || num > 9 );
		for (int i = 2; i <=9; i++) {
			System.out.println(num +num + " * " + i + " = " + num * i);
		}
		
		scanner.close();
		
		*/
	
		int num;
		while(true) {
			System.out.println("단 입력[2~9] >> ");
			num=scanner.nextInt();
			if(num >= 2 && num <= 9) break;
			System.out.println("[에러]2~9 사이 정수값만 입력 가능합니다.");
		}
		for (int i = 2; i <=9; i++) {
			System.out.println(num +num + " * " + i + " = " + num * i);
		}
		
		scanner.close();
		
	}
}
