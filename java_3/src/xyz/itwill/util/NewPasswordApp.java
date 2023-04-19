package xyz.itwill.util;

import java.util.Random;
import java.util.UUID;

public class NewPasswordApp {
	public static String newPasswordOne() {
		Random random = new Random();

		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789~!@#$%^&*";

		StringBuffer password = new StringBuffer();
		for (int i = 1; i <= 10; i++) {
			password.append(str.charAt(random.nextInt(str.length())));
		}

		return password.toString();
	}

	public static String newPasswordTwo() {
		return UUID.randomUUID().toString().replace("-", "").substring(0, 10).toUpperCase();
	}

	public static void main(String[] args) {
		System.out.println("새로운 비밀번호-1 = " + newPasswordOne());
		System.out.println("새로운 비밀번호-2 = " + newPasswordTwo());
	}
}
