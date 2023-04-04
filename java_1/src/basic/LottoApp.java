package basic;

public class LottoApp {
	public static void main(String[] args) {
		int[] lotto = new int[6];

		for (int i = 0; i < lotto.length; i++) {
			while (true) {
				lotto[i] = (int) (Math.random() * 45) + 1;

				boolean result = false;

				for (int j = 0; j < i; j++) {
					if (lotto[i] == lotto[j]) {
						result = true;
						break;
					}
				}

				if (!result)
					break;
			}
		}

		for (int i = 0; i < lotto.length; i++) {
			for (int j = i + 1; j < lotto.length; j++) {
				if (lotto[i] > lotto[j]) {
					int temp = lotto[i];
					lotto[i] = lotto[j];
					lotto[j] = temp;
				}

			}
		}

		System.out.print("행운의 숫자 >> ");
		for (int number : lotto) {
			System.out.print(number + " ");
		}
		System.out.println();
	}
}
