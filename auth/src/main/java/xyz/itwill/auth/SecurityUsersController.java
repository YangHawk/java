package xyz.itwill.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.SecurityAuth;
import xyz.itwill.dto.SecurityUsers;
import xyz.itwill.dto.SecurityUsersGroups;
import xyz.itwill.service.SecurityUsersService;

@Controller
@RequiredArgsConstructor
public class SecurityUsersController {
	private final SecurityUsersService securityUsersService;
	private final PasswordEncoder passwordEncoder;

	@RequestMapping(value = "/security/add", method = RequestMethod.GET)
	@ResponseBody
	public String addUsers() {
		SecurityUsers user1 = new SecurityUsers("abc123", passwordEncoder.encode("123456"), "홍길동", "abc@itwill.xyz",
				"1", null);
		SecurityUsers user2 = new SecurityUsers("xyz789", passwordEncoder.encode("123456"), "임꺽정", "xyz@itwill.xyz",
				"1", null);
		SecurityUsers user3 = new SecurityUsers("opq456", passwordEncoder.encode("123456"), "전우치", "opq@itwill.xyz",
				"1", null);

		securityUsersService.addSecurityUsers(user1);
		securityUsersService.addSecurityUsers(user2);
		securityUsersService.addSecurityUsers(user3);

		SecurityAuth auth1 = new SecurityAuth("abc123", "ROLE_USER");
		SecurityAuth auth3 = new SecurityAuth("abc123", "ROLE_MANAGER");
		SecurityAuth auth2 = new SecurityAuth("xyz789", "ROLE_MANAGER");
		SecurityAuth auth4 = new SecurityAuth("opq456", "ROLE_ADMIN");

		securityUsersService.addSecurityAuth(auth1);
		securityUsersService.addSecurityAuth(auth2);
		securityUsersService.addSecurityAuth(auth3);
		securityUsersService.addSecurityAuth(auth4);

		return "사용자를 성공적으로 등록하였습니다.";
	}

	// @Valid 혹은 @Validated 를 사용할 때 Errors 객체를 꼭 매개 변수로 설정 + BindingResult 사용 가능
	// RESTfulAPI 는 에러 일때 돌아가는 것이 아니라 JSON 객체를 주어야 하기 때문에
	@RequestMapping(value = "/security/add", method = RequestMethod.POST)
	public String addUsers(@ModelAttribute @Validated(SecurityUsersGroups.insertValid.class) SecurityUsers users,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "join";
		}
		return "redirect:/login";
	}
}
