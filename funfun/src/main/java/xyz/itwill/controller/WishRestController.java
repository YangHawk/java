package xyz.itwill.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Account;
import xyz.itwill.dto.Wish;
import xyz.itwill.service.WishService;

@RestController
@RequiredArgsConstructor
public class WishRestController {
	private final WishService wishService;
	
	@PutMapping("/wish_add")
	public String addWish(@RequestBody Wish wish, HttpSession session) {
		Account loginAccount = (Account)session.getAttribute("loginAccount");
		if (loginAccount == null) {
			return "error";
		}
		wishService.addWish(wish);
		return "success";
	}
	
	@DeleteMapping("/wish_remove")
	public String removeWish(@RequestBody Wish wish, HttpSession session) {
		Account loginAccount = (Account)session.getAttribute("loginAccount");
		if (loginAccount == null) {
			return "error";
		}
		wishService.removeWish(wish.getFestivalIdx(), wish.getAccountId());
		return "success";
	}
	
	@GetMapping("/wish_list")
	public Map<String, Object> getWishlist(@RequestParam(defaultValue = "1") int pageNum, HttpSession session) {
		Account loginAccount = (Account)session.getAttribute("loginAccount");
		Map<String, Object> mywish;
		if(loginAccount == null) {
			mywish = null;
		}else {
			
			mywish = wishService.getMyWishList(pageNum, loginAccount.getId());
		}
		return mywish;
	}

}
