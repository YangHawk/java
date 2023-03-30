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
		System.out.println("<<문자형(CharacterType)>>");
		System.out.println("문자형(2Byte) = "+'A');
		System.out.println("문자형(2Byte) = "+'가');
		System.out.println("문자형(2Byte) = "+'\'');
		System.out.println("문자형(2Byte) = "+'\\');
		
		char c1='A';
		char c2=65;
		char c3='a'-32;
		
		System.out.println("c1 = "+c1);
		System.out.println("c2 = "+c2);
		System.out.println("c3 = "+c3);
		
		char c4=45000;

		System.out.println("c4 = "+c4);
		System.out.println("==============================================");
		System.out.println("<<논리형(BooleanType)>>");
		System.out.println("논리값(1Byte) = "+false);
		System.out.println("논리값(1Byte) = "+true);
		System.out.println("논리값(1Byte) = "+(20>10));
		System.out.println("논리값(1Byte) = "+(20<10));
		
		boolean d1=false;
		boolean d2=20>10;
		
		System.out.println("d1 = "+d1);
		System.out.println("d2 = "+d2);
		
		System.out.println("<<문자열(StringType)>>");
		System.out.println("문자열 = "+"홍길동");
		System.out.println("문자열 = "+"유관순 열사가 \"대한독립 만세\"를 외쳤습니다.");
		
		String name="임꺽정";
		System.out.println("이름 = "+name);
		System.out.println("==============================================");

	}
}
