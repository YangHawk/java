package realization;

public class WolfHuman extends Human implements Wolf {

	@Override
	public void fastWalk() {
		System.out.println("[늑대]네 발로 빠르게 달릴 수 있는 능력");
	}

	@Override
	public void cryLoudly() {
		System.out.println("[늑대]큰 소리로 울부 짖을 수 있는 능력");
	}

	public void change() {
		System.out.println("[늑대인간]변신할 수 있는 능력");
	}
}
