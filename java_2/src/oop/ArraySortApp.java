package oop;

import java.util.Arrays;

public class ArraySortApp {
	public static void main(String[] args) {
				
		int[] array = { 30, 50, 10, 40, 20 };

		System.out.print("정렬 전 >> ");
		for (int num : array) {
			System.out.print(num + " ");
		}
		System.out.println();

		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i] > array[j]) {
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}

		System.out.print("정렬 후 >> ");
		for (int num : array) {
			System.out.print(num + " ");
		}
		System.out.println();

		// 자바는 전형적인 객체 지향 프로그래밍 언어이다! 위에 방식은 절차 지향 프로그래밍!

		System.out.println("=======================================================================");

		array = new int[] { 30, 50, 10, 40, 20 };

		System.out.println("정렬 전 >> " + Arrays.toString(array));

		Arrays.sort(array);

		System.out.println("정렬 후 >> " + Arrays.toString(array));

		System.out.println("=======================================================================");
	}
}
