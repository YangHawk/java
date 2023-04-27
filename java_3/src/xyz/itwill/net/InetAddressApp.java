package xyz.itwill.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressApp {
	public static void main(String[] args) throws UnknownHostException {
		InetAddress myComputer = InetAddress.getLocalHost();

		System.out.println("myComputer = " + myComputer);

		System.out.println("myComputer = " + myComputer.getHostName());

		System.out.println("myComputer = " + myComputer.getHostAddress());
		System.out.println("============================================================");
		InetAddress itwill = InetAddress.getByName("www.itwill.xyz");

		System.out.println("[www.itwill.xyz]의 IP 주소 = " + itwill.getHostAddress());
		System.out.println("============================================================");
		InetAddress[] naver = InetAddress.getAllByName("www.naver.com");

		for (InetAddress address : naver) {
			System.out.println("[www.naver.com]의 IP 주소 = " + address.getHostAddress());
		}
	}
}
