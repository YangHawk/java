package example;

import java.util.Scanner;

//컴퓨터로부터 제공받은 정수 난수값을 키보드로 입력하여 맞추는 프로그램을 작성하세요.
// => 1~100 범위의 정수 난수값을 제공받도록 작성
// => 난수값을 맞출 수 있는 기회는 10번만 제공되도록 작성
// => 키보드 입력값이 1~100 범위가 아닌 경우 메세지 출력 후 재입력
// => 난수값과 입력값이 같은 경우 입력 횟수 출력 후 프로그램 종료
// => 난수값과 입력값이 다른 경우 "큰값 입력" 또는 "작은값 입력" 형식의 메세지 출력
// => 난수값을 10번 안에 맞추지 못한 경우 난수값이 출력되도록 작성
public class UpAndDownExample {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int dap = (int) (Math.random() * 100) + 1;

		boolean result = false;

		for (int cnt = 1; cnt <= 10; cnt++) {
			int input;
			while (true) {
				System.out.println(cnt + "번째 정수값 입력[1~100] >> ");
				input = scanner.nextInt();
				if (input >= 1 && input <= 100)
					break;
				System.out.println("[에러]1~100 범위의 정수값만 입력해 주세요.");
			}
			if (dap == input) {
				System.out.println("[메세지]축하합니다. " + cnt + "번만에 맞췄습니다.");
				result = true;
				break;
			} else if (dap > input) {
				System.out.println("[결과]" + input + "보다 큰 값을 입력해 보세요.");
			} else {
				System.out.println("[결과]" + input + "보다 작은 값을 입력해 보세요.");
			}
		}
			if (!result) {
				System.out.println("[메세지]정답을 맞추지 못했군요. 정답은 [" + dap + "]입니다.");
			}
		scanner.close();
	}
}
