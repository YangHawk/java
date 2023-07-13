package xyz.itwill.custom;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

// 태그 속성은 존재하고 태그 내용이 없는 커스텀 태그의 클래스 작성!
public class HelloMessageTag extends TagSupport {
  private static final long serialVersionUID = 1L;

  // 커스텀 태그의 속성값을 저장하기 위한 필드
  // ▶ 커스텀 태그의 속성명과 같은 이름으로 필드 선언
  private String name;

  /*
  
  custom.tld 의 required 엘리먼트를 true로 하였으므로 이 부분 필요 없음
  
  // 생성자에서 객체 생성에 필요한 초기화 작업 관련 명령 작성 - 필드 초기값 설정
  public HelloMessageTag() {
    // 커스텀 태그 사용 시 속성을 생략할 경우 기본적으로 사용될 속성값으로 필드에 저장하기 위하여 사용
    // ▶ 커스텀 태그의 속성이 필수인 경우 필드 기본값 설정 생략
    name = "홍길동";
  }
  
  */

  public String getName() {
    return name;
  }

  // 커스텀 태그 사용 시 속성을 사용하여 속성값을 설정할 경우 Setter 메소드가 자동으로 호출
  public void setName(String name) { // 매개변수로 속성값을 전달받아
    this.name = name; // 필드값을 변경
  }

  // 커스텀 태그 사용 시 실행될 명령을 작성할 메소드만 오버라이드 선언
  // ▶ 오버라이드 선언하지 않은 메소드는 부모 클래스의 명령이 없는 메소드 호출
  @Override
  public int doStartTag() throws JspException {
    try {
      if (name.equals("홍길동")) {
        pageContext.getOut().println("<h3>관리자님, 안녕하세요.</h3>");
      } else {
        pageContext.getOut().println("<h3>" + name + "님, 안녕하세요.</h3>");
      }
    } catch (IOException e) {
      throw new JspException(e.getMessage()); // 브라우저로 예외를 전달
    }
    
    return SKIP_BODY;
  }

}
