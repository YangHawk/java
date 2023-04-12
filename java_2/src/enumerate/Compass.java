package enumerate;

public enum Compass {
	EAST("동"), WEST("서"), SOUTH("남"), NORTH("북");

	// private final int value;
	private final String value;

	private Compass(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
