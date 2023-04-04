package basic;

public class ArrayApp {

	public static void main(String[] args) {
		int[] num = new int[3];
		System.out.println("num = " + num);
		System.out.println("=========================================================================");
		System.out.println("num[0] = " + num[0]);
		System.out.println("num[3] = " + num[1]);
		System.out.println("num[2] = " + num[2]);
		System.out.println("=========================================================================");
		System.out.println("num.length = " + num.length);
		System.out.println("=========================================================================");
		int index = 0;
		num[index] = 10;
		num[index + 1] = 10;
		for (int i = 0; i < num.length; i++) {
			System.out.println("num[" + i + "] = " + num[i]);
		}
		System.out.println("=========================================================================");
		int[] su1 = new int[] { 10, 20, 30 };
		for (int i = 0; i < su1.length; i++) {
			System.out.println("su1[" + i + "] = " + su1[i]);
		}
		System.out.println("=========================================================================");
		int[] su2 = { 10, 20, 30 };
		for (int i = 0; i < su1.length; i++) {
			System.out.println("su[" + i + "] = " + su2[i]);
		}
		System.out.println("=========================================================================");
		for (int temp : su2) {
			System.out.print(temp + "\t");
		}
		System.out.println();
		System.out.println("=========================================================================");
		int[] array = { 54, 70, 64, 87, 96, 21, 65, 76, 11, 34, 56, 41, 77, 80 };

		int tot = 0;

		for (int i = 0; i < array.length; i++) {
			tot += array[i];
		}
		System.out.println("합계 = " + tot);

		System.out.println("=========================================================================");
		int count = 0;
		for (int element : array) {
			if (element >= 30 && element <= 60) {
				count++;
			}
		}
		System.out.println("30~60 범위의 요소의 갯수 = " + count);
		System.out.println("=========================================================================");

	}
}
