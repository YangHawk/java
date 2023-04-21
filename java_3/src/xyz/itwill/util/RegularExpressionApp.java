package xyz.itwill.util;

import java.util.Scanner;
import java.util.regex.Pattern;

public class RegularExpressionApp {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		
		/*
		System.out.print("아이디 입력 >> ");
		String id = scanner.nextLine();

		if (id == null || id.equals("")) {
			System.out.println("[에러]아이디를 반드시 입력하여 주세요.");
			System.exit(0);
		}

		//String idReg = "^[a-zA-Z][a-zA-z0-0_]{5,19}$"; 같은 거야아!
		String idReg = "^[a-zA-Z]\\w{5,19}$";
		
	
		if(!Pattern.matches(idReg, id)) {
			System.out.println("[에러]아이디를 형식에 맞게 입력하여 주세요.");
			System.exit(0);
		}
		
		System.out.println("[메세지]형식에 맞는 아이디를 입력하였습니다.");
		*/

		
		/*
		System.out.print("비밀번호 입력 >>");
		String password = scanner.nextLine();

		if (password == null || password.equals("")) {
			System.out.println("[에러]비밀번호를 반드시 입력하여 주세요.");
			System.exit(0);
		}

		String passwordReg = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!@#$%^&*_-]).{8,30}$";

		if (!Pattern.matches(passwordReg, password)) {
			System.out.println("[에러]비밀번호를 형식에 맞게 입력하여 주세요.");
			System.exit(0);
		}

		System.out.println("[메세지]형식에 맞는 비밀번호를 입력하였습니다.");
		*/
		
		
		System.out.print("이메일 입력 >>");
		String email = scanner.nextLine();

		if (email == null || email.equals("")) {
			System.out.println("[에러]이메일을 반드시 입력하여 주세요.");
			System.exit(0);
		}

		String emailReg = "^([a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+)*$";

		if (!Pattern.matches(emailReg, email)) {
			System.out.println("[에러]이메일을 형식에 맞게 입력하여 주세요.");
			System.exit(0);
		}

		System.out.println("[메세지]형식에 맞는 이메일을 입력하였습니다.");

		scanner.close();
	}
}
