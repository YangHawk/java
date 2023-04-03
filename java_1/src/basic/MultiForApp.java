package basic;

public class MultiForApp {
	public static void main(String[] args) {
		int cnt = 0;

		for (int i = 1; i <= 3; i++) {
			for (int j = 1; j <= 4; j++) {
				cnt++;
			}
		}
		System.out.println("이동방법의 갯수 = " + cnt);
		System.out.println("================================================================");
		// ★
		for (int i = 1; i <= 10; i++) {// 행(Row)을 처리하기 위한 반복문
			for (int j = 1; j <= 7; j++) {// 열(Column)을 처리하기 위한 반복문
				System.out.print("★");
			}
			System.out.println();
		}
		System.out.println("================================================================");
		for (int i = 1; i <= 9; i++) {// 9행
			/*
			 * for (int j = 1; j <= 8; j++) {// 8열 System.out.print((j + 1) + "*" + "\t"); }
			 */
			for (int j = 2; j <= 9; j++) {
				System.out.print(j + "*" + i + "=" + (i * j) + "\t");
			}
			System.out.println();
		}
		System.out.println("================================================================");
		for (int i = 5; i >= 1; i--) {// 위와 동일하다...
			for (int j = 5; j >= 1; j--) {
				System.out.print("★");
			}
			System.out.println();
		}
		System.out.println("================================================================");
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= i; j++) {// 출력되는 별의 갯수는 i(행)의 수이므로....1차방정식이라고 생각!
				System.out.print("★");
			}
			System.out.println();
		}
		System.out.println("================================================================");
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= (6 - i); j++) {
				System.out.print("★");
			}
			System.out.println();
		}
		System.out.println("================================================================");
		for (int i = 5; i >= 1; i--) {
			for (int j = 1; j <= i; j++) {
				System.out.print("★");
			}
			System.out.println();
		}
		System.out.println("================================================================");
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= (5 - i); j++) {
				System.out.print("☆");
			}
			for (int j = 1; j <= i; j++) {
				System.out.print("★");
			}
			System.out.println();
		}
		System.out.println("================================================================");
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 5; j++) {
				if (i + j <= 5) {// i+j<6 과 같다.
					System.out.print("☆");
				} else {
					System.out.print("★");
				}
			}
			System.out.println();
		}
		System.out.println("================================================================");

	}
}