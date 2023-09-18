package xyz.itwill.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Donation;
import xyz.itwill.dto.Expect;
import xyz.itwill.security.CustomAccountDetails;
import xyz.itwill.service.DonationService;
import xyz.itwill.service.ExpectService;

@RestController
@RequiredArgsConstructor
public class ExpectController {
	private final ExpectService expectService;
	private final DonationService donationService;

	//festival_idx를 전달받아 해당 영화제의 기대평을 검색하여 JSON 형식의 문자열로 응답하는 요청 처리 메소드
	@GetMapping(value="/expect_list/{festivalIdx}")
	public List<Expect> showExpectList(@PathVariable int festivalIdx, Model model) {
		List<Expect> expectinfo = expectService.getExpectList(festivalIdx);
        return expectinfo;
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value="/expect_add")
	public String addExpect(@RequestBody Expect expect){
		expect.setContent(HtmlUtils.htmlEscape(expect.getContent()));
		expectService.addExpect(expect);
		return "success";
	}
	
	@PreAuthorize("isAuthenticated() and principal.id eq #accountId")
	@GetMapping(value = "/expect_remove/{idx}/{accountId}")
	public String removeExpect(@PathVariable int idx, @PathVariable String accountId) {
		expectService.removeExpect(idx);
		return "success";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "/expect_form/{idx}", produces = "application/json")
	@ResponseBody
	public Map<String, Object> showExpectForm(@PathVariable int idx, Model model, Authentication authentication) {
	    Map<String, Object> response = new HashMap<>();
	    CustomAccountDetails loginAccount = null;
	    
		if (authentication != null && authentication.getPrincipal() instanceof CustomAccountDetails) {
		    loginAccount = (CustomAccountDetails) authentication.getPrincipal();
		}
		
	    if (loginAccount != null) {
	        List<Donation> donationinfo = donationService.getDonation(loginAccount.getId(), idx);
	        if (donationinfo != null && !donationinfo.isEmpty()) {
	            response.put("donationinfo", donationinfo);
	        }
	        Expect expectaccount = expectService.getExpectAccountId(loginAccount.getId(), idx);
	        if (expectaccount != null) {
	            response.put("expectAccountId", expectaccount);
	        }
	    }
	    return response;
	}
	
}
