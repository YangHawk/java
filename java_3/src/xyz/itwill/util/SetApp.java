package xyz.itwill.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetApp {
	public static void main(String[] args) {

		// HashSet set = new HashSet(); 이거 X
		// HashSet<String> set = new HashSet<String>();
		Set<String> set = new HashSet<String>();

		set.add("홍길동");
		set.add("임꺽정");
		set.add("전우치");

		// System.out.println("set.toString() = " + set.toString());
		System.out.println("set = " + set);
		System.out.println("=============================================================");

		set.add("홍길동");
		System.out.println("set = " + set);
		System.out.println("=============================================================");

		System.out.println("요소의 갯수 = " + set.size());
		System.out.println("=============================================================");

		set.remove("임꺽정");
		System.out.println("요소의 갯수 = " + set.size());
		System.out.println("=============================================================");

		Iterator<String> iterator = set.iterator();

		while (iterator.hasNext()) {
			String str = iterator.next();
			System.out.print(str + " ");
		}
		System.out.println();
		System.out.println("=============================================================");

		for (String str : set) {
			System.out.print(str + " ");
		}
		System.out.println();
		System.out.println("=============================================================");

	}
}
