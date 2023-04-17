package xyz.itwill.lang.thread;

public class AccountUser extends Thread {
	private Account account;
	private String userName;

	public AccountUser() {
		// TODO Auto-generated constructor stub
	}

	public AccountUser(Account account, String userName) {
		super();
		this.account = account;
		this.userName = userName;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public void run() {

		this.account.deposit(5000, userName);
		// this.account.withDraw(5000, userName); 이렇게 하거나, 아래처럼 하거나.

		synchronized (account) {
			account.withDraw(5000, userName);
		}
	}
}
