package association;

public class Car {
	private String modelName;
	private int productionYear;
	private Engine carEngine; //←이렇게 된다고 해서 포함관계가 되는 것이 아니다!

	public Car() {
		// TODO Auto-generated constructor stub
	}

	public Car(String modelName, int productionYear, Engine carEngine) {
		super();
		this.modelName = modelName;
		this.productionYear = productionYear;
		this.carEngine = carEngine;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public int getProductionYear() {
		return productionYear;
	}

	public void setProductionYear(int productionYear) {
		this.productionYear = productionYear;
	}

	public Engine getCarEngine() {
		return carEngine;
	}

	public void setCarEngine(Engine carEngine) {
		this.carEngine = carEngine;
	}

	public void displayCar() {
		System.out.println("모델명 = " + modelName);
		System.out.println("생산년도 = " + productionYear);
		
		//System.out.println("엔진정보 = " + carEngine);
		//필드에 저장된 객체를 이용하여 메소드 호출
		//→포함관계로 설정된 클래스의 메소드를 호출하여 원하는 기능을 구현할 수 있다
		//→포함관계가 설정되지 않은 상태에서 메소드가 호출될 경우 NullPointerException 발생!
		/*
		System.out.println("연료타입 = "+carEngine.getFuelType());
		System.out.println("배기량 = "+carEngine.getDisplacement());
		*/
		
		carEngine.displayEngine(); //←위와 같지만, 코드의 중복성을 최소화해보았다.
	}
}
