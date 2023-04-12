package realization;

public class BoatCarRealApp {
	public static void main(String[] args) {
		BoatCarReal boatCarReal = new BoatCarReal();
		BoatCar boatCar = new BoatCarReal();
		Boat boatCar2 = new BoatCarReal();
		Car boatCar3 = new BoatCarReal();
		
		boatCar.run();
		boatCar.navigate();
		boatCar.floating();
		System.out.println("====================================================");
	}
}
