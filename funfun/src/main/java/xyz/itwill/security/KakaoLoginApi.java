package xyz.itwill.security;

import com.github.scribejava.core.builder.api.DefaultApi20;

// 로그인 관련 API 정보를 제공하기 위한 클래스
// ▶ DefaultApi20 클래스를 상속받아 작성
public class KakaoLoginApi extends DefaultApi20 {
	protected KakaoLoginApi() {

	}

	private static class InstanceHolder {
		private static final KakaoLoginApi INSTANCE = new KakaoLoginApi();
	}

	public static KakaoLoginApi instance() {
		return InstanceHolder.INSTANCE;
	}

	// 사용자 접근 토큰을 제공받기 위한 API의 URL 주소를 반환하는 메소드
	@Override
	public String getAccessTokenEndpoint() {
		return "https://kauth.kakao.com/oauth/token";
	}
	
	// 로그인 처리를 위한 API의 URL 주소를 반환하는 메소드
	@Override
	protected String getAuthorizationBaseUrl() {
		return "https://kauth.kakao.com/oauth/authorize";
	}
	

}
