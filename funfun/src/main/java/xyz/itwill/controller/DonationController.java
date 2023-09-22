package xyz.itwill.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Donation;
import xyz.itwill.dto.Festival;
import xyz.itwill.exception.FestivalinfoNotFoundException;
import xyz.itwill.service.DonationService;
import xyz.itwill.service.FestivalService;

@Controller
@RequestMapping("/donation")
@RequiredArgsConstructor
public class DonationController {
	private final DonationService donationService;
	private final FestivalService festivalService;

	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "/pay")
	public String donationDisplay(@ModelAttribute Donation donation, Model model) throws FestivalinfoNotFoundException {
		System.out.println(donation.getMoney());
		model.addAttribute("money", donation.getMoney());
		model.addAttribute("festivalinfo", festivalService.getFestival(donation.getFestivalIdx()));
		return "donation/pay";
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/pay_update", method = RequestMethod.POST)
	public String donationAdd(@ModelAttribute Donation donation, RedirectAttributes redirectAttributes) {
		if (donation == null) {
			redirectAttributes.addFlashAttribute("message", "결제 오류! 다시 시도해주세요.");
			return "error/error";
		}

		donationService.addDonation(donation);
		redirectAttributes.addFlashAttribute("message", "결제가 완료되었습니다.후원 감사드립니다.");
		return "redirect:/donation/pay_completion?idx=" + donation.getIdx() + "&festivalIdx="
				+ donation.getFestivalIdx();
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "/pay_completion")
	public String donationCompletion(@RequestParam int idx, @RequestParam int festivalIdx,
			@ModelAttribute("message") String message, Model model) throws FestivalinfoNotFoundException {
		Festival festival = festivalService.getFestival(festivalIdx);
		model.addAttribute("festival", festival);
		model.addAttribute("message", message);
		model.addAttribute("donation", donationService.getDonationOne(idx));
		return "donation/pay_completion";
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/real_pay", method = RequestMethod.POST)
	@ResponseBody
	public String pay(@RequestBody Donation donation, HttpSession session) {
		// 결제 관련 API를 이용하기 전에 결제 금액 검증을 위해 세션에 주문번호(이름)와 결제금액(값)을 저장
		session.setAttribute(donation.getMerchantUid(), donation.getMoney());
		return "ok";
	}

	// 결제 후 결제 금액을 검증하여 응답하는 요청 처리 메소드
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/real_complete", method = RequestMethod.POST)
	@ResponseBody
	public String complete(@RequestBody Donation donation, HttpSession session) {
		// 접근 토큰을 발급받아 저장
		String accessToken = donationService.getAccessToken(donation);

		// 토큰과 결제 고유값을 전달하여 결제 정보를 반환받아 저장
		Donation returnDonation = donationService.getRealDonation(accessToken, donation);

		// 세션에 저장된 결제 금액을 반환받아 저장
		String beforeMoney = (String) session.getAttribute(donation.getMerchantUid());
		session.removeAttribute(donation.getMerchantUid());

		// 결제된 결제 금액을 반환받아 저장
		String money = returnDonation.getMoney();

		if (beforeMoney.equals(money)) { // 검증 성공
			donationService.modifyDonation(donation); // 테이블에 결제 정보를 변경
			return "success";
		} else { // 검증 실패 - 결제 금액 불일치 - 위변조된 결제
			donationService.cancelDonation(accessToken, returnDonation);
			return "forgery";
		}
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/real_cancel", method = RequestMethod.POST)
	public String realCancel(@RequestBody Donation donation) {
		Donation cancelDonation = donationService.getDonationOne(donation.getIdx());
		
		String accessToken = donationService.getAccessToken(cancelDonation);
		
		donationService.cancelDonation(accessToken, cancelDonation);
		
		if(donationService.cancelDonation(accessToken, cancelDonation)=="success") {
			return "success";
		}
		return "error";
	}
}