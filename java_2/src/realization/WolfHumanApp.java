package realization;

public class WolfHumanApp {
	public static void main(String[] args) {
		WolfHuman wolfHuman = new WolfHuman();

		wolfHuman.speak();
		wolfHuman.walk();
		wolfHuman.smile();
		wolfHuman.change();
		wolfHuman.fastWalk();
		wolfHuman.cryLoudly();
		System.out.println("====================================================");

		Human human = new WolfHuman();

		human.speak();
		human.walk();
		human.smile();
		System.out.println("====================================================");

		((WolfHuman) human).change();
		System.out.println("====================================================");

		// Wolf wolf = new WolfHuman(); ← 새로운 객체를 만드는 것이므로,
		Wolf wolf = (Wolf) human;
		wolf.fastWalk();
		wolf.cryLoudly();
		System.out.println("====================================================");

	}
}
