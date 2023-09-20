package xyz.itwill.controller;

import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Account;
import xyz.itwill.service.AccountService;

@RestController
@RequiredArgsConstructor
public class CheckEmailRestController {
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/checkEmail")
	public Map<String, Boolean> checkEmailAvailabilty(@ModelAttribute Account account) {
		Map<String, Boolean> response = new HashMap<>();
		boolean isAvailable = !accountService.isEmailExists(account);
		response.put("available", isAvailable);
		return response;
	}
}
