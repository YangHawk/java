package xyz.itwill.guest;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.itwill.dao.GuestDAO;
import xyz.itwill.dto.GuestDTO;

//방명록 게시글을 전달받아 GUEST 테이블에 저장된 행을 변경하고 방명록 게시글 목록 페이지(/guest/list.itwill)로 이동하기 위한 URL 주소를 클라이언트에게 전달하여 응답하는 서블릿 
//→ 방명록 게시글 입력 페이지(/guest/modifyForm.itwill)에서 form 태그를 사용하여 post 방식으로 요청하는 서블릿
@WebServlet("/guest/modify.itwill")
public class GuestUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getMethod().equals("GET")) {
			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return;
		}

		request.setCharacterEncoding("utf-8");

		int num = Integer.parseInt(request.getParameter("num"));
		String writer = request.getParameter("writer");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");

		GuestDTO guest = new GuestDTO();
		guest.setNum(num);
		guest.setWriter(writer);
		guest.setSubject(subject);
		guest.setContent(content);

		GuestDAO.getDAO().updateGuest(guest);

		response.sendRedirect("list.itwill");

	}

}
