package xyz.itwill.util;

import java.text.DecimalFormat;

public class DecimalFormatApp {
	public static void main(String[] args) {
		int money = 100_000_000;
		System.out.println("금액 = " + money + "원");
		DecimalFormat decimalFormat = new DecimalFormat("###,###,##0");

		System.out.println("금액 = " + decimalFormat.format(money) + "원!");
		System.out.println("금액 = " + DecimalFormat.getInstance().format(money) + "원!");
		System.out.println("금액 = " + DecimalFormat.getCurrencyInstance().format(money));

	}
}
