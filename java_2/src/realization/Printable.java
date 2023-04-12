package realization;

public interface Printable {
	void print();
	default void scan() {
		System.out.println("[에러]스캔 기능을 제공하지 않습니다.");
	}
}
