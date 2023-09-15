package xyz.itwill.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Account;
import xyz.itwill.service.WishService;

@Controller
@RequestMapping("/donation")
@RequiredArgsConstructor
public class WishController {
   private final WishService wishService;
   
   @GetMapping("/wishlist")
   public String getWish(HttpSession session, Model model) {
      Account loginAccount = (Account) session.getAttribute("loginAccount");
         if(loginAccount == null) {
            return "redirect:/account/login";
         } 
       
         model.addAttribute("loginAccount", loginAccount);
      return "donation/wishlist";
   }
}