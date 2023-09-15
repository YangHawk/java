package xyz.itwill.exception;

//영화제 정보에 대한 변경/검색을 할 때 사용자로부터 전달받은 영화제 등록 번호의
//영화제 정보가 없는 경우 발생될 예외를 처리하기 위한 예외 클래스
public class FestivalinfoNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public FestivalinfoNotFoundException() {
		// TODO Auto-generated constructor stub
	}
	
	public FestivalinfoNotFoundException(String message) {
		super(message);
	}
	
}
