package xyz.itwill.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import xyz.itwill.security.CustomAccountDetails;

@Controller
@RequestMapping("/donation")
@RequiredArgsConstructor
public class WishController {

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/wishlist")
	public String getWish() {
		return "donation/wishlist";
	}
}