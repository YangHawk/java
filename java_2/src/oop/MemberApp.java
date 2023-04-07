package oop;

public class MemberApp {
	public static void main(String[] args) {
		Member member = new Member();
		
		System.out.println("아이디 = " + member.getId());
		System.out.println("이름 = " + member.getName());
		System.out.println("이메일 = " + member.getEmail());
		System.out.println("====================================================================");

		member.setId("abc123");
		member.setName("홍길동");
		member.setEmail("abc@itwill.xyz");

		System.out.println("아이디 = " + member.getId());
		System.out.println("이름 = " + member.getName());
		System.out.println("이메일 = " + member.getEmail());
		System.out.println("====================================================================");

		member.display();
		System.out.println("====================================================================");
		Member member2 = new Member("def456");
		member2.display();
		System.out.println("====================================================================");
		Member member3 = new Member("ghj789", "임꺽정");
		member3.display();
		System.out.println("====================================================================");
		Member member4 = new Member("xyz258", "전우치", "xyz@itwill.xyz");
		member4.display();
		System.out.println("====================================================================");
		Member member5 = new Member();
		member5.display(); // 앞서 this() 키워드를 이용했으므로.....이해됐어! 시발!
	}
}
