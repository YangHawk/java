package association;

public class CarApp {
	public static void main(String[] args) {
		Engine engine = new Engine();

		engine.setFuelType("경유");
		engine.setDisplacement(2000);

		engine.displayEngine();
		System.out.println("====================================================");
		Car carOne = new Car();

		// carOne.displayCar(); ← 밑에 setCarEngine(engine)을 안해주어 nullpointer..

		carOne.setModelName("소렌토");
		carOne.setProductionYear(2020);
		carOne.setCarEngine(engine);

		carOne.displayCar();
		System.out.println("====================================================");

		// 자동차 생성 >> 엔진을 포함하여 필드에 생성한 것이다!(엔진을 생성하여 필드에 저장!) = 이 또한 포함관계가 성립
		// →생성자를 호출하여 매개변수에 엔진정보(Engine 객체)를 전달받아 필드에 저장)
		Car carTwo = new Car("싼타페", 2023, new Engine("휘발유", 3000));
		carTwo.displayCar();
		System.out.println("====================================================");
		System.out.println(carOne.getModelName() + "의 엔진정보 >> ");
		engine.displayEngine();
		System.out.println("====================================================");
		System.out.println(carTwo.getModelName() + "의 엔진정보 >> ");
		//첫번쨰 자동차는 engine을 참조변수에 저장해 메소드를 호출할 수 있지만,
		//두번째 자동차는 engine이라는 객체가 필드에만 저장되어있고, 

		//따라서 두번째자동차한테 엔진을 달라고 하면 된다.
		//자동차(Car 객체)에 저장된 엔진정도(Engine 필드값 = Engine 객체)를 Getter메
		//소드로 반환받아 직접 메소드를 호출하는 것이다!
		//즉, 굳이 참조변수를 만들 필요가 없이 아래와 같이 사용하면 되는 것이다!
		carTwo.getCarEngine().displayEngine();
		System.out.println("====================================================");

	}
}
