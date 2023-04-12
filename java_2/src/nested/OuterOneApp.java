package nested;

import nested.OuterOne.InnerOne;

public class OuterOneApp {
	public static void main(String[] args) {
		OuterOne outerOne = new OuterOne(100);
		outerOne.outerDisplay();
		System.out.println("====================================================");
		// InnerOne innerOne = new InnerOne(200);
		InnerOne innerOne = outerOne.new InnerOne(200);
		innerOne.innerDisplay();
		System.out.println("====================================================");
	}
}
