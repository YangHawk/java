package oop;

public class Member {
	private String id;
	private String name;
	private String email;

	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(String id) {
		super();
		this.id = id;
	}

	public Member(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Member(String id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void display() {
		System.out.println("아이디 = " + id);
		System.out.println("이름 = " + name);
		System.out.println("이메일 = " + email);
	}

}
