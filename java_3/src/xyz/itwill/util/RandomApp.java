package xyz.itwill.util;

import java.util.Random;

public class RandomApp {
	public static void main(String[] args) {
		Random random = new Random();
		
		for(int i = 1; i<=5;i++) {
			System.out.println(i+"번째 난수값 >> "+random.nextInt(100));
		}
	}
}
