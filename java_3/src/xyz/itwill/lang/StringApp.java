package xyz.itwill.lang;

public class StringApp {
	public static void main(String[] args) {
		String str1 = "ABC";

		System.out.println("str1.toString() = " + str1.toString());
		System.out.println("str1 = " + str1);
		System.out.println("==============================================================");
		String str2 = "ABC";

		if (str1 == str2) {
			System.out.println("str1 변수와 str2 변수의 저장된 String 객체의 메모리 주소가 같네?");
		} else {
			System.out.println("str1 변수와 str2 변수의 저장된 String 객체의 메모리 주소가 다르네?");
		}
		System.out.println("==============================================================");
		String str3 = new String("ABC");
		if (str1 == str3) {
			System.out.println("str1 변수와 str3 변수의 저장된 String 객체의 메모리 주소가 같네?");
		} else {
			System.out.println("str1 변수와 str3 변수의 저장된 String 객체의 메모리 주소가 다르네?");
		}
		System.out.println("==============================================================");

		if (str1.equals(str3)) {
			System.out.println("str1 변수와 str3 변수의 저장된 String 객체의 문자열이 같네?");
		} else {
			System.out.println("str1 변수와 str3 변수의 저장된 String 객체의 문자열이 다르네?");
		}
		System.out.println("==============================================================");
		String str4 = "abc";

		if (str1.equals(str4)) {
			System.out.println("str1 변수와 str4 변수의 저장된 String 객체의 문자열이 같네?");
		} else {
			System.out.println("str1 변수와 str4 변수의 저장된 String 객체의 문자열이 다르네?");
		}
		System.out.println("==============================================================");
		if (str1.equalsIgnoreCase(str4)) {
			System.out.println("str1 변수와 str4 변수의 저장된 String 객체의 문자열이 같네?");
		} else {
			System.out.println("str1 변수와 str4 변수의 저장된 String 객체의 문자열이 다르네?");
		}
		System.out.println("==============================================================");
		if (str1.compareTo(str4) > 0) {
			System.out.println("str1 변수에 저장된 문자열이 str4 변수의 저장된 문자열보다 크네?");
		} else if (str1.compareTo(str4) < 0) {
			System.out.println("str1 변수에 저장된 문자열이 str4 변수의 저장된 문자열보다 작네?");
		} else {
			System.out.println("str1 변수에 저장된 문자열이 str4 변수의 저장된 문자열과 같네?");
		}
		System.out.println("==============================================================");
		byte[] array = str1.getBytes();

		for (byte ch : array) {
			// System.out.print(ch);
			System.out.print((char) ch);
		}
		System.out.println();
		System.out.println("==============================================================");
		String str5 = "ABCDEFG";

		System.out.println("문자열의 문자 갯수 = " + str5.length());
		System.out.println("==============================================================");
		System.out.println("두번째 위치의 문자 = " + str5.charAt(1));
		System.out.println("==============================================================");
		System.out.println("A 문자열의 저장 위치 = " + str5.indexOf("A"));
		System.out.println("A 문자열의 저장 위치 = " + str5.indexOf("EFG"));
		System.out.println("A 문자열의 저장 위치 = " + str5.indexOf("X"));
		System.out.println("==============================================================");
		String str6 = "Java Programming";

		System.out.println("str6 = " + str6);
		System.out.println("str6.toUpperCase = " + str6.toUpperCase());
		System.out.println("str6.toLowerCase = " + str6.toLowerCase());
		System.out.println("==============================================================");
		String str7 = "		홍길동		";

		System.out.println("입력된 이름은 [" + str7 + "]입니다.");
		System.out.println("입력된 이름은 [" + str7.trim() + "]입니다.");
		System.out.println("==============================================================");
		String str8 = "		임		꺽		정		";
		System.out.println("입력된 이름은 [" + str8 + "]입니다.");
		System.out.println("입력된 이름은 [" + str8.replace("	", "").replace("꺽", "걱") + "]입니다.");
		System.out.println("==============================================================");
		String str9 = "010-1234-5678";

		System.out.println("전화번호 = " + str9);
		System.out.println("==============================================================");
		String[] numArray = str9.split("-");
		// String[] numArray = str9.split("\\*");

		System.out.println("전화번호 앞부분 = " + numArray[0]);
		System.out.println("전화번호 중간부분 = " + numArray[1]);
		System.out.println("전화번호 뒷부분 = " + numArray[2]);
		System.out.println("==============================================================");
		System.out.println("전화번호 앞부분 = " + str9.substring(0, 3));
		System.out.println("전화번호 중간부분 = " + str9.substring(4, 8));
		// System.out.println("전화번호 뒷부분 = " + str9.substring(9, 13));
		System.out.println("전화번호 뒷부분 = " + str9.substring(9));
		System.out.println("==============================================================");
		// String numString = String.valueOf(100);
		String numString = 100 + "";

		System.out.println("numString = " + numString);
		System.out.println("==============================================================");

	}
}
