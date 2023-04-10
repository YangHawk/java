package oop;

public class RuntimeApp {
	public static void main(String[] args) {
		// Runtime runtime=new Runtime();
		Runtime runtime1 = Runtime.getRuntime();
		Runtime runtime2 = Runtime.getRuntime();
		System.out.println("runtime1 = " + runtime1);
		System.out.println("runtime2 = " + runtime2);
		System.out.println("=========================================================================");
		System.out.print("메모리 정리 전 JVM 사용 메모리 크기 >>");
		System.out.println(runtime1.totalMemory() - runtime1.freeMemory() + "Byte");
		runtime1.gc();
		System.out.print("메모리 정리 후 JVM 사용 메모리 크기 >>");
		System.out.println(runtime1.totalMemory() - runtime1.freeMemory() + "Byte");
		System.out.println("=========================================================================");

		System.out.println(Math.PI);
	}

}
