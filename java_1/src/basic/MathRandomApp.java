package basic;

public class MathRandomApp {
	public static void main(String[] args) {
		for (int i = 1; i <= 5; i++) {
			System.out.println(i + "번째 실수 난수값 = " + Math.random());
		}
		System.out.println("===================================================");
		for (int i = 1; i <= 5; i++) {
			System.out.println(i + "번째 정수 난수값 = " + (int) (Math.random() * 100));
		}
		System.out.println("===================================================");
		for (int i = 1; i <= 6; i++) {
			System.out.println(i + "번째 정수 난수값 = " + ((int) (Math.random() * 45) + 1));
			System.out.println(i + "번째 정수 난수값 = " + (int) (Math.random() * 45) + 1);
			//82Page, 102Page 보기!
		}
		System.out.println("===================================================");
	}
}
