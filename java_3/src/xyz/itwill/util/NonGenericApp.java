package xyz.itwill.util;

public class NonGenericApp {
	public static void main(String[] args) {
		NonGeneric nonGeneric1 = new NonGeneric();

		nonGeneric1.setField(100);

		Integer returnObject1 = (Integer) nonGeneric1.getField();

		System.out.println("필드값 = " + returnObject1);
		System.out.println("=============================================================");

		NonGeneric nonGeneric2 = new NonGeneric();

		nonGeneric2.setField(12.34);

		if (nonGeneric2.getField() instanceof Double) {

			// Integer returnObject2 = (Integer) nonGeneric2.getField();
			Double returnObject2 = (Double) nonGeneric2.getField();

			System.out.println("필드값 = " + returnObject2);
		}
		System.out.println("=============================================================");
		NonGeneric nonGeneric3 = new NonGeneric();

		nonGeneric3.setField("홍길좆");

		String returnObject3 = (String) nonGeneric3.getField();

		System.out.println("필드값 = " + returnObject3);
		System.out.println("=============================================================");
		
		
		
		
		
	}
}
