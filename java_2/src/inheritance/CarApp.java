package inheritance;

public class CarApp {
	public static void main(String[] args) {
		Car car = new Car("산타페", "홍길동");

		System.out.println("모델명 = " + car.getModelName());
		System.out.println("소유자 = " + car.getUserName());
		System.out.println("====================================================");

		System.out.println("car.toString() = " + car.toString());
		System.out.println("car.toString() = " + car);
		System.out.println("====================================================");
		String name = "홍길동";
		//Object.toString(); ← Object 클래스에 있는 toString 메소드!

		System.out.println("name = "+name.toString());
		
	
	}
}
