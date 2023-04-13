package xyz.itwill.exception;

public class ExceptionHandleApp {
	public static void main(String[] args) {
		int[] array = { 10, 20, 30, 40, 50 };

		try {
			for (int i = 0; i <= array.length; i++) {
				System.out.println("array[" + i + "] = " + array[i]);
				System.out.println("====================================================");
			}
			System.out.println("[메세지]프로그램이 정상적으로 실행 되었습니다.");
		} catch (ArrayIndexOutOfBoundsException e) {
			// System.out.println("[에러]프로그램에 예기치 못한 오류가 발생하였습니다.");
			//System.out.println("[예외]" + e.getMessage());
			//e.printStackTrace();
			e.getStackTrace();
		} finally {
			System.out.println("[메세지]예외 발생과 상관없이 무조건 실행될 명령");
		}
	}
}