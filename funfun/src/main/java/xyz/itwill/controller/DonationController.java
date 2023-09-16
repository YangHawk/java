package xyz.itwill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Donation;
import xyz.itwill.exception.FestivalinfoNotFoundException;
import xyz.itwill.service.DonationService;
import xyz.itwill.service.FestivalService;

@Controller
@RequestMapping("/donation")
@RequiredArgsConstructor
public class DonationController{
	private final DonationService donationService;
	private final FestivalService festivalService;
	
	@PostMapping(value = "/pay")
	public String donationDisplay(@ModelAttribute Donation donation, Model model) throws FestivalinfoNotFoundException {
		System.out.println(donation.getMoney());
		model.addAttribute("money", donation.getMoney());
		model.addAttribute("festivalinfo", festivalService.getFestival(donation.getFestivalIdx()));
		return "donation/pay";
	}

	@RequestMapping(value = "/pay_update", method = RequestMethod.POST)
	public String donationAdd(@ModelAttribute Donation donation, RedirectAttributes redirectAttributes) {
		if(donation == null) {
			redirectAttributes.addFlashAttribute("message", "결제 오류! 다시 시도해주세요.");
			return "redirect:/donation/pay_completion";
		}
		
		donationService.addDonation(donation);
		redirectAttributes.addFlashAttribute("message", "결제가 완료되었습니다.후원 감사드립니다.");
		return "redirect:/donation/pay_completion";
	}
	
	@GetMapping(value = "/pay_completion")
	public String donationCompletion(@ModelAttribute("message") String message, Model model) {
		model.addAttribute("message", message);
		return "donation/pay_completion";
	}
}
