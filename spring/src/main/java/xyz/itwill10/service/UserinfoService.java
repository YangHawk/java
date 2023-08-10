package xyz.itwill10.service;

import java.util.List;

import xyz.itwill10.dto.Userinfo;
import xyz.itwill10.exception.ExistsUserinfoException;
import xyz.itwill10.exception.LoginAuthFailException;
import xyz.itwill10.exception.UserinfoNotFoundException;

public interface UserinfoService {
	void addUserinfo(Userinfo userinfo) throws ExistsUserinfoException;

	void modifyUserinfo(Userinfo userinfo) throws UserinfoNotFoundException;

	void removeUserinfo(String userid) throws UserinfoNotFoundException;

	Userinfo getUserinfo(String userid) throws UserinfoNotFoundException;

	List<Userinfo> getUserinfoList();

	Userinfo loginAuth(Userinfo userinfo) throws LoginAuthFailException; // 인증 처리 - 인증 실패 시 예외 발생
}
