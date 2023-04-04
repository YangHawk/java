package basic;

import java.util.Scanner;

public class RpaGameApp {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int count = 0; // count = 사용자 승리 횟수를 저장하기 위한 변수

		while (true) {

			int computer = (int) (Math.random() * 3) + 1;
			int user;
			while (true) {// 무한루프, 컴퓨터에게 가위바위보 중 하나를 제공받아 저장! 1: 가위, 2: 바위, 3: 보
				System.out.println("가위바위보[1:가위, 2:바위, 3:보] >> ");
				user = scanner.nextInt();
				if (user >= 1 && user <= 3)
					break;
				System.out.println("[에러]가위, 바위, 보 중 하나를 선택하여 입력해주세요.");

			}
			System.out.println("[입력]컴퓨터 >> ");
			switch (computer) {
			case 1:
				System.out.println("가위");
				break;
			case 2:
				System.out.println("바위");
				break;
			case 3:
				System.out.println("보");
				break;
			}
			System.out.println("[입력]사용자 >> ");
			switch (user) {
			case 1:
				System.out.println("가위");
				break;
			case 2:
				System.out.println("바위");
				break;
			case 3:
				System.out.println("보");
				break;
			}
			System.out.println();
			if (computer == user) {
				System.out.println("[결과]비김");
			} else if (computer == 1 && user == 2 || computer == 2 && user == 3 || computer == 3 && user == 1) {
				System.out.println("[결과]승리!");
				count++;
			} else {
				System.out.println("[결과]패배!");
				break;
			}
			System.out.println();
		}
		System.out.println("==================================================================");
		if (count == 0) {
			System.out.println("[메세지]한번도 이기지 못했습니다.");

		} else {
			System.out.println("[메세지]연속 " + count + "번 승리하였습니다.");
		}
		scanner.close();
	}
}
