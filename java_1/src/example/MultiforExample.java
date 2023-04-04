package example;

public class MultiforExample {
	public static void main(String[] args) {
		// ★★★★★
		// ★★★★
		// ★★★
		// ★★
		// ★
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= (6 - i); j++) {
				System.out.print("★");
			}
			System.out.println();
		}

		System.out.println("===============================================================");
		// ★★★★★
		// ☆★★★★
		// ☆☆★★★
		// ☆☆☆★★
		// ☆☆☆☆★
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 5; j++) {
				if (i <= j) {
					System.out.print("★");
				} else {
					System.out.print("☆");
				}
			}
			System.out.println();
		}
		System.out.println("===============================================================");
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= i - 1; j++) {
				System.out.print("☆");
			}
			for (int j = 1; j <= 6 - i; j++) {
				System.out.print("★");
			}
			System.out.println();
		} // 위와 동일한 결과가 출력된다!

		System.out.println("===============================================================");
		// ★
		// ★★★
		// ★★★★★
		// ★★★★★★★
		// ★★★★★★★★★
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= (2 * i - 1); j++) {
				System.out.print("★");
			}
			System.out.println();
		}
		System.out.println("===============================================================");
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 5 - i; j++) {
				System.out.print("  ");
			}
			for (int j = 1; j <= (2 * i - 1); j++) {
				System.out.print("★");
			}
			System.out.println();
		} // 신기한 것!
		System.out.println("===============================================================");
		// ★☆☆☆★
		// ☆★☆★☆
		// ☆☆★☆☆
		// ☆★☆★☆
		// ★☆☆☆★
		for (int i = 1; i <= 5; i++) {
			for (int j = 5; j >= 1; j--) {
				if (i + j == 6 || i == j) {
					System.out.print("★");
				} else {
					System.out.print("☆");

				}
			}
			System.out.println();
		}

		System.out.println("===============================================================");
	}
}
