package xyz.itwill.util;

import java.util.Random;
import java.util.Scanner;

public class BaseballGameApp {
	public static void main(String[] args) {
		Random random = new Random();

		int[] dap = new int[3];
		int[] num = new int[3];

		boolean result = false;

		while (true) {
			for (int i = 0; i < dap.length; i++) {
				dap[i] = random.nextInt(10);
			}

			if (dap[0] != 0 && dap[0] != dap[1] && dap[1] != dap[2] && dap[2] != dap[0])
				break;
		}
		Scanner scanner = new Scanner(System.in);

		for (int cnt = 1; cnt <= 15; cnt++) {
			int strike = 0, ball = 0;

			loop: while (true) {
				System.out.print(cnt + "번째 입력 >>");
				String input = scanner.nextLine();

				if (input.length() != 3) {
					System.out.println("[에러]3자리의 숫자만 입력 가능합니다.");
					continue;
				}

				for (int i = 0; i < num.length; i++) {
					num[i] = input.charAt(i) - '0';

					if (num[i] < 0 || num[i] > 9) {
						System.out.println("[에러]3자리의 숫자만 입력 가능합니다.");
						continue loop;
					}
				}

				if (num[0] != 0 && num[0] != num[1] && num[1] != num[2] && num[2] != num[0])
					break;
				System.out.println("[에러]0으로 시작되거나 중복된 숫자가 존재합니다.");
			}

			for (int i = 0; i < dap.length; i++) {
				for (int j = 0; j < num.length; j++) {
					if (dap[i] == num[j]) {
						if (i == j) {
							strike++;
						} else {
							ball++;
						}
					}
				}
			}

			if (strike == 3) {
				System.out.println("[메세지]축하합니다. " + cnt + "번 만에 맞췄습니다.");
				result = true;
				break;
			}
			System.out.println("[결과]" + strike + "스트라이크, " + ball + "볼");
		}

		if (!result) {
			System.out.print("메세지]정답을 맞추지 못했습니다. 정답은 [");
			for (int su : dap) {
				System.out.print(su);
			}
			System.out.println("] 입니다.");
		}

		scanner.close();
	}
}
