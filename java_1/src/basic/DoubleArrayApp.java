package basic;

public class DoubleArrayApp {
	public static void main(String[] args) {
		int[][] num = new int[2][3];
		System.out.println("num = " + num);
		System.out.println("num[0] = " + num[0]);
		System.out.println("num[1] = " + num[1]);
		System.out.println("===================================================================");
		System.out.println("num[0][0] = " + num[0][0]);
		System.out.println("num[0][1] = " + num[0][1]);
		System.out.println("num[0][2] = " + num[0][2]);
		System.out.println("===================================================================");
		System.out.println("num[1][0] = " + num[1][0]);
		System.out.println("num[1][1] = " + num[1][1]);
		System.out.println("num[1][2] = " + num[1][2]);
		System.out.println("===================================================================");
		System.out.println("num.length = " + num.length);
		System.out.println("num[0].length = " + num[0].length);
		System.out.println("num[1].length = " + num[1].length);
		System.out.println("===================================================================");
		for (int i = 0; i < num.length; i++) {
			for (int j = 0; j < num[i].length; j++) {
				System.out.print(num[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("===================================================================");
		int[][] su = new int[][] { { 10, 20, 30 }, { 40, 50, 60 }, { 70, 80, 90 } };
		for (int[] array : su) {
			for (int temp : array) {
				System.out.print(temp + "\t");
			}
			System.out.println();
		}
		System.out.println("===================================================================");
		int[][] value = new int[3][];
		System.out.println("value = " + value);
		System.out.println("value[0] = " + value[0]);
		System.out.println("value[1] = " + value[1]);
		System.out.println("value[2] = " + value[2]);
		System.out.println("===================================================================");
		value[0] = new int[3];
		value[1] = new int[2];
		value[2] = new int[4];
		System.out.println("value[0] = " + value[0]);
		System.out.println("value[1] = " + value[1]);
		System.out.println("value[2] = " + value[2]);
		System.out.println("===================================================================");
		int[][] array = new int[][] { { 10, 20, 30 }, { 40, 50 }, { 60, 70, 80, 90 } };
		for(int[] arr : array) {
			for(int temp : arr) {
				System.out.print(temp+"\t");
			}
			System.out.println();
		}
		System.out.println("===================================================================");
	}
}