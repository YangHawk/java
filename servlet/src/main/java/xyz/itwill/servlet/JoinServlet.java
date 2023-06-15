package xyz.itwill.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//입력 페이지(form.html)로부터 전달받은 입력값(회원 정보)을 반환받아 클라이언트에게 전달하여 응답하는 서블릿
//→ 입력 페이지의 form 태그를 이용하여 POST 방식으로 요청해야만 실행되는 서블릿
@WebServlet("/join.itwill")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		// 서블릿을 GET 방식으로 요청한 경우 비정상적인 요청으로 클라이언트에게 에러 코드를 전달하거나 에러 페이지로 이동되도록 처리
		// HttpServletRequest.getMethod(): 서블릿을 요청한 요청 방식(GET / POST)을 반환하는 메소드
		if (request.getMethod().equals("GET")) { // 서블릿을 비정상적으로 요청한 경우

			// 1. 에러 코드를 전달
			// HttpServletResponse.sendError(int sc): 클라이언트에게 에러 코드(4XX / 5XX)를 전달하여 응답하는 메소드
			// → 상태 코드(StatusCode)는 HttpServletResponse 인터페이스의 정의된 상수를 사용
			// response.sendError(405);
			// response.sendError(HttpServletResponse.SC_BAD_REQUEST); // 400
			// response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED); // 405
			// return;

			// 2. 에러 페이지로 이동
			// HttpServletResponse.sendError.sendRedirect(String url): 클라이언트에게 301 상태 코드와 URL 주소를 전달하여 응답하는 메소드
			// → 클라이언트는 전달받은 URL 주소로 브라우저의 요청 URL을 변경하여 웹프로그램을 요청하여 실행 결과를 응답받아 출력 - 페이지 이동(리다이렉트 이동)
			// response.sendRedirect("error.html"); // 에러 페이지로 이동
			// response.sendRedirect("form.html"); // 입력 페이지로 이동
			// return;

			// 3. 클라이언트에서 자바스크립트 전달하여 응답
			out.println("<script type='text/javascript'>");
			out.println("alert('비정상적인 페이지 요청');");
			out.println("location.href='form.html';");
			out.println("</script>");
			return;
		}

		// 서블릿을 POST 방식으로 요청한 경우 사용자 입력값을 리퀘스트 메세지 몸체부에 저장하여 전달
		// → 리퀘스트 메세지 몸체부에 저장되어 전달되는 값은 기본적으로 서유럽어(ISO-8859-1)의 문자 형태로 반환받아 사용
		// → 리퀘스트 메세지 몸체부에 저장되어 전달되는 값에 대한 캐릭터셋 변경
		// HttpServletRequest.setCharacterEncoding(String encoding): 리퀘스트 메세지 몸체부에 저장되어 전달되는 값에 대한 문자 형태를 변경하는 메소드
		// → GET 방식으로 요청한 경우 리퀘스트 메세지 몸체부를 사용하지 않으므로 메소드 사용 필요 XXX
		request.setCharacterEncoding("utf-8");

		// 클라이언트에서 서블릿을 요청할 때 전달된 값을 반환받아 저장
		// HttpServletRequest.getParameter(String name): 매개변수로 전달된 이름의 전달값을 문자열(String 객체)로 반환하는 메소드
		// → 매개변수의 이름으로 전달값이 없는 경우 null 반환 - error가 아님!
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");

		// 동일한 이름으로 전달되는 값이 여러 개 있는 경우 getParameterValues() 메소드 호출
		// HttpServletRequest.getParameterValues(String name): 매개변수로 전달받은 이름의 모든 전달값을 문자열 배열(String[])로 반환하는 메소드
		// String hobby = request.getParameter("hobby"); // 첫번째 전달값만 반환받아 저장
		String[] hobby = request.getParameterValues("hobby");

		String job = request.getParameter("job");
		String profile = request.getParameter("profile");

		// 이미 입력값에 대한 검증을 끝냈는데 왜 또해? - 보안 때문에! 클라이언트 쪽에서 자바 스크립트를 이용하여 했지만? 서버 쪽에서 서블릿을 이용하여 하는 것도 중요!
		if (id == null || id.equals("")) { // 전달값이 없는 경우
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		if (!Pattern.matches("^[a-zA-Z]\\w{5,19}$", id)) { // 전달값이 정규 표현식 패턴과 맞지 않는 경우
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<title>Servlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>회원 가입 확인</h1>");
		out.println("<hr>");
		out.println("<p>아이디는 " + id + "!</p>");
		out.println("<p>비번은 " + passwd + "!</p>");
		out.println("<p>이름은 " + name + "!</p>");
		out.println("<p>이메일은 " + email + "!</p>");
		out.println("<p>성별은 " + gender + "!</p>");
		// out.println("<p>취미는" + hobby + "!</p>");
		out.println("<p>취미는 ");
		for (int i = 0; i < hobby.length; i++) { // 배열로 반환받아 저장되었기 때문에 배열의 요소를 출력해야 함
			out.println(hobby[i]); // 배열의 요소값을 HTML 문서에 포함하여 응답
			if (i < hobby.length - 1) {
				out.println(", ");
			}
		}
		out.println("!</p>");
		out.println("<p>직업은 " + job + "!</p>");
		// 전달값의 엔터(Enter)는 브라우저에서 실행되지 않는 문자이므로 br 태그로 변환시켜 응답 처리
		// out.println("<p>자기소개: " + profile + "에요!</p>"); // \n 무시
		out.println("<p>자기소개<br>" + profile.replace("\n", "<br>") + "!</p>"); // 라인 스킵 발생!
		/*
		 * 브라우저에서 `\n`은 줄바꿈을 의미하지 않습니다.
		 * 
		 * `\n`은 일반적으로 문자열에서 사용되는 이스케이프 시퀀스(escape sequence) 중 하나로, 줄바꿈 문자를 나타냅니다. 이는 대부분의 프로그래밍 언어에서 문자열 안에서 줄바꿈을 표현하는 방법입니다.
		 * 
		 * 하지만 HTML 문서에서는 `\n`이 그대로 표시되지 않고 무시됩니다. HTML은 공백과 줄바꿈에 대해 일반적으로 무시하는 경향이 있습니다. 따라서 `\n`은 HTML 문서에서는 줄바꿈을 표현하지 않습니다.
		 * 
		 * HTML에서 줄바꿈을 표현하려면 `<br>` 태그를 사용해야 합니다. 따라서, 주어진 코드에서 `profile.replace("\n", "<br>")`는 문자열 안에 있는 모든 `\n`을 `<br>`로 대체하여 줄바꿈을 표현하는 것입니다. 이렇게 변경된 문자열은 브라우저에서 HTML로 렌더링될 때 줄바꿈으로 표시됩니다.
		 */
		out.println("</body>");
		out.println("</html>");

	}

}
