package example;

public class ForExample {
	public static void main(String[] args) {
		// 본인 이름을 화면에 7번 출력하세요.
		// ex) 홍길동 홍길동 홍길동 홍길동 홍길동 홍길동 홍길동

		for (int i = 1; i <= 7; i++) {
			System.out.print("홍길동" + "\t");
		}
		System.out.println();
		System.out.println("=========================================");
		// 1~10 범위의 정수 중 홀수를 화면에 출력하세요.
		// ex) 1 3 5 7 9

		for (int i = 1; i <= 10; i += 2) {// i <=9; 또한 상관없다.
			System.out.print(i + "\t");
		}
		System.out.println();

		for (int i = 1; i <= 10; i++) {// 둘 중 편한 것을 사용!
			if (i % 2 != 0) {
				System.out.print(i + "\t");
			}
		}
		System.out.println();
		System.out.println("=========================================");
		// 1~100 범위의 정수에서 3의 배수들의 합계와 5의 배수들의 합계를 계산하여
		// 3의 배수의 합계에서 5의 배수의 합계를 뺀 결과값을 출력하세요.
		// ex) 결과 = 633
		int tot3 = 0, tot5 = 0;
		/*
		 * 내가 푼 방법.....(깔끔하지 못하다!) for (int i = 1; i <= 100; i++) { if (i % 3 == 0) tot3
		 * += i; } for (int i = 1; i <= 100; i++) { if (i % 5 == 0) { tot5 += i; } }
		 * System.out.println(tot3 - tot5);
		 */
		// 명령이 단일일 때는 블럭 생략 가능, 또한 for 문도 동일하므로 하나만 사용해도 된다!
		for (int i = 1; i <= 100; i++) {
			if (i % 3 == 0)
				tot3 += i;
			if (i % 5 == 0)
				tot5 += i;
		}
		System.out.println("결과 = " + (tot3 - tot5));
		System.out.println("=========================================");
		// 구구단 중 7단을 화면에 출력하세요.
		// ex) 7 * 1 = 7
		// 7 * 2 = 14
		// ...
		// 7 * 8 = 56
		// 7 * 9 = 63
		int num = 7;
		for (int i = 1; i <= 9; i++) {
			System.out.println(num + " * " + i + " = " + num * i);

		}
		System.out.println("=========================================");
		// 5!의 결과값을 출력하세요.(5! = 5 * 4 * 3 * 2 * 1)
		// ex) 5! = 120
		int sum1 = 1, sum2 = 1;// 중요한 것, *할 때에는 초기값을 0으로 주면 안된다!!!!
		for (int i = 1; i <= 5; i++) {
			sum1 = i;
			sum2 *= i;
		}
		System.out.println(sum1 + "!" + " = " + sum2);

		sum1 = 5;
		sum2 = 1;
		for (int i = sum1; i >= 1; i--) {
			sum2 *= i;
		}
		System.out.println(sum1 + "! = " + sum2);

		System.out.println("=========================================");
		// 두 변수에 저장된 정수값 사이의 정수들을 화면에 출력하세요.
		// 단, 한 줄에 정수값이 7개씩 출력되도록 프로그램을 작성하세요.
		// ex) 36 37 38 39 40 41 42
		// 43 44 45 46 47 48 49
		// 50 51 52 53 54 55 56
		// 57
		int begin = 36, end = 57;
		for (int i = begin; i <= end; i++) {
			if ((i - begin) % 7 != 6) {
				System.out.print(i + "\t");
			} else {
				System.out.println(i + "\t");
			}
		}
		System.out.println();
		System.out.println("=========================================");
		begin = 36;
		end = 57;
		int count = 0;

		for (int i = begin; i <= end; i++) {
			System.out.print(i + "\t");
			count++;
			if (count % 7 == 0) {
				System.out.println();
			}
		}//변수 하나를 지정해줌으로써 출력갯수를 셀 수 있어 자신이 원하는 타이밍에 밑으로 내릴 수 있다.
		System.out.println();
		System.out.println("=========================================");
	}
}
