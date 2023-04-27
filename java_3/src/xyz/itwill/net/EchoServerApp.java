package xyz.itwill.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerApp {
	public static void main(String[] args) {
		ServerSocket echoServer = null;

		try {
			echoServer = new ServerSocket(3000);

			System.out.println("Echo Server is running...");

			while (true) {
				Socket socket = echoServer.accept();

				System.out.println("socket = " + socket);

				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				System.out.println("[" + socket.getInetAddress().getHostAddress() + "]님이 보내온 메세지 = " + in.readLine());

				socket.close();
			}
		} catch (IOException e) {
			System.out.println("서버에 문제가 있어요!");
		}
	}
}
