package basic;

public class WhileApp {
	public static void main(String[] args) {

		int i = 1;
		while (i <= 5) {
			System.out.println("Java Programming");
			i++;
		} // for문과 비슷하지만, 조건식이 내부에 있지 않다..!
		System.out.println("============================================");

		int j = 1, tot = 0;
		do {
			tot += j;
			j++;
		} while (j <= 100);// ; 기호 위치 주의!
		System.out.println("1~100 범위의 정수들의 합계 = " + tot);
		System.out.println("============================================");
		// A4 용지를 계속 반으로 접어 펼쳤을 경우 사각형 모양의 갯수가 500개 이상이 만들어지려면....

		int cnt = 0, gae = 1; // cnt: 접는 횟수, gae: 사각형 모양의 갯수
		while (gae < 500) {// A4 용지를 반으로 계속 접기 위한 행위를 제공하는 반복문
			cnt++;
			gae *= 2;
		}
		System.out.println(cnt + "번 접으면 " + gae + "개의 사각형이 만들어진다.");
		System.out.println("============================================");
		// 1~X 범위의 정수들의 합계가 300 이상이 만들어지려면...X가 얼마인지 계산하시오!
		int number = 0, total = 0; // 위치 굉장히 중요! number와 total 명령 순서가 바뀔 시 25까지 더해져 잘못된 결과!
		do {
			number++;
			total += number;
		} while (total < 300);
		System.out.println(number + "까지 더하면 " + total + "이란 값이 출력된다.");
		System.out.println("============================================");
		int x = 0, sum = 0; // x: 1씩 증가되는 정수값, sum: 누적 결과(합계)
		while (sum < 300) {
			x++;
			sum += x;
		}
		System.out.println("1~" + x + "범위의 정수들의 합계 =" + sum);
		System.out.println("============================================");
	}
}