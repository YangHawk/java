package basic;

public class BreakApp {
	public static void main(String[] args) {
		for (int i = 1; i <= 5; i++) {
			if (i == 3)
				break;
			System.out.print(i + "\t");
		}
		System.out.println();
		System.out.println("=====================================");
		// 반복문 작성 시 반복문을 구분하기 위한 식별자(라벨명) 선언 가능
		// 형식) 라벨명:반복문 : = 위치(position)이므로...
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 5; j++) {
				System.out.println("i = " + i + ", j = " + j);
				// break 라벨명 = 라벨명으로 지정된 반복문 종료!
				if (j == 3) // 위아래 바뀌면 출력되는 값 또한 다르다...
					break; // if i break 문을 만들고 싶다면, 블럭 외곽으로 설정! 지역변수이기 때문...
			} // for (int j = 1; j <= 5; j++) 반복문만 종료하는 것이다....
		}
		System.out.println("=====================================");
		loop: for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 5; j++) {
				System.out.println("i = " + i + ", j = " + j);

				if (j == 3)
					break loop;
			}
		}
		System.out.println("=====================================");

	}
}
