package xyz.itwill.auth;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.scribejava.core.model.OAuth2AccessToken;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.SecurityAuth;
import xyz.itwill.dto.SecurityUsers;
import xyz.itwill.security.CustomUserDetails;
import xyz.itwill.security.NaverLoginBean;
import xyz.itwill.service.SecurityUsersService;

// 네이버 로그인 OpenAPI를 사용하여 간편 로그인하는 방법
// 1. 네이버 개발자 센터에 OpenAPI 신청 기능을 사용하여 어플리케이션 등록
// ▶ 어플리케이션 이름 / 사용자 API / 서비스 환경(서비스 URL / 콜백 URL) 등을 입력하여 등록
// 2. 내 어플리케이션에서 Client_Id / Client_Secret 확인
// 3. 네이버 로그인 이미지를 다운로드받아 출력하고 서비스 URL로 링크 제공
// 4. scribejava-apis / json-simple 라이브러리를 프로젝트에 빌드 처리 - pom.xml
// 5. 로그인 관련 API를 요청하기 위한 클래스를 작성 - NaverLoginApi / NaverLoginBean 클래스
// 6. 요청 처리 메소드(Controller 메소드)에서 NaverLoginBean 클래스의 메소드를 호출하여 인증을 이용한 권한 처리

@Controller
@RequestMapping("/naver")
@RequiredArgsConstructor
public class NaverLoginController {
	private final NaverLoginBean naverLoginBean;
	private final SecurityUsersService securityUsersService;

	// 네이버 로그인 페이지를 요청하기 위한 요청 처리 메소드
	@RequestMapping("/login")
	public String login(HttpSession session) throws UnsupportedEncodingException {
		/*
		
		// 네이버에서만 쓰는 방법
		String clientId = "DdUGl2N7tmnfkPjZQO_T";// 애플리케이션 클라이언트 아이디값";
		String redirectURI = URLEncoder.encode("http://localhost:8000/auth/naver/callback", "UTF-8");
		SecureRandom random = new SecureRandom();
		String state = new BigInteger(130, random).toString();
		String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
		apiURL += "&client_id=" + clientId;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&state=" + state;
		session.setAttribute("state", state);
		return "redirect:" + apiURL;
		
		 */
		
		// 권장하는 방법
		String naverAuthUrl = naverLoginBean.getAuthorizationUrl(session);
		return "redirect:" + naverAuthUrl;
	}
	
	// 네이버 로그인 성공 시 Callback URL 페이지를 처리하기 위한 요청 처리 메소드
	@RequestMapping("/callback")
	public String callBack(@RequestParam String code, @RequestParam String state, HttpSession session)
			throws IOException, ParseException {
		// 네이버 로그인 사용자에 대한 접근 토큰을 반환하는 메소드를 호출하여 접근 토큰을 저장
		OAuth2AccessToken accessToken = naverLoginBean.getAccessToken(session, code, state);

		// 로그인 사용자의 프로필을 반환하는 메소드를 호출하여 사용자 프로필(JSON)을 저장
		String apiResult = naverLoginBean.getUserProfile(accessToken);
		// System.out.println(apiResult); 하면
		// {"resultcode":"00","message":"success","response":{"id":"RSwCqD8sQt1rsYyJYAbH0OXvOvHyhjcsIGbVvvQOem4","email":"jinlee0063@jr.naver.com","name":"\uc774\ub3d9\ud615"}}

		// ※JSON 형태의 문자열을 JSON 객체로 변환※
		// JSONParser 객체: JSON 형식의 문자열을 JSON 객체로 변환하는 기능을 제공하는 객체
		JSONParser parser = new JSONParser();
		// JSONParser.parse(String json): JSON 형식의 문자열을 Object 객체로 변환하는 메소드
		Object object = parser.parse(apiResult);
		// Object 객체를 JSONObject 객체로 변환하여 저장
		JSONObject jsonObject = (JSONObject) object;
		
		// JSON 객체에 저장된 값을 제공받아 저장 - 파싱(Parsing)
		// JSONObject.get(String name): JSONObject 객체에 저장된 값(또는 객체)을 반환하는 메소드
		// ▶ Object 타입으로 값(객체)이 반환되므로 반드시 형변환하여 저장
		JSONObject responseObject = (JSONObject) jsonObject.get("response");
		String id = (String) responseObject.get("id");
		String name = (String) responseObject.get("name");
		String email = (String) responseObject.get("email");

		// 전달받은 네이버 로그인 사용자 프로필의 값을 사용하여 Java 객체의 필드값으로 저장
		SecurityAuth auth = new SecurityAuth();
		auth.setUserid("naver_" + id);
		auth.setAuth("ROLE_USER");

		List<SecurityAuth> authList = new ArrayList<SecurityAuth>();
		authList.add(auth);

		SecurityUsers users = new SecurityUsers();
		users.setUserid("naver_" + id);
		users.setPasswd(UUID.randomUUID().toString());
		users.setName(name);
		users.setEmail(email);
		users.setEnabled("1");
		users.setSecurityAuthList(authList);

		// 네이버 로그인 사용자의 정보를 SECURITY_USERS 테이블과 SECURITY_AUTH 테이블에 저장
		if (securityUsersService.getSecurityUsers(users.getUserid()) == null) {
			securityUsersService.addSecurityUsers(users);
			securityUsersService.addSecurityAuth(auth);
		}

		// 네이버 로그인 사용자 정보를 사용하여 UserDetails 객체(로그인 사용자)를 생성하여 저장
		CustomUserDetails customUserDetails = new CustomUserDetails(users);
		
		// UsernamePasswordAuthenticationToken 객체를 생성하여 Spring Security가 사용 가능한 인증 사용자로 등록 처리
		// UsernamePasswordAuthenticationToken 객체: 인증 성공한 사용자를 Spring Security가 사용 가능한 인증 사용자로 등록 처리하는 객체
		Authentication authentication = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
		
		// SecurityContextHolder 객체: 인증 사용자의 권한 관련 정보를 저장하기 위한 객체
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return "redirect:/";
	}
}