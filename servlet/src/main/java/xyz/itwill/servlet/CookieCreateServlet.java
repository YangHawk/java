package xyz.itwill.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//클라이언트에게 쿠기를 전달하고 실행 결과를 웹 문서로 응답하는 서블릿
//→ 서블릿에 의하여 전달받은 쿠키는 클라이언트에 저장
//쿠키(Cookie): 서버(웹 프로그램)와 클라이언트(웹 브라우저)의 연결 지속성을 제공하기 위하여 클라이언트에 저장되는 문자값
//→ 클라이언트는 접속 서버의 정보를 식별자로 하여 쿠키를 저장
@WebServlet("/create.itwill")
public class CookieCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		// Cookie 객체 생성 - Cookie 객체를 클라이언트에 전달하여 클라이언트에 쿠키가 저장
		// → Cookie 객체: 쿠키 관련 정보를 저장하기 위한 객체
		// → Cookie(String name, String value) 생성자의 매개변수에 쿠키명과 쿠키값을 전달하여 객체를 생성
		// → 쿠키명: 쿠키값을 구분하기 위한 식별자 / 쿠기값: 연결 지속성을 제공하기 위한 문자값
		// → 쿠키명과 쿠키값은 영문자, 숫자, 일부 특수문자만 사용하여 작성 가능
		Cookie idCookie = new Cookie("id", "abc123");
		Cookie countCookie = new Cookie("count", "0");

		// 클라이언트에 전달되어 저장될 쿠키의 유지 시간 변경
		// Cookie.setMaxAge(int expiry): 쿠키의 유지 시간을 변경하는 메소드
		// → 매개변수에 쿠키를 유지하기 위한 시간(초)을 전달하면 클라이언트는 해당 시간 동안 쿠키를 유지
		// → setMaxAge() 메소드를 호출하지 않을 경우 쿠키의 유지 시간은 [-1]로 설정
		// → 유지 시간이 [-1]로 설정된 쿠키는 브라우저 종료 시 자동으로 소멸
		countCookie.setMaxAge(24 * 60 * 60); // 쿠키의 유지 시간을 1일로 변경

		// 클라이언트에게 Cookie 객체를 전달 - 클라이언트에 쿠키를 저장
		// → Cookie 객체의 유지 시간이 [-1]인 쿠키는 클라이언트 브라우저 메모리에 저장 - 브라우저 종료 시 소멸
		// → Cookie 객체의 유지 시간이 [-1]이 아닌 쿠키는 클라이언트의 쿠키 파일로 저장 - 유지 시간 경과 후 소멸
		// HttpServletResponse.addCookie(Cookie cookie): 클라이언트에게 Cookie 객체를 전달하는 메소드 - 리스폰스 메세지에 쿠키를 추가하여 전달
		response.addCookie(idCookie);
		response.addCookie(countCookie);

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<title>Servlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>내가 만든 쿠키~ 생성</h1>");
		out.println("<hr>");
		out.println("<p>내가 만든 쿠키~</p>");
		out.println("<hr>");
		out.println("<p><a href='read.itwill'>내가 만든 쿠키~</a></p>");
		out.println("</body>");
		out.println("</html>");

	}

}
