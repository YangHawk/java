package xyz.itwill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/donation")
@RequiredArgsConstructor
public class PayController {
	@RequestMapping(value = "/pay", method = RequestMethod.GET)
	public String pay() {
		return "donation/pay";
	}
}