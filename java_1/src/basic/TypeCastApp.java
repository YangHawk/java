package basic;

public class TypeCastApp {
	public static void main(String[] args) {
		
		System.out.println("결과 = "+(3+1.5));
		
		double su=10;
		
		System.out.println("su = "+su);
		System.out.println("결과 = "+(35/10));
		System.out.println("결과 = "+(35.0/10.0));
		System.out.println("결과 = "+(35.0/10));
		System.out.println("결과 = "+(35/10.0));
		
		int kor=95, eng=90;
		int tot=kor+eng;
		double ave=tot/2;
		System.out.println("총점 = "+tot+", 평균 = "+ave);
		
		double ave2=tot/2.0;
		System.out.println("총점 = "+tot+", 평균 = "+ave2);
		
		byte su1=10, su2=20;
		int su3=su1+su2;
		System.out.println("su3 = "+su3);
		
		int num=(int)12.3;
		System.out.println("num = "+num);
		
		int num1=95, num2=10;
		double num3=num1/num2;
		System.out.println("num3 = "+num3);
		double num4=(double)num1/num2;
		System.out.println("num4 = "+num4);
		double num5=num1/(double)num2;
		System.out.println("num5 = "+num5);
		double num6=(double)num1/(double)num2;
		System.out.println("num6 = "+num6);
		
		int num7=100_000_000, num8=30;
		int num9=num7*num8;
		System.out.println("num9 = "+num9);
		long num10=(long)num7*num8;
		System.out.println("num10 = "+num10);
		
		double number=1.23456789;
		System.out.println("number = "+number);
		System.out.println("number(내림) = "+(int)(number*100)/100.0);
		System.out.println("number(반올림) = "+(int)(number*100+0.5)/100.0);
		System.out.println("number(올림) = "+(int)(number*100+0.9)/100.0);
		
	}
}
