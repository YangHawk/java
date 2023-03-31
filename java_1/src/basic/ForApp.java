package basic;

public class ForApp {
	public static void main(String[] args) {
		for (int i = 1; i <= 5; i++) {
			System.out.println("Java");
		}
		System.out.println("============================================");
		for (int i = 5; i >= 1; i--) {
			System.out.println("Programming");
		}
		System.out.println("============================================");
		for (int i = 2; i <= 10; i += 2) {
			System.out.println("Java2");
		}
		System.out.println("============================================");
		for (int i = 1; i <= 5; i++) {
			System.out.print(i + "\t");
		}
		System.out.println();
		System.out.println("============================================");
		for (int i = 1; i <= 5; i++) {
			System.out.println(i + "\n");
		}
		System.out.println("============================================");
		for (int i = 2; i <= 10; i += 2) {
			System.out.print(i + "\t");
		}
		System.out.println();
		System.out.println("============================================");
		for (int i = 1; i <= 10; i++) {
			if (i % 2 == 0) {
				System.out.print(i + "\t");
			} else {
				System.out.print("X" + "\t");
			}
		}
		System.out.println();
		System.out.println("============================================");
		int tot = 0;
		for (int i = 1; i <= 100; i++) {
			tot += i;
		}
		System.out.println("1~100 범위의 정수들의 합계 = " + tot);
		System.out.println("============================================");
		int begin = 90, end = 40;
		if (begin > end) {
			int temp=begin;
			begin=end;
			end=temp;
			//System.out.println("[에러]시작값이 종료값보다 작아야 됩니다.");
			//System.exit(0);
		}
		int sum = 0;
		for (int i = begin; i <= end; i++) {
			sum += i;
		}
		System.out.println(begin + "~" + end + " 범위의 정수들의 합계 = " + sum);
		System.out.println("============================================");
	}
}
