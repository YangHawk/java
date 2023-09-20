package xyz.itwill.service;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dao.AccountDAO;
import xyz.itwill.dto.Account;
import xyz.itwill.dto.AccountAuth;
import xyz.itwill.exception.ExistsUserinfoException;
import xyz.itwill.exception.LoginAuthFailException;
import xyz.itwill.exception.UserinfoNotFoundException;
import xyz.itwill.security.CustomAccountDetails;
import xyz.itwill.util.Pager;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
	private final AccountDAO accountDAO;

	// 회원가입 기능
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void addAccount(Account account, String accountRole) throws ExistsUserinfoException {
		if (accountDAO.selectAccount(account.getId()) != null) {
			throw new ExistsUserinfoException("이미 사용 중인 아이디를 입력하였습니다.", account);
		}
		String hashedPassword = BCrypt.hashpw(account.getPassword(), BCrypt.gensalt());
		account.setPassword(hashedPassword);

		accountDAO.insertAccount(account);
		/*
		if (account.getStatus() == 1) {
			accountDAO.insertAccountAuth(new AccountAuth(account.getIdx(), account.getId(), "ROLE_USER"));
			accountDAO.insertAccountAuth(new AccountAuth(account.getIdx(), account.getId(), "ROLE_REGISTER"));
		} else if (account.getStatus() == 2) {
			accountDAO.insertAccountAuth(new AccountAuth(account.getIdx(), account.getId(), "ROLE_USER"));
		}*/
		
		//Account 테이블에서 status를 삭제할 경우
	    if (accountRole.equals("ROLE_REGISTER")) {
	        accountDAO.insertAccountAuth(new AccountAuth(account.getIdx(), account.getId(), "ROLE_REGISTER"));
	        accountDAO.insertAccountAuth(new AccountAuth(account.getIdx(), account.getId(), "ROLE_USER"));
	    } else if (accountRole.equals("ROLE_USER")) {
	        accountDAO.insertAccountAuth(new AccountAuth(account.getIdx(), account.getId(), "ROLE_USER"));
	    }
		
	}
	
	@Override
	public void addAccountAuth(AccountAuth auth) {
		accountDAO.insertAccountAuth(auth);
		
	}

	// 회원 정보 수정 기능
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void modifyAccount(Account account) throws UserinfoNotFoundException {
		if (accountDAO.selectAccount(account.getId()) == null) {
			throw new UserinfoNotFoundException("아이디의 회원 정보가 존재하지 않습니다.");
		}
		if (account.getPassword() != null && account.getPassword().equals("")) {
			String hashedPassword = BCrypt.hashpw(account.getPassword(), BCrypt.gensalt());
			account.setPassword(hashedPassword);
		}
		accountDAO.updateAccount(account);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void modifyAccountByAdmin(Account account) throws UserinfoNotFoundException {
		
		if (accountDAO.selectAccount(account.getId()) == null) {
			throw new UserinfoNotFoundException("아이디의 회원 정보가 존재하지 않습니다.");
		}
		if (account.getPassword() != null && account.getPassword().equals("")) {
			String hashedPassword = BCrypt.hashpw(account.getPassword(), BCrypt.gensalt());
			account.setPassword(hashedPassword);
		}
		accountDAO.updateAccountByAdmin(account);
	}

	// 회원 탈퇴 기능
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void removeAccount(String id) throws UserinfoNotFoundException {
		if (accountDAO.selectAccount(id) == null) {
			throw new UserinfoNotFoundException("아이디의 회원 정보가 존재하지 않습니다.");
		}
		accountDAO.deleteAccount(id);
	}

	// 회원 정보 조회 기능
	@Override
	public Account getAccount(String id) throws UserinfoNotFoundException {
		Account account = accountDAO.selectAccount(id);
		if (account == null) {
			throw new UserinfoNotFoundException("아이디의 회원 정보가 존재하지 않습니다.");
		}
		//return account;
		return accountDAO.selectAccount(id);
	}

	// 회원 리스트 조회 기능
	@Override
	public Map<String, Object> getAccountList(int pageNum, int pageSize, String selectKeyword) {
		int totalBoard = accountDAO.selectAccountCount();
		int blockSize = 10;
		Pager pager = new Pager(pageNum, totalBoard, pageSize, blockSize);

		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("startRow", pager.getStartRow());
		pageMap.put("endRow", pager.getEndRow());
		pageMap.put("selectKeyword", selectKeyword);

		List<Account> accountList = accountDAO.selectAccountList(pageMap);

		Map<String, Object> accountMap = new HashMap<String, Object>();
		accountMap.put("accountList", accountList);
		accountMap.put("pager", pager);

		return accountMap;
	}

	// 로그인 인증 기능
	@Override
	public Account loginAuth(Account account) throws LoginAuthFailException {
		Account authAccount = accountDAO.selectAccount(account.getId());
		if (authAccount == null) {
			throw new LoginAuthFailException("아이디의 회원 정보가 존재하지 않습니다.", account.getId());
		}

		if (!BCrypt.checkpw(account.getPassword(), authAccount.getPassword())) {
			throw new LoginAuthFailException("아이디의 회원 정보가 존재하지 않습니다.", account.getPassword());
		}
		return authAccount;
	}

	// 아이디 찾기
	@Override
	public Account idSearch(Account search) {
		try {
			return accountDAO.idSearch(search);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * //데이터베이스에 정상적으로 접근하고 있는지 확인하는 코드
	 * 
	 * @Override public Account idSearch(Account search) {
	 * System.out.println("idSearch method called with name: " + search.getName() +
	 * " and email: " + search.getEmail()); Account result =
	 * accountDAO.idSearch(search); System.out.println("idSearch result: " +
	 * result); return result; }
	 */

	// 비밀번호 찾기
	@Override
	public String findPassword(Account account) throws Exception {
		Account check = accountDAO.selectAccount(account.getId());

		if (check == null) {
			return "등록되지 않은 아이디 입니다.";
		}

		if (!account.getEmail().equals(check.getEmail())) {
			return "등록되지 않은 이메일입니다.";
		}

		String temporaryPassword = generateTemporaryPassword();

		// 이메일 보내는 로직 추가
		sendTemporaryPasswordByEmail(check.getEmail(), temporaryPassword);

		// 임시 비밀번호 저장
		saveHashedTemporaryPasswordToServer(check, temporaryPassword);

		return "이메일로 임시 비밀번호를 발송하였습니다. 로그인 후 비밀번호를 변경해주세요.";
	}

	// 임시 비밀번호 발송
	private void sendTemporaryPasswordByEmail(String recipientEmail, String temporaryPassword) {
		// SMTP 서버 설정
		String host = "smtp.gmail.com";
		String port = "465"; // Gmail SMTP 포트 번호

		// 발신자 계정 설정
		String senderEmail = "keunsin0@gmail.com";
		String senderPassword = "eeluiuvagflnrpwe";

		// 이메일 속성 설정
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		// 세션 생성
		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, senderPassword);
			}
		});

		try {
			// 메세지 생성 및 설정
			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(senderEmail));
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
			mimeMessage.setSubject("클라우드펀펀 임시 비밀번호 입니다.");

			// HTML 형식으로 본문 작성
			String htmlContent = "<html><body>";
			htmlContent += "<h1 style='color: blue;'>임시 비밀번호 안내</h1><br><br>";
			htmlContent += "<p>임시 비밀번호는 <span style='font-size: 20px; font-weight: bold;'>" + temporaryPassword
					+ "</span> 입니다.</p>";
			htmlContent += "<p>비밀번호를 변경하여 사용하세요.</p>";
			htmlContent += "</body></html>";

			// HTML 콘텐츠를 설정
			mimeMessage.setContent(htmlContent, "text/html; charset=utf-8");

			// 이메일 보내기
			Transport.send(mimeMessage);
			System.out.println("이메일이 성공적으로 전송되었습니다.");
		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("이메일 전송 중 오류가 발생했습니다.");
		}
	}

	private String generateTemporaryPassword() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + // 영문 대문자
				"abcdefghijklmnopqrstuvwxyz" + // 영문 소문자
				"0123456789" + // 숫자
				"@$!%*#?&"; // 특수 문자

		Random random = new Random();
		int minLength = 8; // 8자리
		int maxLength = 12;
		int passwordLength = random.nextInt(maxLength - minLength + 1) + minLength;
		StringBuilder temporaryPassword = new StringBuilder();

		for (int i = 0; i < passwordLength; i++) {
			int index = random.nextInt(characters.length());
			char randomChar = characters.charAt(index);
			temporaryPassword.append(randomChar);
		}
		return temporaryPassword.toString();

	}

	private void saveHashedTemporaryPasswordToServer(Account account, String temporaryPassword) {
		// 임시 비밀번호 해시화
		String hashedTemporaryPassword = BCrypt.hashpw(temporaryPassword, BCrypt.gensalt());

		// 해시화된 임시 비밀번호를 서버에 저장
		account.setPassword(hashedTemporaryPassword);
		accountDAO.updateAccount(account); // 데이터베이스에 업데이트

	}

	// 비밀번호 변경 메소드
	@Override
	public void modifyPassword(Account account) {
		accountDAO.updatePassword(account);
	}

	// 아이디 중복체크
	@Override
	public boolean isIdExists(Account account) {
		// 입력된 아이디를 가진 계정이 데이터베이스에서 조회되는지 확인
		Account checkId = accountDAO.selectAccount(account.getId());

		// 계정이 조회되었으면 아이디가 이미 존재한다고 판단
		if (checkId != null) {
			return true;
		} else {
			return false;
		}
	}
}