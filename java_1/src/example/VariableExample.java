package example;

public class VariableExample {
	public static void main(String[] args) {
		// 가로의 길이가 7이고 세로의 길이가 10인 사각형의 넓이를 계산하여 출력하세요.
		int width = 7, depth = 10;
		int area1 = width * depth;
		System.out.println("넓이 = " + area1);
		System.out.println("======================================================");
		// 높이가 9이고 밑변의 길이가 7인 삼각형의 넓이를 계산하여 출력하세요.
		int height = 9, length = 7;
		double area2 = ((height * length) / 2.0);
		System.out.println("넓이 = " + area2);
		System.out.println("======================================================");
		// 10명의 전체 몸무게가 759Kg인 경우 평균 몸무게를 계산하여 출력하세요.
		int tot1 = 759, num1 = 10;
		double ave1 = (double) tot1 / num1;
		System.out.println("평균 몸무게 = " + ave1);
		System.out.println("======================================================");
		// 이름이 [홍길동]인 학생이 국어점수 89점, 영어점수 93점, 수학점수 95점을 받은 경우
		// 총점과 평균을 계산하여 이름, 총점, 평균을 출력하세요.
		// 단, 평균은 소숫점 한자리까지만 출력하고 나머지는 절삭 처리 하세요.
		int kor=89, eng=93, math=95, num2=3;
		int tot2=kor+eng+math;
		double ave2=tot2/(double)num2;
		System.out.println("홍길동 , "+"총점 = "+tot2+"평균 = "+(int)(ave2*10)/10.0);
		System.out.println("======================================================");
	}
}
