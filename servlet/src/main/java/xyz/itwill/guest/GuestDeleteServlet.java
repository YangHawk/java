package xyz.itwill.guest;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.itwill.dao.GuestDAO;
import xyz.itwill.dto.GuestDTO;

//글번호를 GUEST 테이블에 저장된 행을 삭제하고 방명록 게시글 목록 페이지(/guest/list.itwill)로 이동하기 위한 URL 주소를 클라이언트에게 전달하여 응답하는 서블릿 
@WebServlet("/guest/remove.itwill")
public class GuestDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		if (request.getMethod().equals("GET")) {
			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return;
		}
		*/

		// 전달값이 없는 경우 - 비정상적인 요청
		if (request.getParameter("num") == null) { // 전달값이 없는 경우 - 비정상적인 요청
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		int num = Integer.parseInt(request.getParameter("num"));

		// 글번호를 전달받아 GUEST 테이블에 저장된 행을 삭제하는 DAO 클래스의 메소드 호출
		int rows = GuestDAO.getDAO().deleteGuest(num);

		if (rows > 0) {
			response.sendRedirect("list.itwill");
		} else { // 삭제된 행이 없는 경우 - 비정상적인 요청
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}

	}

}
