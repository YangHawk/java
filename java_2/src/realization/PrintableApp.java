package realization;

public class PrintableApp {
	public static void main(String[] args) {
		Printable printOne = new PrintSingle();

		printOne.print();
		printOne.scan();
		System.out.println("====================================================");
	
		Printable printTwo = new PrintMulti();
		
		printTwo.print();
		printTwo.scan();
		System.out.println("====================================================");
	}

}
