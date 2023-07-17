package xyz.itwill.dto;

/*

이름      널?       유형            
------- -------- ------------- 
NO      NOT NULL NUMBER        
NAME             VARCHAR2(50)  
EMAIL            VARCHAR2(50)  
PHONE            VARCHAR2(20)  
ADDRESS          VARCHAR2(100) 

 */

// 테이블의 컬럼명과 같은 이름으로 필드명을 작성하여 클래스를 선언하는 것을 권장
// ▶ mybatis 프레임워크의 SqlSession 객체는 검색행의 컬럼값을 같은 이름의 필드에 자동 매핑되어 저장됨
public class Student {
  private int no;
  private String name;
  private String email;
  private String phone;
  private String address;

  public Student() {
    // TODO Auto-generated constructor stub
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
  
}
