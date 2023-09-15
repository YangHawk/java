package xyz.itwill.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import xyz.itwill.exception.BadRequestException;
import xyz.itwill.exception.ExistsUserinfoException;
import xyz.itwill.exception.LoginAuthFailException;
import xyz.itwill.exception.UserinfoNotFoundException;



// @ControllerAdvice: 예외 처리 메소드만 작성된 Controller 클래스를 Spring Bean으로 등록하기 위한 어노테이션
// ▶ 모든 Controller 클래스의 요청 처리 메소드에서 발생되어 전달된 예외를 제공받아 처리 가능
@ControllerAdvice
public class ExceptionController {
	// @ExceptionHandler: 메소드의 예외 처리 기능을 제공하기 위한 어노테이션
	// ▶ Controller 클래스의 요청 처리 메소드에서 예외가 발생되어 Front Controller에게 예외가 전달될 경우 예외 객체를 제공받아 예외 처리하기 위한 메소드 - 예외 처리 메소드
	// ▶ 예외 처리 메소드는 매개변수를 이용하여 예외 처리에 필요한 객체를 제공받아 사용 가능하며 클라이언트에게 응답하기 위한 뷰이름 반환 - 리다이렉트 이동 가능
	// value 속성: 예외 처리하기 위한 클래스(Class 객체)를 속성값으로 설정
	
	@ExceptionHandler(value = BadRequestException.class)
	public String badRequestExceptionHandler() {
		return "error";
	}

	@ExceptionHandler(value = ExistsUserinfoException.class)
	public String existsUserinfoExceptionHandler(ExistsUserinfoException exception, Model model) {
		model.addAttribute("message", exception.getMessage());
		model.addAttribute("userinfo", exception.getAccount());
		return "account/register";
	}

	@ExceptionHandler(value = LoginAuthFailException.class)
	public String loginAuthFailExceptionHandler(LoginAuthFailException exception, Model model) {
		model.addAttribute("message", exception.getMessage());
		model.addAttribute("userid", exception.getUserid());
		return "account/login";
	}

	@ExceptionHandler(value = UserinfoNotFoundException.class)
	public String userinfoNotFoundExceptionHandler() {
		return "error";
	}
	
	/*
	
	@ExceptionHandler(value = Exception.class)
	public String exceptionHandler() {
		return "userinfo/user_error";
	}
	
	 */
}
