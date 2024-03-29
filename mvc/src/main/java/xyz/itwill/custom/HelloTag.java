package xyz.itwill.custom;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

// 커스텀 태그(Custom Tag): JSP 문서에서 스크립트 요소 대신 사용하기 위하여 프로그래머가 생성한 태그
// ▶ 태그 클래스 작성 ▶ TLD 파일에 커스텀 태그 등록 ▶ JSP 문서에서 커스텀 태그 사용

// 태그 클래스: JSP 문서에서 커스텀 태그를 사용할 경우 호출될 메소드가 선언된 클래스
// ▶ TagSupport / BodyTagSupport / SimpleTagSupoort 클래스 중 하나를 상속받아 작성
// ▶ 커스텀 태그 사용 시 호출되는 메소드는 부모 클래스의 메소드를 오버라이드 선언하여 작성

// 태그 속성과 태그 내용이 없는 커스텀 태그의 클래스 작성!
public class HelloTag extends TagSupport {
  private static final long serialVersionUID = 1L;

  // JSP 문서에서 커스텀 태그를 최초로 사용할 경우 태그 클래스를 객체로 생성하기 위하여 한번만 호출
  public HelloTag() {
    System.out.println("HelloTag 클래스의 기본 생성자 호출 - 객체가 생성되었습니다.");
  }

  // JSP 문서에서 커스텀 태그의 시작 태그를 사용할 때마다 호출되는 메소드
  @Override
  public int doStartTag() throws JspException {
    // System.out.println("HelloTag 클래스의 doStartTag() 메소드 호출");

    try {
      // 부모 클래스(TagSupport)로부터 제공받은 PageContext 객체(pageContext 필드에 존재)를 이용하여 웹 프로그램 작성에 필요한 객체를 반환받아 명령 작성
      // pageContext.getOut(): 응답 문서를 생성하기 위한 출력 스트림(JspWriter 객체)을 반환하는 메소드
      pageContext.getOut().println("<div>안녕하세요!!!</div>");
    } catch (IOException e) {
      e.printStackTrace();
    }

    // doStartTag() 메소드의 반환값(정수값): 부모 클래스에서 제공되는 상수 사용
    // ▶ SKIP_BODY / EVAL_BODY_INCLUDE 중 하나를 속성값으로 선택하여 반환
    // ▶▶ SKIP_BODY: 태그 내용을 클라이언트에게 전달하지 않을 경우 사용하는 상수(기본값)
    // ▶▶ EVAL_BODY_INCLUDE: 태그 내용을 클라이언트에게 전달할 경우 사용하는 상수
    // return super.doStartTag();
    return SKIP_BODY;
  }

  // JSP 문서에서 커스텀 태그의 태그 내용을 사용할 때마다 호출되는 메소드
  @Override
  public int doAfterBody() throws JspException {
    // System.out.println("HelloTag 클래스의 doAfterBody() 메소드 호출");

    // doAfterTag() 메소드의 반환값(정수값): 부모 클래스에서 제공되는 상수 사용
    // ▶ SKIP_BODY / EVAL_BODY_AGAIN 중 하나를 속성값으로 선택하여 반환
    // ▶▶ SKIP_BODY: 태그 내용을 다시 전달하지 않을 경우 사용하는 상수(기본값)
    // ▶▶ EVAL_BODY_AGAIN: 태그 내용을 클라이언트에게 다시 전달할 경우 사용하는 상수
    // return super.doAfterTag();
    return SKIP_BODY;
  }

  // JSP 문서에서 커스텀 태그의 종료 태그를 사용할 때마다 호출되는 메소드
  @Override
  public int doEndTag() throws JspException {
    // System.out.println("HelloTag 클래스의 doEndTag() 메소드 호출");

    // doEndTag() 메소드의 반환값(정수값): 부모 클래스에서 제공되는 상수 사용
    // ▶ SKIP_PAGE / EVAL_PAGE 중 하나를 속성값으로 선택하여 반환
    // ▶▶ SKIP_PAGE: 종료 태그 실행 후 JSP 문서를 강제로 종료될 경우 사용하는 함수
    // ▶▶ EVAL_PAGE: 종료 태그 실행 후 JSP 문서를 계속 실행할 경우 사용하는 함수(기본값)
    // return super.doEndTag();
    return EVAL_PAGE;
  }
}
