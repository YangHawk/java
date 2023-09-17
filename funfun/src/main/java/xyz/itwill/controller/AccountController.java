package xyz.itwill.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Account;
import xyz.itwill.exception.ExistsUserinfoException;
import xyz.itwill.service.AccountService;



@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
   private final AccountService accountService;
   
   //회원 등록 페이지를 GET 요청시에 보여주는 메소드
   @RequestMapping(value = "/register", method = RequestMethod.GET)
   public String register(HttpSession session) {
      return "account/register"; // account/register.jsp 뷰로 이동
   }
   
   
   // 회원 등록을 처리하는 메소드 (POST 요청)
   @RequestMapping(value = "/register", method = RequestMethod.POST)
   public String register(@ModelAttribute Account account, @RequestParam String accountRole) throws ExistsUserinfoException {
      System.out.println("register");
      accountService.addAccount(account, accountRole); //회원 등록 서비스 실행
      return "redirect:/account/login"; // 회원 등록 후 로그인 페이지로 리다이렉트
   }

   // 로그인 페이지를 GET 요청 시에 보여주는 메소드
   @RequestMapping(value = "/login", method = RequestMethod.GET)
   public String login() {
      return "account/login"; // account/login.jsp 뷰로 이동
   }
   
   /*
   //로그인 시 아이디랑 비밀번호가 서버에 등록되어있는지 확인하는 메소드
   @PostMapping("/login")
   public ResponseEntity<String> login(@RequestBody Account account,
         @RequestParam(value = "autoLogin", required = false) boolean autoLogin,
         HttpSession session) {
      try {
         Account authAccount = accountService.loginAuth(account);
         
         if(authAccount != null) {
            session.setAttribute("loginAccount", authAccount); // 세션에 사용자 정보 저장
            
            if(autoLogin) {
               //자동 로그인 처리
            }
            return new ResponseEntity<>("로그인 성공 메시지", HttpStatus.OK);
         } else {
            return new ResponseEntity<>("아이디 또는 비밀번호를 확인해주세요.", HttpStatus.UNAUTHORIZED);
         }
      } catch (LoginAuthFailException e) {
         return new ResponseEntity<>("아이디 또는 비밀번호를 확인해주세요.", HttpStatus.UNAUTHORIZED);
      }
   }
   */
   
   /*
   //로그아웃 처리 메소드
   @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
   public String logout(HttpServletRequest request){ 
      HttpSession session = request.getSession(false);
      
      if(session != null) {
         session.invalidate();
      }
      
      return "redirect:/";
   }
   */
   
   // 아이디 및 비밀번호 찾기 페이지 요청 처리
    @GetMapping("/find")
    public String findPage() {
        return "account/find"; // find.jsp 뷰로 이동
    }
   
    @GetMapping("/find-id")
    public String findIdPage() {
        return "find-id"; // find-id.jsp 또는 해당하는 view의 이름으로 수정
    }
    
   //아이디 찾기 실행
    @RequestMapping(value = "/find-id", method = RequestMethod.POST)
    public String findId(HttpServletRequest request, Model model,
            @RequestParam(required = true, value = "name") String name,
            @RequestParam(required = true, value = "email") String email,
            Account account) {

        try {
            account.setName(name);
            account.setEmail(email);

            Account foundAccount = accountService.idSearch(account);

            if (foundAccount != null) {
                model.addAttribute("search", foundAccount);
            } else {
                model.addAttribute("msg", "해당하는 아이디가 없습니다.");
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            model.addAttribute("msg", "오류가 발생되었습니다.");
        }

        return "account/find-id"; // find-id 페이지로 이동
    }
    
    //비밀번호 찾기 관련 메소드
    @RequestMapping(value = "/find-password", method = RequestMethod.GET)
     public void findPwGET() throws Exception {
     }
 
    
     @RequestMapping(value = "find-password", method = RequestMethod.POST)
     public void findPwPost(@ModelAttribute Account account) throws Exception {
        accountService.findPassword(account);
     }
     
     @RequestMapping(value = "/my_festival", method = RequestMethod.GET)
     public String showMyFestival() {
        return "account/my_festival";
     }
}