package xyz.itwill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xyz.itwill.dto.Account;
import xyz.itwill.exception.UserinfoNotFoundException;
import xyz.itwill.service.AccountService;

@RestController
public class SocialLoginErrorController {
	@Autowired
	private AccountService accountService;
	
    @GetMapping("/getUserInfo")
    public ResponseEntity<Account> getUserInfo(@RequestParam("id") String id) throws UserinfoNotFoundException {
    	Account account = accountService.getAccount(id);
    	
    	return ResponseEntity.ok(account);
    }
}		

