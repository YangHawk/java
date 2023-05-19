package xyz.itwill.team05;

/*
 
DESC STUDENT;

이름      널?       유형            
------- -------- ------------- 
NO      NOT NULL NUMBER        
NAME             VARCHAR2(50)  
EMAIL            VARCHAR2(50)  
PHONE            VARCHAR2(20)  
ADDRESS          VARCHAR2(100) 

 
 */

public class StudentDTO {

	private int no;
	private String name;
	private String email;
	private String phone;
	private String address;

	public StudentDTO() {
		// TODO Auto-generated constructor stub
	}

	public StudentDTO(int no, String name, String email, String phone, String address) {
		super();
		this.no = no;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
@Override
public String toString() {
	return no + "\t" + name + "\t" + phone + "\t" + address + "\t" + email;
	
}
}
