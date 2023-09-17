package xyz.itwill.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@PreAuthorize("isAuthenticated()")
@Controller
@RequestMapping("/account")
public class MyAccountController {
	@RequestMapping(value = "/myaccount", method = RequestMethod.GET)
	public String index(Authentication authentication) {

		return "account/myaccount";
	}
}
