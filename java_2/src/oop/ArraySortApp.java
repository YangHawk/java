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
		// Arrays 클래스: 배열의 요소를 제어하는 기능의 메소드를 제공하는 클래스
		//Arrays.toString(Object[] array): 배열을 전달받아 배열의 모든 요소값들을 문자열로 변환하여 반환하는 메소드

		Arrays.sort(array);//Arrays.sort(Object[] array): 배열을 전달받아 배열의 모든 요소값들을 오름차순 정렬하는 메소드
		
		System.out.println("정렬 후 >> " + Arrays.toString(array));

		System.out.println("=======================================================================");
	}
}
