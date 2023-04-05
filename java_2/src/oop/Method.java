package oop;

public class Method {
	void displayOne() {
		System.out.println("Method 클래스의 displayOne() 메소드 호출");
	}

	void displayTwo() {
		System.out.println("Method 클래스의 displayTwo() 메소드 호출");
	}

	void printOne() {
		int tot = 0;
		for (int i = 1; i <= 100; i++) {
			tot += i;
		}
		System.out.println("1~100 범위 정수들의 합계 = " + tot);
	}

	void printTwo(int num) {
		if (num <= 0) {
			System.out.println("[에러]매개변수에는 0보다 큰 값을 전달해야 합니다.");
			return;
		}

		int tot = 0;
		for (int i = 1; i <= num; i++) {
			tot += i;
		}
		System.out.println("1~" + num + " 범위 정수들의 합계 = " + tot);
	}

	void printThree(int num1, int num2) {
		if (num1 > num2) {
			System.out.println("[에러]첫 번째 전달값이 두 번째 전달값보다 작아야 합니다.");
			return;
		}

		int tot = 0;
		for (int i = num1; i <= num2; i++) {
			tot += i;
		}
		System.out.println(num1 + "~" + num2 + " 범위 정수들의 합계 = " + tot);
	}

	void printFour(int num1, int num2) {
		if (num1 > num2) {
			int temp = num1;
			num1 = num2;
			num2 = temp;
		}
		int tot = 0;
		for (int i = num1; i <= num2; i++) {
			tot += i;
		}
		System.out.println(num1 + "~" + num2 + " 범위 정수들의 합계 = " + tot);
	}

	int returnTot(int num1, int num2) {
		int tot = 0;
		for (int i = num1; i <= num2; i++) {
			tot += i;
		}
		return tot;
	}

	boolean isOddEven(int num) {
		if (num % 2 != 0) {
			return false;
		} else {
			return true;
		}
	}

	int[] returnArray() {
		int[] array = { 10, 20, 30, 40, 50 };
		return array;
	}

	/*
	 * int[] returnArray() { return {10,20,30,40,50}; } → 에러 발생(배열이 아니기 때문!)
	 */

	// 사용하고 싶다면 → return new int[] {10,20,30,40,50}; 이렇게 써야 한다!

	int sumOne(int num1, int num2, int num3) {
		return num1 + num2 + num3;
	}

	int sumTwo(int[] array) {
		int tot = 0;
		for (int su : array) {
			tot += su;
		}
		return tot;
	}

	int sumThree(int... array) {
		int tot = 0;
		for (int su : array) {
			tot += su;
		}
		return tot;
	}
}
