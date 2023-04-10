package inheritance;

public class MemberEvent extends Member {
	/*
	 * //부모클래스(Member)에세 상속받은 필드 또는 메소드 미선언 private String id; private String name;
	 */
	private String email;

	public MemberEvent() {

	}

	public MemberEvent(String id, String name, String email) {
		super(id, name);// 부모클래스의 매개변수가 있는 생성자 호출
		this.email = email;
	}

	/*
	 * public String getId() { return id; }
	 * 
	 * public void setId(String id) { this.id = id; }
	 * 
	 * public String getName() { return name; }
	 * 
	 * public void setName(String name) { this.name = name; }
	 */

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		super.display(); //→super. 은 부모클래스에 있는 display()에 접근합니다~라는 뜻!
		System.out.println("이메일 = " + email);
	}

}