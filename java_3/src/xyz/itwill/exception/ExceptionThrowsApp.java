package xyz.itwill.exception;

public class ExceptionThrowsApp {
	public static void display() throws ArrayIndexOutOfBoundsException {
		int[] array = { 10, 20, 30, 40, 50 };

		for (int i = 0; i <= array.length; i++) {
			System.out.println("array[" + i + "] = " + array[i]);
		}

	}

	public static void main(String[] args) {
		try {
			// ExceptionThrowapp.display();
			display();
		} catch (ArrayIndexOutOfBoundsException e) {
			
			System.out.println("[에러]프로그램에 예기치 못한 오류가 발생되었습니다.");
		}
		
	}
	
}
