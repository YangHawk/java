package xyz.itwill.controller;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/donation")
@RequiredArgsConstructor
public class ShopController {
	
	@RequestMapping(value = "/shopfull", method = RequestMethod.GET)
	public String shop(@RequestParam String select, Model model, HttpSession session) {
		
		model.addAttribute("select", select);
		return "donation/shopfull";
	}
}
