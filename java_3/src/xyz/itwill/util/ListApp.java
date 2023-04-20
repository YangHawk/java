package xyz.itwill.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ListApp {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();

		list.add("홍길동");
		list.add("임꺽정");
		list.add("전우치");
		list.add("일지매");

		// System.out.println("list.toString() = " + list.toString());
		System.out.println("list = " + list);
		System.out.println("=============================================================");

		list.add("임꺽정");
		System.out.println("list = " + list);
		System.out.println("=============================================================");

		System.out.println("요소의 갯수 = " + list.size());
		System.out.println("=============================================================");

		System.out.println("3번째 위치에 저장된 문자열 = " + list.get(2));
		System.out.println("=============================================================");

		list.add(4, "장길산");
		System.out.println("list = " + list);
		System.out.println("=============================================================");

		// list.remove("임꺽정");
		// System.out.println("list = " + list);

		list.remove(5);
		System.out.println("list = " + list);
		System.out.println("=============================================================");
		list.set(1, "임걱정");
		System.out.println("list = " + list);
		System.out.println("=============================================================");

		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println();
		System.out.println("=============================================================");

		Iterator<String> iterator = list.iterator();

		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}
		System.out.println();
		System.out.println("=============================================================");

		for (String str : list) {
			System.out.print(str + " ");
		}
		System.out.println();
		System.out.println("=============================================================");

		Collections.sort(list);
		System.out.println("list = " + list);
		System.out.println("=============================================================");

		list.clear();

		if (list.isEmpty()) {
			System.out.println("List 객체에 저장된 요소가 하나도 없습니다.");
		}
		System.out.println("=============================================================");

		List<Integer> numberList = Arrays.asList(10, 20, 30, 40, 50);
		System.out.println("number =" + numberList);
		System.out.println("=============================================================");
	}

}
