package xyz.itwill.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Wish;
import xyz.itwill.security.CustomAccountDetails;
import xyz.itwill.service.FestivalService;
import xyz.itwill.service.WishService;

@RestController
@RequestMapping("/donation")
@RequiredArgsConstructor
public class ShopRestController {
	private final FestivalService festivalService;
	private final WishService wishService;

	@GetMapping("/festival_list")
	public Map<String, Object> getFestivals(@RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "12") int pageSize, @RequestParam(defaultValue = "") String selectKeyword,
			@RequestParam(defaultValue = "all") String viewType) {
		if ("all".equals(viewType)) {
			// 모든 영화제 목록 반환
			return festivalService.getAllFestivalList(pageNum, pageSize, selectKeyword);
		} else if ("ongoing".equals(viewType)) {
			// 진행 중인 영화제 목록 반환
			return festivalService.getOngoingFestivalList(pageNum, pageSize, selectKeyword);
		} else if ("upcoming".equals(viewType)) {
			// 마감 임박 중인 영화제 목록 반환
			return festivalService.getUpcomingFestivalList(pageNum, pageSize, selectKeyword);
		} else if ("sponsor".equals(viewType)) {
			// 후원자순으로 정렬된 영화제 목록 반환
			return festivalService.getSponsorFestivalList(pageNum, pageSize, selectKeyword);
		} else if ("collected".equals(viewType)) {
			// 금액순으로 정렬된 영화제 목록 반환
			return festivalService.getCollectedFestivalList(pageNum, pageSize, selectKeyword);
		} else {
			// 지원하지 않는 viewType이면 기본값인 모든 영화제 목록 반환
			return festivalService.getAllFestivalList(pageNum, pageSize, selectKeyword);
		}
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/wish_list")
	@ResponseBody
	public List<Wish> getWishList(Authentication authentication) {
		CustomAccountDetails loginAccount = (CustomAccountDetails) authentication.getPrincipal();
		return wishService.getMyWishList(loginAccount.getId());
	}

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

}
