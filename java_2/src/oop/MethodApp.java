package oop;

public class MethodApp {
	public static void main(String[] args) {
		Method method = new Method();
		System.out.println("method = " + method);
		System.out.println("====================================================================");
		method.displayOne();
		method.displayTwo();
		method.displayOne();
		System.out.println("====================================================================");
		method.printOne();
		method.printTwo(0);
		method.printTwo(80);
		method.printThree(51, 51);
		method.printFour(20, 10);
		System.out.println("====================================================================");
		int result = method.returnTot(30, 70);
		System.out.println("합계(메소드 호출의 반환값) = " + result);
		System.out.println("합계(메소드 호출의 반환값) = " + method.returnTot(20, 80));
		System.out.println("====================================================================");
		boolean result1 = method.isOddEven(10);
		if (result1) {
			System.out.println("짝수");
		} else {
			System.out.println("홀수");
		}
		if (method.isOddEven(9)) {
			System.out.println("짝수");
		} else {
			System.out.println("홀수");
		}
		int[] array = method.returnArray();
		for (int num : array) {
			System.out.print(num + " ");
		}
		System.out.println();
		System.out.println("====================================================================");
		System.out.println("합계 = " + method.sumOne(10, 20, 30));
		System.out.println("합계 = " + method.sumTwo(new int[] {}));
		System.out.println("합계 = " + method.sumTwo(new int[] { 10, 20 }));
		System.out.println("합계 = " + method.sumTwo(new int[] { 10, 20, 30 }));
		System.out.println("합계 = " + method.sumThree());
		System.out.println("합계 = " + method.sumThree(10, 50));
		System.out.println("====================================================================");
	}

}
