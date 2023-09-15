package xyz.itwill.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.Validator;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Account;
import xyz.itwill.exception.UserinfoNotFoundException;
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
	
	@GetMapping("/account_detail")
	   public Map<String, Object> getMyAccount(
			   @RequestParam int questionPageNum, 
			   @RequestParam int donationPageNum, 
			   @RequestParam int wishPageNum,
			   Model model,
			   HttpSession session
			   ) {
		   Account loginAccount = (Account)session.getAttribute("loginAccount");
		   if (loginAccount == null) {
				return null;
		   }
		   model.addAttribute("loginAccount", loginAccount);
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
	
	@PutMapping("/account_modify")
	public String modifyAcocunt(@RequestBody Account account, HttpSession session) throws UserinfoNotFoundException {
		Account loginAccount = (Account)session.getAttribute("loginAccount");
		if(loginAccount == null) {
			return "error";
		}
		
		loginAccount.setName(account.getName());
		loginAccount.setEmail(account.getEmail());
		loginAccount.setPhone(account.getPhone());
		loginAccount.setAddress1(account.getAddress1());
		loginAccount.setAddress2(account.getAddress2());
		loginAccount.setAddress3(account.getAddress3());
		
		accountService.modifyAccount(account);
		
		return "success";
	}
	
	@DeleteMapping("/account_remove")
	public String removeAccount(HttpSession session) throws UserinfoNotFoundException {
		Account loginAccount = (Account)session.getAttribute("loginAccount");
		if(loginAccount == null) {
			return "error";
		}
		String id = loginAccount.getId();
		accountService.removeAccount(id);
		session.invalidate();
		return "success";
	}
	
	@PutMapping("/changePassword")
	public String updatePassword(@RequestBody @Valid Account account, HttpSession session) throws UserinfoNotFoundException {
		Account loginAccount = accountService.getAccount(account.getId());
		if(loginAccount == null) {
			return "error1";
		}
		
	   	if(!BCrypt.checkpw(account.getCurrentPassword(), loginAccount.getPassword())) {
	   		return "error2";
	   	}
	   	// 새 비밀번호와 새 비밀번호 확인이 일치하는지 확인
		if(!account.getNewPassword().equals(account.getConfirmPassword())) {
			return "error3";
		}
		
		String hashedNewPassword = BCrypt.hashpw(account.getNewPassword(), BCrypt.gensalt());
		loginAccount.setPassword(hashedNewPassword);
		accountService.modifyPassword(loginAccount);
		return "success";
	}
}