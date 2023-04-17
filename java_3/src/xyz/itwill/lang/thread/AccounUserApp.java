package xyz.itwill.lang.thread;

public class AccounUserApp {
	public static void main(String[] args) {
		Account account = new Account(10000);
		/*
		AccountUser one = new AccountUser(account, "홍길동");
		AccountUser two = new AccountUser(account, "임꺽정");
		AccountUser three = new AccountUser(account, "전우치");

		one.getAccount().deposit(5000, one.getUserName());
		two.getAccount().deposit(5000, two.getUserName());
		three.getAccount().deposit(5000, three.getUserName());
		*/

		new AccountUser(account, "홍길동").start();
		new AccountUser(account, "임꺽정").start();
		new AccountUser(account, "전우치").start();
	}
}
