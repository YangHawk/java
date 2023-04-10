package association;

public class Engine {
	private String fuelType;
	private int displacement;

	public Engine() {
		// TODO Auto-generated constructor stub
	}

	public Engine(String fuelType, int displacement) {
		super();
		this.fuelType = fuelType;
		this.displacement = displacement;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public int getDisplacement() {
		return displacement;
	}

	public void setDisplacement(int displacement) {
		this.displacement = displacement;
	}

	public void displayEngine() {
		System.out.println("연료타입 = " + fuelType);
		System.out.println("배기량 = " + displacement);
	}
}
