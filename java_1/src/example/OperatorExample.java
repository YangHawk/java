package example;

public class OperatorExample {
	public static void main(String[] args) {
		// 245678초를 일시분초 형식으로 변환하여 출력하세요.
		int day = 245678 / 86400, last1 = 245678 % 86400;
		int hour = last1 / 3600, last2 = last1 % 3600;
		int min = last2 / 60, last3 = last2 % 60;
		int sec = last3;
		System.out.println(day + " 일 " + hour + " 시간 " + min + " 분 " + sec + " 초 ");
		System.out.println("===============================================");
		// 한대의 가격이 1억 5천원만인 비행기를 20대 구매할 경우 지불해야될 금액을 계산하여 출력하세요.
		// 단, 15대 이상 구매할 경우 1대당 25%의 할인율을 적용하여 계산하세요.
		int price = 150000000;
		double price2 = 1.5;
		int number = 20;
		System.out.println("할인된 가격" + (number >= 15 ? ((long)price * number) * (0.75) : (long) price * number) + "원");
		System.out.println("할인된 가격" + (number >= 15 ? (price2 * number) * (0.75) : price2 * number) + "억 원");
		

		System.out.println("===============================================");
	}
}