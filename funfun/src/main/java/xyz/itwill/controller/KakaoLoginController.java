package xyz.itwill.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.log4j.spi.LoggerFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.scribejava.core.model.OAuth2AccessToken;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Account;
import xyz.itwill.dto.AccountAuth;
import xyz.itwill.exception.ExistsUserinfoException;
import xyz.itwill.exception.UserinfoNotFoundException;
import xyz.itwill.security.CustomAccountDetails;
import xyz.itwill.security.KakaoLoginBean;
import xyz.itwill.service.AccountService;

@Controller
@RequestMapping("kakao")
@RequiredArgsConstructor
public class KakaoLoginController {
	private final KakaoLoginBean kakaoLoginBean;
	private final AccountService accountService;

	// 로그인 페이지를 요청하기 위한 요청 처리 메소드
	@RequestMapping("/login")
	public String kakaologin(HttpSession session) throws UnsupportedEncodingException {
		String kakaoAuthUrl = kakaoLoginBean.getAuthorizationUrl(session);
		return "redirect:" + kakaoAuthUrl;
	}

	// 로그인 성공시 Callback URL 페이지를 처리하기 위한 요청 처리 메소드
	@RequestMapping("/callback")
	public String kakaologin(@RequestParam String code, @RequestParam String state, HttpSession session)
			throws IOException, ParseException, ExistsUserinfoException, UserinfoNotFoundException {
		// 네이버 로그인 사용자에 대한 접근 토큰을 반환하는 메소드 호출하여 사용자 접근 토큰 저장
		OAuth2AccessToken accessToken = kakaoLoginBean.getAccessToken(session, code, state);

		// 접근 토큰을 이용하여 로그인 사용자의 프로필을 반환하는 메소드를 호출하여 사용자 프로필(JSON)을 저장
		String apiResult = kakaoLoginBean.getUserProfile(accessToken);

		JSONParser parser = new JSONParser();
		Object object = parser.parse(apiResult);
		JSONObject responseObject = (JSONObject) object;

		Long id = (Long) responseObject.get("id");
		String email = (String) responseObject.get("email");
		// String genderStr = (String)responseObject.get("gender");
		String birth = (String) responseObject.get("birth");

		AccountAuth auth = new AccountAuth();
		auth.setId("kakao_" + id);
		auth.setAuth("ROLE_USER");

		List<AccountAuth> authList = new ArrayList<AccountAuth>();
		authList.add(auth);

		Account account = new Account();
		account.setId("kakao_" + id);
		account.setPassword(UUID.randomUUID().toString());
		account.setEmail(email);
		/*
		 * if(genderStr.equals("female")) { account.setGender(1); } else {
		 * account.setGender(0); }
		 */
		account.setBirth(birth);

		account.setAccountAuthList(authList);
		account.setEnabled("1");

		Account existAccount = new Account();

		if (accountService.getAccount("kakao_" + id) == null) {
			accountService.addAccount(account, "ROLE_USER");

			account.setIdx(accountService.getAccount("kakao_" + id).getIdx());

			CustomAccountDetails customAccountDetails = new CustomAccountDetails(account);
			Authentication authentication = new UsernamePasswordAuthenticationToken(customAccountDetails, null,
					customAccountDetails.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authentication);

			System.out.println(apiResult);
			return "redirect:/";
		} else {
			existAccount = accountService.getAccount("kakao_" + id);

			CustomAccountDetails customAccountDetails = new CustomAccountDetails(existAccount);
			Authentication authentication = new UsernamePasswordAuthenticationToken(customAccountDetails, null,
					customAccountDetails.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authentication);

			System.out.println(apiResult);
			return "redirect:/";
		}
	}
}
