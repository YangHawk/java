package xyz.itwill.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Account;
import xyz.itwill.dto.AccountAuth;
import xyz.itwill.mapper.AccountMapper;
import xyz.itwill.security.CustomAccountDetails;

@Repository
@RequiredArgsConstructor
public class AccountDAOImpl implements AccountDAO {
	private final SqlSession sqlSession;

	@Override
	public int insertAccount(Account account) {
		return sqlSession.getMapper(AccountMapper.class).insertAccount(account);
	}
	
	@Override
	public int insertAccountAuth(AccountAuth auth) {
		return sqlSession.getMapper(AccountMapper.class).insertAccountAuth(auth);
	}

	@Override
	public int updateAccount(Account account) {
		return sqlSession.getMapper(AccountMapper.class).updateAccount(account);
	}

	@Override
	public int deleteAccount(String id) {
		return sqlSession.getMapper(AccountMapper.class).deleteAccount(id);
	}

	@Override
	public Account selectAccount(String id) {
		return sqlSession.getMapper(AccountMapper.class).selectAccount(id);
	}

	@Override
	public int selectAccountCount() {
		return sqlSession.getMapper(AccountMapper.class).selectAccountCount();
	}

	@Override
	public List<Account> selectAccountList(Map<String, Object> map) {
		return sqlSession.getMapper(AccountMapper.class).selectAccountList(map);
	}

	// 아이디 찾기
	@Override
	public Account idSearch(Account search) {
		return sqlSession.getMapper(AccountMapper.class).idSearch(search);
	}

	@Override
	public int updateAccountByAdmin(Account account) {
		return sqlSession.getMapper(AccountMapper.class).updateAccountByAdmin(account);
	}

	// 새 비밀번호 업데이트
	@Override
	public int updatePassword(Account account) {
		return sqlSession.getMapper(AccountMapper.class).updatePassword(account);
	}

	@Override
	public String getHashedPassword(String id) {
		return sqlSession.getMapper(AccountMapper.class).getHashedPassword(id);
	}
}
