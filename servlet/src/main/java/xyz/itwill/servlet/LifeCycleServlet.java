package xyz.itwill.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//서블릿은 클라이언트 요청에 의하여 WAS 프로그램에 등록된 서블릿 클래스를 읽어 메모리에 저장하고 서블릿 클래스로 객체를 생성하여 요청 처리 메소드 호출
//→ 클라이언트 요청에 의한 서블릿 객체가 이미 생성되어 있는 경우 새로운 서블릿 객체를 생성하지 않고 기존 서블릿 객체를 사용하여 요청 처리 메소드 호출
//→ WAS 프로그램이 종료되면 WAS 프로그램에 의하여 생성되어 관리된 모든 서블릿 객체 소멸
//→ WAS 프로그램은 서블릿 객체를 관리하는 웹 컨테이너(Web Container) 기능을 제공 
//컨테이너(Container): 프로그램 실행에 필요한 객체의 생명주기(Life Cycle)를 관리하는 프로그램
//→ 서블릿은 컨테이너에 의하여 관리되는 컴포넌트(Componenet)
@WebServlet("/life.itwill")
public class LifeCycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String name;

	// 서블릿 클래스로 객체를 생성하기 위한 생성자 - 객체를 생성하면서 초기화 처리 명령 작성
	public LifeCycleServlet() {
		// name = "홍길좆";
		System.out.println("※LifeCycleServlet 클래스의 생성자 호출 - 객체 생성※");
	}

	// 서블릿 객체가 생성된 후 자동으로 한번만 호출되는 메소드 - 객체를 생성한 후에 초기화 처리 명령 작성
	// ※생성자에서도 name 전달 가능 / init에서도 가능! 그럼 왜 init이 필요한가? 어차피 자동으로 호출되는데!
	// 생성자 대신 init 메소드를 이용하여 초기화 처리 명령을 작성하는 이유
	// → init() 메소드의 매개변수로 ServletConfig 객체를 제공받아 사용 가능하기 때문
	// ServletConfig 객체: 웹 자원을 생성하기 위한 환경 설정 관련 정보를 저장한 객체
	// → [web.xml] 파일에서 제공되는 값을 얻어와 서블릿 클래스에서 사용 가능 - 유지 보수의 효율성 증대
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("※LifeCycleServlet 클래스의 init() 메소드 호출※");

		// name = "홍길좆"; → 이거 말고 web.xml에서 받아와보자!
		// ServletConfig.getServletContext(): ServletContext 객체를 반환하는 메소드
		// ServletContext.getInitParameter(String name): [web.xml] 파일에서 제공되는 값을 얻어와 반환하는 메소드
		name = config.getServletContext().getInitParameter("name");

		// [web.xml] 파일의 init-param 엘리먼트로 제공되는 값은 ServletConfig 객체의 getInitParameter(String name) 메소드를 호출하여 반환
		// 근데 지금 HelloServlet에서 쓰고 있으므로 지금은 못 불러와!

	}

	// 서블릿 객체가 소멸되기 전에 WAS 프로그램에 의하여 자동으로 한번만 호출되는 메소드
	// → 객체 소멸 전 실행될 명령 작성 - 마무리 처리
	@Override
	public void destroy() {
		System.out.println("※LifeCycleServlet 클래스의 destroy() 메소드 호출※");
	}

	// 클라이언트가 요청할 때마다 WAS 프로그램에 의하여 서블릿 객체로 반복 호출되는 메소드
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("※LifeCycleServlet 클래스의 service() 메소드 호출※");

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<title>Servlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>서블릿의 생명 주기(Life Cycle)</h1>");
		out.println("<hr>");
		out.println("<p>" + name + "님아! 안녕하세요.</p>");
		out.println("</body>");
		out.println("</html>");
	}

}
