package inheritance;

public class MemberCastApp {
	public static void main(String[] args) {
		Member member1 = new Member();

		member1.setId("abc123");
		member1.setName("홍길동");

		member1.display();
		System.out.println("=============================================================");

		MemberEvent member2 = new MemberEvent();

		member2.setId("xyz789");
		member2.setName("임꺽정");
		member2.setEmail("xyz@itwill.xyz");

		member2.display();
		System.out.println("=============================================================");

		Member member3 = new MemberEvent();

		member3.setId("opq456");
		member3.setName("전우치");

		member3.display();
		System.out.println("=============================================================");

		MemberEvent event = (MemberEvent) member3;

		//event.setEmail("opq@itwill.xyz");
		member3.display();
		System.out.println("=============================================================");

		((MemberEvent) member3).setEmail("opq@itwill.xyz");
		
		((MemberEvent) member3).display();
		System.out.println("=============================================================");
		member3.display();
		System.out.println("=============================================================");
	
	
	
	
	
	
	}

}
