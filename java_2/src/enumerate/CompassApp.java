package enumerate;

public class CompassApp {
	public static void main(String[] args) {
		System.out.println("동 = " + Compass.EAST);
		System.out.println("서 = " + Compass.WEST);
		System.out.println("남 = " + Compass.SOUTH);
		System.out.println("북 = " + Compass.NORTH);
		System.out.println("====================================================");
		System.out.println("동쪽 = " + Compass.EAST.getValue());
		System.out.println("서쪽 = " + Compass.WEST.getValue());
		System.out.println("남쪽 = " + Compass.SOUTH.getValue());
		System.out.println("북쪽 = " + Compass.NORTH.getValue());
		System.out.println("====================================================");
		for (Compass compass : Compass.values()) {
			System.out.println(compass + " = " + compass.getValue() + " >> " + compass.ordinal());
		}
		System.out.println("====================================================");

	}
}
