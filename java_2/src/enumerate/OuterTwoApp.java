package enumerate;

import enumerate.OuterTwo.InnerTwo;

public class OuterTwoApp {
	public static void main(String[] args) {
		OuterTwo outerTwo = new OuterTwo(100);

		outerTwo.outerDisplay();
		System.out.println("====================================================");
		InnerTwo innerTwo = new InnerTwo(200);
		innerTwo.innerDisplay();
		System.out.println("====================================================");
	}
}
