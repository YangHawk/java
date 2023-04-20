package xyz.itwill.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapApp {
	public static void main(String[] args) {

		Map<String, String> map = new HashMap<String, String>();

		map.put("1000", "홍길동");
		map.put("2000", "임꺽정");
		map.put("3000", "전우치");
		map.put("4000", "일지매");
		map.put("5000", "장길산");

		// System.out.println("map.toString = "+map.toString());
		System.out.println("map = " + map);
		System.out.println("=============================================================");
		map.put("2000", "임꺽좆"); // 변경! Key 가 같아!
		map.put("2500", "임꺽좆"); // 추가! Key 가 달라!
		System.out.println("map = " + map);
		System.out.println("=============================================================");
		map.remove("4000");
		map.remove("2500");
		System.out.println("map = " + map);
		System.out.println("=============================================================");
		String name = map.get("1000"); // Value를 전달받아서 저장하는 것이다.
		System.out.println("name = " + name);
		System.out.println("=============================================================");
		Iterator<String> iteratorKey = map.keySet().iterator();

		while (iteratorKey.hasNext()) {
			String key = iteratorKey.next();
			String value = map.get(key);
			System.out.println(key + " = " + value);
		}
		System.out.println("=============================================================");
		for (String key : map.keySet()) {
			System.out.println(key + " = " + map.get(key));
		}
		System.out.println("=============================================================");
		Iterator<String> iteratorValue = map.values().iterator();
		while (iteratorValue.hasNext()) {
			String value = iteratorValue.next();
			System.out.println(value);
		}
		System.out.println("=============================================================");
		for (String value : map.values()) {
			System.out.println(value);
		}
	}
}
