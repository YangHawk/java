package example;

public class OperatorExample {
	public static void main(String[] args) {
		// 245678초를 일시분초 형식으로 변환하여 출력하세요.
		int day = 245678 / 86400, last1 = 245678 % 86400;
		int hour = last1 / 3600, last2 = last1 % 3600;
		int min = last2 / 60, last3 = last2 % 60;
		int sec = last3;
		System.out.println(day + " 일 " + hour + " 시간 " + min + " 분 " + sec + " 초 ");

		int sec2 = 245678;
		int day2 = sec2 / (24 * 60 * 60);
		sec2 %= (24 * 60 * 60);
		int hour2 = sec2 / (60 * 60);
		sec2 %= (60 * 60);
		int min2 = sec2 / 60;
		sec2 %= 60;
		System.out.println(day2 + " 일 " + hour2 + " 시간 " + min2 + " 분 " + sec2 + " 초 ");

		System.out.println("===============================================");

		// 한대의 가격이 1억 5천원만인 비행기를 20대 구매할 경우 지불해야될 금액을 계산하여 출력하세요.
		// 단, 15대 이상 구매할 경우 1대당 25%의 할인율을 적용하여 계산하세요.
		int price = 150_000_000, each = 20;
		System.out.println((long) ((each >= 15 ? price * 0.75 : price) * each) + "원");
		System.out.println((each >= 15 ? ((long) price * each * 0.75) : (long) price * each) + "원");
		//단항연산식이 산술연산식보다 먼저 되기 때문에 long은 price에밖에 적용될 수 없다.
		System.out.println((long) (each >= 15 ? (price * each * 0.75) : price * each) + "원");
		
		long totPrice = (long) ((each >= 15 ? price * 0.75 : price) * each);
		System.out.println((long) totPrice + "원");
		totPrice = (long) (each >= 15 ? (price * each * 0.75) : price * each);
		//price*each를 하면서 이미 30억(21억보다 큰 숫자, integer로 표현될 수 없기 때문에 음수값이 결과로 나오는 것이다.
		System.out.println((long) totPrice + "원");
		System.out.println("===============================================");

	}
}