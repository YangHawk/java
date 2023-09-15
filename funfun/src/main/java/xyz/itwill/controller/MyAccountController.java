package xyz.itwill.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xyz.itwill.dto.Account;

@Controller
@RequestMapping("/account")
public class MyAccountController {
	@RequestMapping(value = "/myaccount", method = RequestMethod.GET)
	public String index(HttpSession session) {
		Account loginAccount = (Account)session.getAttribute("loginAccount");
		if(loginAccount == null) {
			return "account/login";
		}
		return "account/myaccount";
	}
}
