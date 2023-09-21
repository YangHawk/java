package xyz.itwill.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Account;
import xyz.itwill.exception.UserinfoNotFoundException;
import xyz.itwill.security.CustomAccountDetails;
import xyz.itwill.service.AccountService;
import xyz.itwill.service.DonationService;
import xyz.itwill.service.QuestionService;
import xyz.itwill.service.WishService;

@RestController
@RequiredArgsConstructor
public class MyAccountRestController {
	private final WishService wishService;
	private final QuestionService questionService;
	private final DonationService donationService;
	private final AccountService accountService;

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/account_detail")
	public Map<String, Object> getMyAccount(@RequestParam int questionPageNum, @RequestParam int donationPageNum,
			@RequestParam int wishPageNum, Authentication authentication) {
		CustomAccountDetails loginAccount = (CustomAccountDetails)authentication.getPrincipal();
		Map<String, Object> myWish;
		myWish = wishService.getMyWishList(wishPageNum, loginAccount.getId());
		Map<String, Object> myQuestion;
		myQuestion = questionService.getMyQuestionList(questionPageNum, loginAccount.getId());
		Map<String, Object> myDonation;
		myDonation = donationService.getMyDonationList(donationPageNum, loginAccount.getId());
		Map<String, Object> myMap = new HashMap<String, Object>();
		myMap.put("myWish", myWish);
		myMap.put("myQuestion", myQuestion);
		myMap.put("myDonation", myDonation);

		return myMap;
	}

	@PreAuthorize("isAuthenticated()")
	@PutMapping("/account_modify")
	public String modifyAcocunt(@RequestBody Account account, Authentication authentication, HttpServletRequest request, HttpServletResponse response) throws UserinfoNotFoundException {
		Account loginAccount = accountService.getAccount(account.getId());
		if(loginAccount == null) {
			return "error";
		}
		loginAccount.setName(account.getName());
		loginAccount.setEmail(account.getEmail());
		loginAccount.setBirth(account.getBirth());
		loginAccount.setPhone(account.getPhone());
		loginAccount.setAddress1(account.getAddress1());
		loginAccount.setAddress2(account.getAddress2());
		loginAccount.setAddress3(account.getAddress3());
		loginAccount.setIdx(account.getIdx());
		accountService.modifyAccount(loginAccount);
		
		new SecurityContextLogoutHandler().logout(request, response, authentication);
		
		return "success";
	}

	@PreAuthorize("isAuthenticated()")
	@PutMapping("/account_remove")
	 public String removeAccount(@RequestParam String id, Authentication authentication, HttpServletRequest request, HttpServletResponse response) throws UserinfoNotFoundException {
        accountService.removeAccount(id);

        new SecurityContextLogoutHandler().logout(request, response, authentication);
        
        return "success";
    }

	@PreAuthorize("isAuthenticated()")
	@PutMapping("/changePassword")
	public String updatePassword(@RequestBody @Valid Account account, Authentication authentication, HttpServletRequest request, HttpServletResponse response)
			throws UserinfoNotFoundException {
		Account loginAccount = accountService.getAccount(account.getId());
		
		if (!BCrypt.checkpw(account.getCurrentPassword(), loginAccount.getPassword())) {
			return "비밀번호가 같지 않습니다.";
		}

		// 새 비밀번호와 새 비밀번호 확인이 일치하는지 확인
		if (!account.getNewPassword().equals(account.getConfirmPassword())) {
			return "새 비밀번호 확인을 다시 해주십시오";
		}

		String hashedNewPassword = BCrypt.hashpw(account.getNewPassword(), BCrypt.gensalt());
		loginAccount.setIdx(account.getIdx());		
		loginAccount.setPassword(hashedNewPassword);
		accountService.modifyPassword(loginAccount);
		
		new SecurityContextLogoutHandler().logout(request, response, authentication);

		return "success";
	}
}