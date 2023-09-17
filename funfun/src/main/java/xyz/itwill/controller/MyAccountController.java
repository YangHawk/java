package xyz.itwill.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xyz.itwill.security.CustomAccountDetails;


@PreAuthorize("isAuthenticated()")
@Controller
@RequestMapping("/account")
public class MyAccountController {
	@RequestMapping(value = "/myaccount", method = RequestMethod.GET)
	public String index(Authentication authentication, Model model) {
		CustomAccountDetails loginAccount = (CustomAccountDetails)authentication.getPrincipal();
		model.addAttribute("loginAccount", loginAccount);

		return "account/myaccount";
	}
}
