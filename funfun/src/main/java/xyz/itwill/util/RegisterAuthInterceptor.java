package xyz.itwill.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import xyz.itwill.dto.Account;
import xyz.itwill.exception.BadRequestException;

//등록자 관련 권한 처리를 위해 작성된 인터셉터 클래스
//▶ 요청 처리 메소드가 호출 전에 비로그인 사용자이거나 등록자가 아닌 사용자가 페이지를 요청한 경우 인위적으로 예외 발생 - 에러 페이지로 응답 처리
public class RegisterAuthInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();

		Account loginAccount = (Account) session.getAttribute("loginAccount");

		// 비로그인 사용자이거나 등록자가 아닌 사용자인 경우
		if (loginAccount == null || loginAccount.getStatus() != 1) {
			throw new BadRequestException("비정상적인 요청입니다.");
		}

		return true; // 요청 처리 메소드 호출
	}
}
