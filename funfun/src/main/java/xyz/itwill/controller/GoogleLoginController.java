package xyz.itwill.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.json.simple.parser.ParseException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.scribejava.core.model.OAuth2AccessToken;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Account;
import xyz.itwill.dto.AccountAuth;
import xyz.itwill.exception.ExistsUserinfoException;
import xyz.itwill.exception.UserinfoNotFoundException;
import xyz.itwill.security.CustomAccountDetails;
import xyz.itwill.security.GoogleLoginBean;
import xyz.itwill.service.AccountService;

@Controller
@RequestMapping("/google")
@RequiredArgsConstructor
public class GoogleLoginController {
	private final GoogleLoginBean googleLoginBean;
	private final AccountService accountService;

	@RequestMapping("/login")
	public String googlelogin(HttpSession session) throws UnsupportedEncodingException {
		String googleAuthUrl = googleLoginBean.getAuthorizationUrl(session);
		return "redirect:" + googleAuthUrl;
	}

	@RequestMapping("/callback")
	public String googlelogin(@RequestParam String code, @RequestParam String state, HttpSession session)
			throws IOException, ParseException, ExistsUserinfoException, UserinfoNotFoundException, ParseException {
		OAuth2AccessToken accessToken = googleLoginBean.getAccessToken(session, code, state);

		String apiResult = googleLoginBean.getUserProfile(accessToken);

		JSONParser parser = new JSONParser();
		Object object = parser.parse(apiResult);
		JSONObject responseObject = (JSONObject) object;

		String id = (String) responseObject.get("id");
		String email = (String) responseObject.get("email");
		String name = (String) responseObject.get("name");
		// String genderStr = (String)responseObject.get("gender");
		// String birth = (String)responseObject.get("birth");

		AccountAuth auth = new AccountAuth();
		auth.setId("google_" + id);
		auth.setAuth("ROLE_USER");

		List<AccountAuth> authList = new ArrayList<AccountAuth>();
		authList.add(auth);

		Account account = new Account();
		account.setId("google_" + id);
		account.setName(name);
		account.setPassword(UUID.randomUUID().toString());
		account.setEmail(email);
		

		account.setAccountAuthList(authList);
		account.setEnabled("1");

		Account existAccount = new Account();
		
		if (accountService.getAccount("google_" + id) == null) { // 계정이 없는 상태라면
				
			if(accountService.isEmailExists(account)) { // 이메일 중복 체크
				session.setAttribute("SocialLoginErrorMessage", "이메일이 중복되었습니다.");
				throw new AccessDeniedException("이메일이 중복되었습니다.");
			}
			
			accountService.addAccount(account, "ROLE_USER");

			account.setIdx(accountService.getAccount("google_" + id).getIdx());
			
			CustomAccountDetails customAccountDetails = new CustomAccountDetails(account);
			Authentication authentication = new UsernamePasswordAuthenticationToken(customAccountDetails, null,
					customAccountDetails.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authentication);

			System.out.println(apiResult);
			return "redirect:/";
			
		} else { // 계정이 이미 있는 상태라면
			existAccount = accountService.getAccount("google_" + id);
			
			CustomAccountDetails customAccountDetails = new CustomAccountDetails(existAccount);
			
			if (!customAccountDetails.isEnabled()) {
				session.setAttribute("SocialLoginErrorMessage", "삭제된 계정입니다.");
		           throw new DisabledException("삭제된 계정입니다.");
	        }
			
			Authentication authentication = new UsernamePasswordAuthenticationToken(customAccountDetails, null,
					customAccountDetails.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authentication);

			System.out.println(apiResult);
			return "redirect:/";
		}
	}
}
