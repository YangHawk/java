package xyz.itwill.lang;

public class WrapperApp {
	public static void main(String[] args) {
		/*
		int num1 = 100, num2 = 200;
		int num3 = num1 + num2;
		System.out.println("합계 = " + num3);
		*/

		/*
		// Integer num1 = new Integer(100);
		Integer num1 = Integer.valueOf(100);
		Integer num2 = Integer.valueOf("200");
		// Integer num3 = num1.intValue() + num2.intValue();
		Integer num3 = Integer.valueOf(num1.intValue() + num2.intValue());
		System.out.println("num = " + num3.intValue());
		*/
		
		Integer num1 = 100, num2 = 200;
		Integer num3 = num1 + num2;

		System.out.println("num 3 = " + num3);
		System.out.println("=============================================================");
		Integer num = Integer.valueOf("ABC", 16);

		System.out.println("num = " + num);
		System.out.println("=============================================================");
		Integer su = 50;

		System.out.println("su(10진수) = " + su);
		System.out.println("su(8진수) = " + Integer.toOctalString(su));
		System.out.println("su(16진수) = " + Integer.toHexString(su));
		System.out.println("su(2진수) = " + Integer.toBinaryString(su));

		su = -50;

		System.out.println("su(2진수) = " + Integer.toBinaryString(su));
		System.out.println("=============================================================");
		String str1 = "100", str2 = "200";

		System.out.println("합계 = " + (str1 + str2));
		System.out.println("합계 = " + (Integer.parseInt(str1) + Integer.parseInt(str2)));
		System.out.println("=============================================================");
	}
}
