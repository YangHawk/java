package xyz.itwill.exception;

// 회원 정보에 대한 변경 / 삭제 / 검색을 할 때 사용자로부터 전달받은 아이디의 회원 정보가 없는 경우 발생될 예외를 처리하기 위한 예외 클래스
public class UserinfoNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public UserinfoNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public UserinfoNotFoundException(String message) {
		super(message);
	}

}