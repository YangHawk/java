package oop;

public class CarApp {
	public static void main(String[] args) {
		Car carOne = new Car();
		Car carTwo = new Car();
		Car carThree = new Car();

		System.out.println("carOne + " + carOne);
		System.out.println("carTwo + " + carTwo);
		System.out.println("carThree + " + carThree);
		System.out.println("======================================================================");
		//carOne.modelName = "싼타페";
		carOne.setModelName("싼타페");
		System.out.println("첫 번째 자동차 모델명 = " + carOne.getModelName());
		System.out.println("첫 번째 자동차 엔진상태 = " + carOne.isEngineStatus());
		System.out.println("첫 번째 자동차 현재속도 = " + carOne.getCurrentSpeed());
		System.out.println("======================================================================");
		carTwo.setModelName("소나타");
		carTwo.setEngineStatus(true);
		carTwo.setCurrentSpeed(80);
		System.out.println("두 번째 자동차 모델명 = " + carTwo.getModelName());
		System.out.println("두 번째 자동차 엔진상태 = " + carTwo.isEngineStatus());
		System.out.println("두 번째 자동차 현재속도 = " + carTwo.getCurrentSpeed());
		System.out.println("======================================================================");
		carOne.startEngine();
		carOne.speedUp(50);
		carOne.speedDown(30);
		carOne.speedZero();
		carOne.stopEngine();
		System.out.println("======================================================================");
		
	}
}
