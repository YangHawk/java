package xyz.itwill.mvc;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 컨트롤러(Controller - Servlet): 클라이언트의 모든 요청을 받아 모델(Model - Class) 역할의 객체로 요청 처리 메소드를
// 호출하여 클라이언트의 요청을 처리하고 처리 결과를 뷰(View - JSP)로 전달하여 응답되도록 제어하는 프로그램의 흐름을
// 제공하는 웹 프로그램

// 1. 클라이언트의 모든 요청을 받을 수 있도록 Servlet을 설정하여 단일 진입점의 기능이 구현되도록 작성
// ▶ Front Controller Pattern이라고 부름 - 하나의 웹 프로그램만이 클라이언트의 모든 요청을 받음
// @WebServlet("url"): Servlet 클래스를 웹 프로그램(Servlet)으로 등록하고 요청 URL 주소를 매핑 처리하기 위한 Annotation
// ▶ 매핑 설정될 URL 주소에 패턴 문자(*: 전체 / ?: 문자 하나)를 사용하여 URL Pattern 등록 가능
// ▶ @WebServlet("*.do"): 클라이언트가 [XXX.do] 형식의 URL 주소를 요청한 경우 웹 프로그램(Servlet)을 실행

// @WebServlet("*.do") - web.xml 에 생성!
// ▶ @WebServlet Annotation 대신 [web.xml] 파일의 엘리먼트를 사용하여 클래스를 웹 프로그램으로 등록하고 URL 매핑 처리 가능

public class ControllerServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  // 여러 개의 엔트리(Entry)를 추가할 수 있는 Map 객체를 저장하기 위한 필드
  // ▶ 요청 정보(Key - String)와 모델 객체(Value - Action)를 이용하여 생성
  // ▶ Map 객체를 사용하면 요청 정보(Key)를 이용하여 모델 객체(Value)를 빠르게 제공받아 사용할 수 있음
  private Map<String, Action> actionMap = new HashMap<>();

  // 클라이언트가 서블릿(Controller) 최초로 요청할 경우 서블릿 클래스를 객체로 생성한 후 가장 먼저 한번만 호출되는 메소드
  // ▶ 생성자 대신 초기화 작업을 실행하기 위한 메소드
  @Override
  public void init(ServletConfig config) throws ServletException {
    //System.out.println("ControllerServlet 클래스의 init() 메소드 호출");
    /*
    
    이걸 사용 또 안하고 Properties 파일로 만들거야!
    
    // Map 객체에 엔트리(Entry) 추가
    // ▶ 모델 객체를 하나만 사용하기 때문에 메모리 효율이 좋다!
    actionMap.put("/loginform.do", new LoginFormModel());
    actionMap.put("/login.do", new LoginModel());
    actionMap.put("/logout.do", new LogoutModel());
    actionMap.put("/writeform.do", new WriteFormModel());
    actionMap.put("/write.do", new WriteModel());
    actionMap.put("/list.do", new ListModel());
    actionMap.put("/view.do", new ViewModel());
    actionMap.put("/modifyform.do", new ModifyFormModel());
    actionMap.put("/modify.do", new ModifyModel());
    actionMap.put("/remove.do", new RemoveModel());
    actionMap.put("/error.do", new ErrorModel());

    */

    // Properties 파일에 요청 정보와 모델 클래스를 저장하고 Properties 파일을 읽어 Map 객체의 엔트리로 추가 - 유지 보수의 효율성 증가
    // ▶ 컨트롤러를 변경하지 않고 Properties 파일만 변경하여 요청 정보에 대한 모델 객체 추가 및 관리
    // Properties 파일(XXX.properties): 프로그램 실행에 필요한 값을 제공하기 위한 텍스트

    // Properties 파일의 내용을 저장하기 위한 Properties 객체 생성
    Properties properties = new Properties();

    //ServletConfig.getInitParameter(String name): [web.xml] 파일에서 init-param 엘리먼트로 제공된 값을 읽어와 반환하는 메소드
    String configFile = config.getInitParameter("configFile");


    // Properties 파일의 파일 시스템 경로를 반환받아 저장
    //String configFilePath = config.getServletContext().getRealPath("/WEB-INF/model.properties");
    // 위에 거를 더 유지 보수 효율성 극대화 하기 위하여 init-param 추가!
    
    String configFilePath = config.getServletContext().getRealPath(configFile);
    // System.out.println("configFilePath = " + configFilePath);
    // configFilePath = C:\Temp\apache-tomcat-9.0.75\webapps\mvc\WEB-INF\model.properties

    try {
      // Properties 파일의 내용을 읽기 위한 파일 입력 스트림을 생성
      FileInputStream in = new FileInputStream(configFilePath);

      // Properties 파일의 내용을 읽어 Properties 객체의 엔트리로 추가할 수 있도록 저장
      properties.load(in);
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Properties 객체의 모든 엔트리의 이름(Key)이 저장된 Set 객체를 반환받아 for 구문을 이용하여 반복 처리
    for (Object key : properties.keySet()) {
      // Properties 객체에 저장된 엔트리의 이름(Key)을 반환받아 저장 - 요청 정보
      String command = (String) key;

      // Properties 객체에 저장된 엔트리의 값(Value)을 반환받아 저장 - 모델 클래스
      String actionClass = (String) properties.get(key); // Action이라는 클래스가 저장되야 하므로 클래스로 바꿔주어야 함!

      try {
        // 모델 클래스로 객체를 생성하여 저장 - 리플렉션 기능 사용
        // 리플렉션(Reflection): 프로그램의 명령 실행 시 Class 객체(Clazz)로 객체를 생성하여 객체의 필드 또는 메소드에 접근하도록 제공하는 기능

        // Class.forName(String className): 매개 변수로 전달받은 문자열의 클래스를 읽어 메모리에 저장하고 Class 객체(Clazz)를 반환하는 메소드
        // ▶ ClassNotFoundException 발생
        // Class.getDecalredConstructor(): Class 객체의 (기본)생성자가 저장된 Constructor 객체를 반환하는 메소드
        // Constructor.newInstance(): Constructor 객체에 저장된 생성자를 이용하여 Object 타입의 객체를 생성하여 반환하는 메소드
        Action actionObject = (Action) Class.forName(actionClass).getDeclaredConstructor().newInstance();

        // Map 객체에 엔트리(Key: 요청정보 / Value: 모델 객체) 추가
        actionMap.put(command, actionObject);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // System.out.println("ControllerServlet 클래스의 service() 메소드 호출!");

    // 2. 클라이언트의 요청을 분석: 요청 URL 주소 이용 - http://localhost:8000/mvc/XXX.do = 클라이언트의 요청이 어떤 do?
    // HttpServletRequest.getRequestURI(): 요청 URL 주소에서 URI 주소를 반환하는 메소드
    String requestURI = request.getRequestURI();
    // System.out.println("requestURI = " + requestURI); //requestURI = /mvc/XXX.do

    // HttpServletRequest.getContextPath(): 요청 URL 주소에서 컨텍스트 경로를 반환하는 메소드
    String contextPath = request.getContextPath();
    // System.out.println("contextPath = " + contextPath); // contextPath = /mvc

    String command = requestURI.substring(contextPath.length());
    // System.out.println("command = " + command); // command = /XXX.do

    // 3. 모델(Model) 객체를 이용하여 요청 처리 메소드를 호출하여 클라이언트의 요청을 처리하고 뷰(View) 관련 정보를 반환받아 저장
    // ▶ 하나의 요청에 대하여 하나의 모델 객체가 요청을 처리하도록 설정 - Command Controller Pattern
    // ▶ 요청 처리 메소드가 선언된 모델 역할의 클래스 작성 - 모델 역할의 클래스는 인터페이스를 상속받아 작성

    // 회원 관리 프로그램에서 클라이언트 요청(Command)에 대한 모델 객체가 매핑되도록 설정
    // ▶ [/loginform.do]: LoginFormModel 클래스를 객체로 만들어 요청 처리 메소드로 호출하여 요청을 처리할 수 있도록!
    // ▶ [/login.do]: LoginModel 클래스
    // ▶ [/logout.do]: LogoutModel 클래스
    // ▶ [/writeForm.do]: WriteFormModel 클래스
    // ▶ [/write.do]: WriteModel 클래스
    // ▶ [/list.do]: ListModel 클래스
    // ▶ [/view.do]: ViewModel 클래스
    // ▶ [/modifyform.do]: ModifyFormModel 클래스
    // ▶ [/modify.do]: ModifyModel 클래스
    // ▶ [/remove.do]: RemoveModel 클래스
    // ▶ [/error.do]: ErrorModel 클래스

    // 모델 역할의 클래스가 상속받기 위한 인터페이스로 참조 변수 생성
    // ▶ 인터페이스로 생성된 참조 변수에는 인터페이스를 상속받은 모든 자식 클래스(모델)의 객체를 생성할 수 있음
    // Action action = null; = 맵 객체를 사용하므로 이렇게 안한다

    /*
     * 컨트롤러에서의 권한 설정! HttpSession session = request.getSession(); UserinfoDTO loginUserinfo =
     * (UserinfoDTO) session.getAttribute("loginUserinfo"); if (command.equals("/writeform.do") ||
     * command.equals("/modifyform.do")) { if (loginUserinfo == null || loginUserinfo.getStatus() !=
     * 9) { command = "/error.do"; } }
     */

    /*
     * 
     * 맵 객체를 이용하므로 이건 사용하지 않는다!
     * 
     * // 클라이언트 요청 정보를 구분하여 인터페이스의 참조 변수에 필요한 모델 역할의 클래스로 객체를 생성하여 저장 if
     * (command.equals("/loginform.do")) { action = new LoginFormModel(); } else if
     * (command.equals("/login.do")) { action = new LoginModel(); } else if
     * (command.equals("/logout.do")) { action = new LogoutModel(); } else if
     * (command.equals("/writeform.do")) { action = new WriteFormModel(); } else if
     * (command.equals("/write.do")) { action = new WriteModel(); } else if
     * (command.equals("/list.do")) { action = new ListModel(); } else if
     * (command.equals("/view.do")) { action = new ViewModel(); } else if
     * (command.equals("/modifyform.do")) { action = new ModifyFormModel(); } else if
     * (command.equals("/modify.do")) { action = new ModifyModel(); } else if
     * (command.equals("/remove.do")) { action = new RemoveModel(); } else if
     * (command.equals("/error.do")) { action = new ErrorModel(); } else { // 클라이언트 요청에 대한 모델 역할의
     * 클래스가 없는 경우 action = new ErrorModel(); }
     * 
     */

    // Map 객체에 저장된 엔트리에서 요청 정보(Key)를 이용하여 모델 객체(Value)를 반환받아 저장
    Action action = actionMap.get(command);
    if (action == null) {
      action = actionMap.get("/error.do");
    }

    // 인터페이스의 참조 변수로 참조 변수에 저장된 모델 역할의 클래스로 생성된 객체의 요청 처리 메소드를 호출하고 뷰 관련 정보를 반환받아 저장 - 오버라이드의 의한 다형성
    ActionForward actionForward = action.execute(request, response);

    // 4. 응답 관련 정보가 저장된 ActionForward 객체를 이용하여 응답 처리
    if (actionForward.isForward()) { // ActionForward 객체의 forward 필드값이 [true]인 경우 JSP 문서로 포워드 이동하여 JSP 문서의 실행결과(HTML)로 클라이언트에게 전달하여 응답 처리
      request.getRequestDispatcher(actionForward.getPath()).forward(request, response);
    } else { // ActionForward 객체의 forward 필드값이 [false]인 경우 Servlet에서 클라이언트에게 URL 주소(/XXX.do)를 전달하여
             // 응답 처리
      // ▶ URL 주소를 전달받은 클라이언트는 브라우저의 URL 주소를 변경하여 Servlet을 요청
      response.sendRedirect(actionForward.getPath());
    }
  }

}
