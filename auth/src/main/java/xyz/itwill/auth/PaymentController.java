package xyz.itwill.auth;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.itwill.dto.Payment;

@Controller
@RequestMapping("/payment")
public class PaymentController {
	@RequestMapping(value = "/pay", method = RequestMethod.GET)
	public String pay() {
		return "pay";
	}

	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	@ResponseBody
	public String pay(@RequestBody Payment payment, HttpSession session) {
		session.setAttribute(payment.getMerchantUid(), payment.getAmount());
		return "ok";
	}
}
