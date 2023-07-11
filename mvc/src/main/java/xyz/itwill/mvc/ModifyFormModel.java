package xyz.itwill.mvc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import xyz.itwill.dto.UserinfoDTO;
import xyz.itwill.service.UserinfoService;

// 클라이언트가 [/modifyform.do]로 요청한 경우 객체로 생성될 모델 역할의 클래스
// ▶ 관리자만 요청 가능하도록 권한 설정
// ▶ 아이디를 전달받아 USERINFO 테이블에 저장된 회원 정보를 검색하여 request 객체의 속성값으로 저장하고
// [user_modify.jsp]로 포워드 이동하기 위한 정보가 저장된 ActionForward 객체 반환
public class ModifyFormModel implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    ActionForward actionForward = new ActionForward();
    try {
      HttpSession session = request.getSession();
      UserinfoDTO loginUserinfo = (UserinfoDTO) session.getAttribute("loginUserinfo");
      if (loginUserinfo == null || loginUserinfo.getStatus() != 9) {
        throw new Exception();
      }

      if (request.getParameter("userid") == null) {
        throw new Exception();
      }

      String userid = request.getParameter("userid");

      request.setAttribute("userinfo", UserinfoService.getService().getUserinfo(userid));

      actionForward.setForward(true);
      actionForward.setPath("/model_two/user_modify.jsp");
    } catch (Exception e) {
      e.printStackTrace();
      actionForward.setForward(false);
      actionForward.setPath(request.getContextPath() + "/error.do");
    }
    return actionForward;
  }
}


