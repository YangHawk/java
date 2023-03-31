package basic;

public class LocalVariableApp {
	int number = 100;

	public static void main(String[] args) {
		int num1 = 100;
		{
			int num2 = 200;
			System.out.println("======= 임의블럭 내부 =======");
			System.out.println("num1 = " + num1 + ", num2 = " + num2);
		}
		System.out.println("======= 임의블럭 외부 =======");
		System.out.println("num1 = " + num1);
	}
}
