package xyz.itwill.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

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
import xyz.itwill.security.NaverLoginBean;
import xyz.itwill.service.AccountService;

//네이버의 로그인 OpenAPI를 사용하여 간편 로그인하는 방법
//1.네이버 개발자 센터에 OpenAPI 신청 기능을 사용하여 어플리케이션 등록
//=> 어플리케이션 이름, 사용자 API, 서비스 환경(서비스 URL, 콜백 URL)등을 입력하여 등록
//2.내 어플리케이션에서 Client Id, Client Secret  확인
//3.네이버 로그인 이미지를 다운로드 받아 출력하고 서비스 URL로 링크 제공
//4.scribejava-apis 라이브러리와 json-simple 라이브러리 프로젝트에 빌드 처리
//5.로그인 관련 API를 요청하기 위한 클래스 작성 - NaverLoginApi 클래스와 NaverLoginBean 클래스
//6.요청 처리 메소드에서 로그인 관련 API를 요청하기 위한 클래스의 메소드를 호출하여 인증을 이용한 권한 처리

@Controller
@RequestMapping("/naver")
@RequiredArgsConstructor
public class NaverLoginController {
	private final NaverLoginBean naverLoginBean;
	private final AccountService accountService;

	// 네이버 로그인 페이지를 요청하기 위한 요청 처리 메소드
	@RequestMapping("/login")
	public String login(HttpSession session) throws UnsupportedEncodingException {
		String naverAuthUrl = naverLoginBean.getAuthorizationUrl(session);
		return "redirect:" + naverAuthUrl;
	}

	// 네이버 로그인 성공시 Callback URL 페이지를 처리하기 위한 요청 처리 메소드
	@RequestMapping("/callback")
	public String login(@RequestParam String code, @RequestParam String state, HttpSession session)
			throws IOException, ParseException, ExistsUserinfoException, UserinfoNotFoundException {
		// 네이버 로그인 사용자에 대한 접근 토큰을 반환하는 메소드 호출하여 사용자 접근 토큰 저장
		OAuth2AccessToken accessToken = naverLoginBean.getAccessToken(session, code, state);

		// 접근 토큰을 이용하여 로그인 사용자의 프로필을 반환하는 메소드를 호출하여 사용자 프로필(JSON)을 저장
		String apiResult = naverLoginBean.getUserProfile(accessToken);

		System.out.println(apiResult);

		JSONParser parser = new JSONParser();
		Object object = parser.parse(apiResult);
		JSONObject jsonObject = (JSONObject) object;

		JSONObject responseObject = (JSONObject) jsonObject.get("response");
		String id = (String) responseObject.get("id");
		String name = (String) responseObject.get("name");
		String phone = (String) responseObject.get("phone");
		String email = (String) responseObject.get("email");
		String genderStr = (String) responseObject.get("gender");
		String birth = (String) responseObject.get("birth");

		// System.out.println("aaaa");

		AccountAuth auth = new AccountAuth();
		auth.setId("naver_" + id);
		auth.setAuth("ROLE_USER");

		List<AccountAuth> authList = new ArrayList<AccountAuth>();
		authList.add(auth);

		Account account = new Account();
		account.setId("naver_" + id);
		account.setPassword(UUID.randomUUID().toString());
		account.setName(name);
		account.setPhone(phone);
		account.setEmail(email);
		// gender를 String으로 받아와서 int로 변환하여 서버에 저장
		if (genderStr.equals("F")) {
			account.setGender(1);
		} else {
			account.setGender(0);
		}
		account.setBirth(birth);

		account.setAccountAuthList(authList);
		account.setEnabled("1");

		Account existAccount = new Account();

		if (accountService.getAccount("naver_" + id) == null) { // 계정이 없는 상태라면
			accountService.addAccount(account, "ROLE_USER");

			account.setIdx(accountService.getAccount("naver_" + id).getIdx());

			CustomAccountDetails customAccountDetails = new CustomAccountDetails(account);
			Authentication authentication = new UsernamePasswordAuthenticationToken(customAccountDetails, null,
					customAccountDetails.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authentication);

			System.out.println(apiResult);
			return "redirect:/";

		} else { // 계정이 이미 있는 상태라면
			existAccount = accountService.getAccount("naver_" + id);

			CustomAccountDetails customAccountDetails = new CustomAccountDetails(existAccount);
			Authentication authentication = new UsernamePasswordAuthenticationToken(customAccountDetails, null,
					customAccountDetails.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authentication);

			System.out.println(apiResult);
			return "redirect:/";
		}
	}

}
