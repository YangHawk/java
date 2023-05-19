package xyz.itwill.team05;


//로그인을 STUDENT 테이블에서 이메일이랑 전화번호 lastindexof 해서 마지막 4자리만
//전달받아 학생 또는 선생으로 로그인 할 수 있게 하는 메소드
	
public interface AccessDAO {

	//학생 정보를 전달받아 로그인 할 수 있게 하는 메소드
	//셀렉트(select from student where email=?, phone=?;
	
	StudentDTO login(String email, String phone);
	
	//학번을 전달받아 입실 버튼을 눌러 ALOG 테이블에 행을 삽입하는 메소드 입력
	
	
}
