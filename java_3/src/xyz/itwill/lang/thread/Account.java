package xyz.itwill.lang.thread;

public class Account {
	private int balance;

	public Account() {
		// TODO Auto-generated constructor stub
	}

	public Account(int balance) {
		super();
		this.balance = balance;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public synchronized void deposit(int amount, String name) {
		balance += amount;
		System.out.println("[입금]" + name + "님이 " + amount + "원 입금하여 잔액은 " + balance + "원 입니다.");
	}

	public void withDraw(int amount, String name) {
		if (balance < amount) {
			System.out.println("[에러]" + name + "님, 잔액이" + balance + "원 남아" + amount + "원을 출금할 수 없습니다.");
			return;
		}

		balance -= amount;
		System.out.println("[출금]" + name + "님이 " + amount + "원 출금하여 잔액은 " + balance + "원 입니다.");
	}

}
