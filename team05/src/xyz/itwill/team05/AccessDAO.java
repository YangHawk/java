package xyz.itwill.team05;

public interface AccessDAO {

	// 입력한 ID와 비밀번호를 전달받아 선생 또는 학생으로 로그인할 수 있는 메소드
	StudentDTO login(String email, String phone);

}
