package xyz.itwill.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import xyz.itwill.dto.SecurityAuth;
import xyz.itwill.dto.SecurityUsers;

// 인증된 사용자 정보를 저장하기 위한 클래스
// ▶ UserDetails 인터페이스를 상속받아 작성
// ▶ UserDetails 인터페이스를 상속받은 User 클래스를 상속받아 작성 또한 가능

@Data
public class CustomUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;

	// 인증된 사용자의 정보를 저장할 필드를 선언
	private String userid;
	private String password;
	private String name;
	private String email;
	private String enabled;
	// private List<SecurityAuth> securityAuths; ◀ 실제로 권한이 저장되지 않음
	// 인증된 사용자의 권한 정보가 저장될 필드 선언
	private List<GrantedAuthority> securityAuthList;

	// 매개 변수로 전달받은 SecurityUsers 객체의 필드값을 CustomUserDetails 클래스의 필드에 저장
	public CustomUserDetails(SecurityUsers users) {
		this.userid = users.getUserid();
		this.password= users.getPasswd();
		this.name= users.getName();
		this.email= users.getEmail();
		this.enabled= users.getEnabled();
		
		// 검색된 사용자의 권한(String 객체)은 GrantedAuthority 객체로 생성하여 저장
		this.securityAuthList = new ArrayList<GrantedAuthority>();
		for(SecurityAuth auth : users.getSecurityAuthList()) {
			securityAuthList.add(new SimpleGrantedAuthority(auth.getAuth()));
		}
	}
	
	// 인증된 사용자의 권한 정보를 반환하는 메소드
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return securityAuthList;
	}

	// 인증된 사용자의 비밀번호를 반환하는 메소드
	@Override
	public String getPassword() {
		return password;
	}

	// 인증된 사용자의 식별자(아이디)를 반환하는 메소드
	@Override
	public String getUsername() {
		return userid;
	}

	// 인증 사용자의 계정 유효 기간 관련 상태 정보를 반환하는 메소드
	// ▶ false: 계정 유효 기간 만료 / true: 계정 유효 기간 안만료
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 인증 사용자의 계정 잠금 관련 상태 정보를 반환하는 메소드
	// ▶ false: 계정 잠금 상태 / true: 계정 해제 상태
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 인증 사용자의 비밀번호 유효 기간 관련 상태 정보를 반환하는 메소드
	// ▶ false: 비밀번호 유효 기간 만료 / true: 비밀번호 유효 기간 안만료
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 인증 사용자의 활성화 상태 정보를 반환하는 메소드
	// ▶ false: 사용자 비활성화 상태 / true: 사용자 활성화 상태
	@Override
	public boolean isEnabled() {
		if(enabled.equals("0")) {
			return false;
		} else {
			return true;
		}
	}
}