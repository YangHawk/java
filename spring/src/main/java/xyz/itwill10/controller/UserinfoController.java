package xyz.itwill10.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import xyz.itwill10.dto.Userinfo;
import xyz.itwill10.exception.ExistsUserinfoException;
import xyz.itwill10.exception.LoginAuthFailException;
import xyz.itwill10.exception.UserinfoNotFoundException;
import xyz.itwill10.service.UserinfoService;

@Controller
@RequestMapping("/userinfo")
@RequiredArgsConstructor
public class UserinfoController {
	private final UserinfoService userinfoService;
	/*

	// 회원 정보를 입력받기 위한 뷰이름을 반환하는 메소드
	// ▶ 비로그인 사용자 또는 관리자가 아닌 사용자가 페이지를 요청할 경우 인위적 예외 발생
	// ▶ 예외 처리 메소드(Exception Handle Method)를 이용하여 예외 처리
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(HttpSession session) throws BadRequestException {
		
		Userinfo loginUserinfo = (Userinfo) session.getAttribute("loginUserinfo");
		
		if (loginUserinfo == null || loginUserinfo.getStatus() != 9) {
			// throw new Exception("비정상적인 요청입니다.");
			throw new BadRequestException("비정상적인 요청입니다.");
		}
		
		return "userinfo/user_write";
	}
	
	*/
	
	// 회원 정보를 입력받기 위한 뷰이름을 반환하는 메소드
	// ▶ 인터셉터를 사용하여 권한 관련 처리 기능을 구현 - 요청 처리 메소드에서 권한 관령 명령을 
	// 인터셉터(Interceptor): 요청 처리 메소드가 호출되기 전 또는 후에 삽입되어 실행될 명령을 작성하여 제공
	// ▶ 인터셉터 관련 클래스를 작성하여 Spring Bean Configuration File(servlet-context.xml)에 인터셉터가 동작할 수 있도록 환경 설정
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(HttpSession session) {
		return "userinfo/user_write";
	}

	/*
	
	// 회원 정보를 전달받아 USERINFO 테이블에 삽입하고 로그인 페이지를 요청할 수 있는 URL 주소를 클라이언트에게 전달하여 응답 처리하는 요청 처리 메소드
	// ▶ UserinfoService 객체로 메소드 호출 시 예외 발생 - try~catch 구문을 사용하여 예외 처리
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@ModelAttribute Userinfo userinfo, Model model) {
		try {
			// 매개 변수로 전달받은 회원 정보의 아이디가 중복될 경우 ExistsUserinfoException 예외 발생
			userinfoService.addUserinfo(userinfo);
		} catch (ExistsUerinfoException e) {
			// ExistsUerinfoException 객체에 저장된 예외 메세지를 반환받아 속성값으로 저장
			model.addAttribute("message", e.getMessage());

			// ExistsUerinfoException 객체에 저장된 회원 정보(Userinfo 객체)를 반환받아 속성값으로 저장
			// model.addAttribute("userinfo", e.getUserinfo());

			// 아이디가 중복되어 예외가 발생된 경우 회원 정보를 입력받기 위한 뷰이름을 반환
			return "userinfo/user_write";
		}
		return "redirect:/userinfo/login";
	}

	 */
	
	// 회원 정보를 전달받아 USERINFO 테이블에 삽입하고 로그인 페이지를 요청할 수 있는 URL 주소를 클라이언트에게 전달하여 응답 처리하는 요청 처리 메소드
	// ▶ UserinfoService 객체로 메소드 호출 시 예외 발생 - 예외 처리 메소드를 이용하여 예외 처리
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@ModelAttribute Userinfo userinfo) throws ExistsUserinfoException{
		
		userinfoService.addUserinfo(userinfo);
		
		return "redirect:/userinfo/login";
	}
	
	// 로그인 정보를 입력받기 위한 뷰이름을 반환하는 요청 처리 메소드
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	
	public String login() {
		
		return "userinfo/user_login";
	}

	/*
	
	// 로그인 정보를 전달받아 로그인 처리하고 환영메세지를 출력하는 뷰이름을 반환하는 요청 처리 메소드
	// ▶ 인증 성공 후 회원 정보를 세션에 속성값으로 저장 - 권한
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute Userinfo userinfo, Model model, HttpSession session) {
		try {
			// 매개 변수로 전달받은 회원 정보로 인증 실패된 경우 LoginAuthFailException 발생
			Userinfo authUserinfo = userinfoService.loginAuth(userinfo);

			// LoginAuthFailException 미발생 - 인증 성공
			session.setAttribute("loginUserinfo", authUserinfo);
		} catch (LoginAuthFailException e) {
			model.addAttribute("message", e.getMessage());
			model.addAttribute("userid", e.getUserid());
			return "userinfo/user_login";
		}
		return "userinfo/user_login";
	}
	
	 */
	
	// 로그인 정보를 전달받아 로그인 처리하고 환영메세지를 출력하는 뷰이름을 반환하는 요청 처리 메소드
	// ▶ 인증 성공 후 회원 정보를 세션에 속성값으로 저장 - 권한
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute Userinfo userinfo, HttpSession session) throws LoginAuthFailException {
		
			Userinfo authUserinfo = userinfoService.loginAuth(userinfo);
			
			session.setAttribute("loginUserinfo", authUserinfo);
			
			return "userinfo/user_login";
	}
	
	// 로그아웃 처리하고 로그인 페이지를 요청할 수 있는 URL 주소를 클라이언트에게 전달하여 응답 처리하는 요청 처리 메소드
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		// session.removeAttribute("loginUserinfo");
		session.invalidate();
		
		return "redirect:/userinfo/login";
	}
	
	/*
	
	// USERINFO 테이블에 저장된 모든 회원 정보를 검색하여 속성값으로 저장하여 회원 목록을 출력하는 뷰이름을 반환하는 요청 처리 메소드
	// ▶ 비로그인 사용자가 페이지를 요청할 경우 인위적 예외 발생
	@RequestMapping("/list")
	public String list(Model model, HttpSession session) throws BadRequestException {
		
		Userinfo loginUserinfo = (Userinfo) session.getAttribute("loginUserinfo");
		
		if (loginUserinfo == null) {
			// throw new Exception("비정상적인 요청입니다.");
			throw new BadRequestException("비정상적인 요청입니다.");
		}
		
		model.addAttribute("userinfoList", userinfoService.getUserinfoList());
		
		return "userinfo/user_list";
	}

	 */
	
	// USEINFO 테이블에 저장된 모든 회원정보를 검색하여 속성값으로 저장하여 회원목록을 출력하는 뷰이름을 반환하는 요청 처리 메소드
	// ▶ 인터셉터를 사용하여 권한 관련 처리 기능 구현 - 요청 처리 메소드에서 권한 관련 명령 미작성
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("userinfoList", userinfoService.getUserinfoList());
		
		return "userinfo/user_list";
	}
	
	/*
	
	// 아이디를 전달받아 USERINFO 테이블에 저장된 모든 회원 정보를 검색하여 속성값으로 저장하여 회원 정보를 출력하는 뷰이름을 반환하는 요청 처리 메소드
	// ▶ 비로그인 사용자가 페이지를 요청할 경우 인위적 예외 발생
	@RequestMapping("/view")
	public String view(@RequestParam String userid, Model model, HttpSession session) throws BadRequestException, UserinfoNotFoundException {
		
		Userinfo loginUserinfo = (Userinfo) session.getAttribute("loginUserinfo");
		
		if (loginUserinfo == null) {
			// throw new Exception("비정상적인 요청입니다.");
			throw new BadRequestException("비정상적인 요청입니다.");
		}
		
		model.addAttribute("userinfoList", userinfoService.getUserinfo(userid));
		
		return "userinfo/user_view";
	}
	
	 */
	
	// 아이디를 전달받아 USERINFO 테이블에 저장된 모든 회원 정보를 검색하여 속성값으로 저장하여 회원 정보를 출력하는 뷰이름을 반환하는 요청 처리 메소드
	// ▶ 인터셉터를 사용하여 권한 관련 처리 기능 구현 - 요청 처리 메소드에서 권한 관련 명령 미작성
	@RequestMapping("/view")
	public String view(@RequestParam String userid, Model model, HttpSession session) throws UserinfoNotFoundException {

		model.addAttribute("userinfoList", userinfoService.getUserinfo(userid));

		return "userinfo/user_view";
	}
	
	/*
	
	// 아이디를 전달받아 USEINFO 테이블에 저장된 회원정보를 검색하여 속성값으로 저장하여 회원정보를 변경하는 뷰이름을 반환하는 요청 처리 메소드
	// ▶ 비로그인 사용자 또는 관리자가 아닌 사용자가 페이지를 요청할 경우 인위적 예외 발생
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(@RequestParam String userid, Model model, HttpSession session) throws BadRequestException, UserinfoNotFoundException {
		
		Userinfo loginUserinfo = (Userinfo) session.getAttribute("loginUserinfo");
		
		if (loginUserinfo == null || loginUserinfo.getStatus() != 9) {
			throw new BadRequestException("비정상적인 요청입니다.");
		}

		model.addAttribute("userinfo", userinfoService.getUserinfo(userid));
		
		return "userinfo/user_modify";
	}
	
	 */
	
	// 아이디를 전달받아 USEINFO 테이블에 저장된 회원정보를 검색하여 속성값으로 저장하여 회원정보를 변경하는 뷰이름을 반환하는 요청 처리 메소드
	// ▶ 인터셉터를 사용하여 권한 관련 처리 기능 구현 - 요청 처리 메소드에서 권한 관련 명령 미작성
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(@RequestParam String userid, Model model, HttpSession session) throws UserinfoNotFoundException {

		model.addAttribute("userinfo", userinfoService.getUserinfo(userid));

		return "userinfo/user_modify";
	}

	// 회원정보를 전달받아 USERINFO 테이블에 저장된 회원정보를 변경하고 회원정보를 출력하는 페이지를 요청할 수 있는 URL 주소를 클라이언트에게 전달하여 응답 처리하는 요청 처리 메소드
	// ▶ 회원정보를 출력하는 페이지 요청시 아이디 전달
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(@ModelAttribute Userinfo userinfo, HttpSession session) throws UserinfoNotFoundException {
		userinfoService.modifyUserinfo(userinfo);

		Userinfo loginUserinfo = (Userinfo) session.getAttribute("loginUserinfo");
		// 로그인 사용자와 변경 처리된 사용자가 동일한 경우
		if (loginUserinfo.getUserid().equals(userinfo.getUserid())) {
			// 세션에 권한 관련 정보(회원정보)로 저장된 속성값 변경
			session.setAttribute("loginUserinfo", userinfoService.getUserinfo(userinfo.getUserid()));
		}

		return "redirect:/userinfo/view?userid=" + userinfo.getUserid();
	}

	/*
	
	// 아이디를 전달받아 USEINFO 테이블에 저장된 회원정보를 삭제하고 회원목록 출력페이지를 요청할 수 있는 URL 주소를 클라이언트에게 전달하여 응답 처리하는 요청 처리 메소드
	// ▶ 비로그인 사용자 또는 관리자가 아닌 사용자가 페이지를 요청할 경우 인위적 예외 발생
	@RequestMapping("/remove")
	public String remove(@RequestParam String userid, HttpSession session) throws BadRequestException, UserinfoNotFoundException {
		
		Userinfo loginUserinfo = (Userinfo) session.getAttribute("loginUserinfo");
		
		if (loginUserinfo == null || loginUserinfo.getStatus() != 9) {
			throw new BadRequestException("비정상적인 요청입니다.");
		}

		userinfoService.removeUserinfo(userid);

		if (loginUserinfo.getUserid().equals(userid)) {
			return "redirect:/userinfo/logout";
		}

		return "redirect:/userinfo/list";
	}
	
	 */
	
	// 아이디를 전달받아 USEINFO 테이블에 저장된 회원정보를 삭제하고 회원목록 출력페이지를 요청할 수 있는 URL 주소를 클라이언트에게 전달하여 응답 처리하는 요청 처리 메소드
	// ▶ 인터셉터를 사용하여 권한 관련 처리 기능 구현 - 요청 처리 메소드에서 권한 관련 명령 미작성
	@RequestMapping("/remove")
	public String remove(@RequestParam String userid, HttpSession session) throws UserinfoNotFoundException {
		
		Userinfo loginUserinfo = (Userinfo) session.getAttribute("loginUserinfo");
		
		userinfoService.removeUserinfo(userid);

		if (loginUserinfo.getUserid().equals(userid)) {
			return "redirect:/userinfo/logout";
		}

		return "redirect:/userinfo/list";
	}
}