package xyz.itwill.service;

import java.util.Map;

import xyz.itwill.dto.Account;
import xyz.itwill.dto.AccountAuth;
import xyz.itwill.exception.ExistsUserinfoException;
import xyz.itwill.exception.LoginAuthFailException;
import xyz.itwill.exception.UserinfoNotFoundException;
import xyz.itwill.security.CustomAccountDetails;

public interface AccountService {
	void addAccount(Account account, String accountRole) throws ExistsUserinfoException;

	void addAccountAuth(AccountAuth auth);
	
	void modifyAccount(Account account) throws UserinfoNotFoundException;
	
	void modifyAccountByAdmin(Account account) throws UserinfoNotFoundException;

	void removeAccount(String id) throws UserinfoNotFoundException;

	Account getAccount(String id) throws UserinfoNotFoundException;

	Map<String, Object> getAccountList(int pageNum, int pageSize, String selectKeyword);

	Account loginAuth(Account account) throws LoginAuthFailException; // 인증 처리 - 인증 실패 시 예외 발생

	// 아이디 찾기
	Account idSearch(Account account);

	// 비밀번호 찾기
	String findPassword(Account account) throws Exception;

	// 새 비밀번호 업데이트
	void modifyPassword(Account account);

	// 아이디 중복 체크
	boolean isIdExists(Account account);
	
	// 이메일 중복 체크
	boolean isEmailExists(Account account);
	
	//소셜로그인 회원 비밀번호 변경 방지
	Account getUserInfo(String id);
}