package xyz.itwill.util;

public class GenericApp {
	public static void main(String[] args) {
		Generic<Integer> generic1 = new Generic<Integer>();

		// generic1.setField(12.34);
		generic1.setField(100);

		Integer returnObject1 = generic1.getField();

		System.out.println("필드값 = " + returnObject1);
		System.out.println("=============================================================");

		Generic<Double> generic2 = new Generic<Double>();

		generic2.setField(100.0);

		Double returnObject2 = generic2.getField();

		System.out.println("필드값 = " + returnObject2);
		System.out.println("=============================================================");
		
		/* Generic<凸 extends Number> 하였기 때문에 Number 클래스의 자식클래스만 사용 가능!
		Generic<String> generic3 = new Generic<String>();

		generic3.setField("홍길");

		String returnObject3 = generic3.getField();

		System.out.println("필드값 = " + returnObject3);
		System.out.println("=============================================================");
		*/
		
	}

}
