package basic;

public class DataTypeApp {
	public static void main(String[] args) {
		System.out.println("<<정수형(IntegerType)>>");
		System.out.println("정수값(10진수 100) = "+100);
		System.out.println("정수값(8진수 100) = "+0100);
		System.out.println("정수값(16진수 100) = "+0x100);
		System.out.println("정수값(10진수 100) = "+100L);
		System.out.println("정수값= "+2147483647);
		System.out.println("정수값= "+2147483648L);

		byte a1=127;
		short a2=32767;
		int a3=2147483647;
		long a4=2147483648L;
		
		System.out.println("a1 = "+a1);
		System.out.println("a2 = "+a2);
		System.out.println("a3 = "+a3);
		System.out.println("a4 = "+a4);
		
		long a5=100;
		
		System.out.println("a5 = "+a5);
		System.out.println("==============================================");
		System.out.println("<<실수형(DoubleType)>>");
		System.out.println("실수값(8Byte) = "+12.3);
		System.out.println("실수값(4Byte) = "+12.3F);
		System.out.println("실수값(8Byte) = "+0.000000000123);
		System.out.println("실수값(8Byte) = "+1.23E+10);

		float b1=1.23456789F;
		double b2=1.23456789;
	
		System.out.println("b1 = "+b1);
		System.out.println("b2 = "+b2);
		System.out.println("==============================================");
	}
}
