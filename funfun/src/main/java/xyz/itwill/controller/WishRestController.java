package xyz.itwill.controller;

import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Wish;
import xyz.itwill.security.CustomAccountDetails;
import xyz.itwill.service.WishService;

@RestController
@RequiredArgsConstructor
public class WishRestController {
	private final WishService wishService;

	@PreAuthorize("isAuthenticated()")
	@PutMapping("/wish_add")
	public String addWish(@RequestBody Wish wish, Authentication authentication) {

		wishService.addWish(wish);
		return "success";
	}

	@PreAuthorize("isAuthenticated()")
	@DeleteMapping("/wish_remove")
	public String removeWish(@RequestBody Wish wish) {

		wishService.removeWish(wish.getFestivalIdx(), wish.getAccountId());
		return "success";
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/wish_list")
	public Map<String, Object> getWishlist(@RequestParam(defaultValue = "1") int pageNum,
			Authentication authentication) {
		CustomAccountDetails loginAccount = (CustomAccountDetails) authentication.getPrincipal();

		Map<String, Object> mywish;

		mywish = wishService.getMyWishList(pageNum, loginAccount.getId());
		return mywish;
	}

}