package xyz.itwill.net;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerSocketApp {
	public static void main(String[] args) {
		for (int i = 2000; i <= 9000; i += 1000) {
			try {
				ServerSocket serverSocket = new ServerSocket(i);

				System.out.println("[메세지]" + i + "번 포트는 네트워크 프로그램에서 사용 가능합니다.");

				serverSocket.close();
			} catch (IOException e) {
				System.out.println("[메세지]" + i + "번 포트는 네트워크 프로그램에서 사용 불가능합니다.");
			}
		}
	}
}
