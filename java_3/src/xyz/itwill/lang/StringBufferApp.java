package xyz.itwill.lang;

public class StringBufferApp {
	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer("ABC");

		System.out.println("sb.toString() = " + sb.toString());
		System.out.println("sb = " + sb);

		String str = sb.toString();
		str += "DEF";
		System.out.println("str = " + str);
		System.out.println("=============================================================");
		sb.append("DEF");
		System.out.println("sb = " + sb);
		System.out.println("=============================================================");
		sb.insert(4, "X");
		System.out.println("sb = " + sb);
		System.out.println("=============================================================");
		sb.deleteCharAt(2);
		System.out.println("sb = " + sb);
		System.out.println("=============================================================");
		sb.delete(4, 6);
		System.out.println("sb = " + sb);
		System.out.println("=============================================================");
		sb.reverse();
		System.out.println("sb = " + sb);
		System.out.println("=============================================================");

	}
}
