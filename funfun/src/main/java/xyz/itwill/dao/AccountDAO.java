package xyz.itwill.dao;

import java.util.List;
import java.util.Map;

import xyz.itwill.dto.Account;
import xyz.itwill.dto.AccountAuth;
import xyz.itwill.security.CustomAccountDetails;

public interface AccountDAO {
	int insertAccount(Account account);

	int insertAccountAuth(AccountAuth auth);
	
	int updateAccount(Account account);

	int updateAccountByAdmin(Account account);

	int deleteAccount(String id);

	Account selectAccount(String id);

	int selectAccountCount();

	List<Account> selectAccountList(Map<String, Object> map);

	// 아이디 찾기
	Account idSearch(Account search);

	// 새 비밀번호 업데이트
	int updatePassword(Account account);

	// 비밀번호를 해시화하여 반환하는 메소드
	String getHashedPassword(String id); 
}
