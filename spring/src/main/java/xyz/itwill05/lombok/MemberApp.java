package xyz.itwill05.lombok;

public class MemberApp {
	public static void main(String[] args) {
		Member member1 = new Member("abc123", "홍길동", "abc123@naver.com");

		System.out.println("아이디 = " + member1.getId() + ", 이름 = " + member1.getName() + ", 이메일 = " + member1.getEamil());
		System.out.println("=======================================================================");
		// Member 클래스로 생성된 참조 변수를 출력할 경우 Member 클래스의 toString() 메소드 자동 호출
		System.out.println(member1);
		System.out.println("=======================================================================");
		// builder(): 클래스 내부에 작성된 Builder 클래스로 객체를 생성하여 반환하는 메소드
		// ▶ Builder 객체로 필드명과 같은 이름의 메소드를 호출하여 필드값 변경 - Builder 객체 반환: 계속 반환하기 때문에 . 연산자로 계속 메소드 호출할 수 있는 거임!
		// ▶ Builder.build(): 클래스로 객체를 생성하여 반환하는 메소드
		Member member2 = Member.builder()
				.id("xyz456")
				.name("임꺽정")
				.eamil("xyz456@naver.com")
				.build();
		System.out.println(member2);
		System.out.println("=======================================================================");
		
		
	}
}
