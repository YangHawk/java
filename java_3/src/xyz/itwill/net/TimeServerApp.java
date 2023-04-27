package xyz.itwill.net;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TimeServerApp {
	public static void main(String[] args) {
		ServerSocket ntpServer = null;

		try {
			ntpServer = new ServerSocket(2000);

			//System.out.println("ntpServer = " + ntpServer);

			System.out.println("[메세지]NTP Server Running...");

			while (true) {
				Socket socket = ntpServer.accept();

				System.out.println("socket = " + socket);

				/*
				 * OutputStream stream = socket.getOutputStream();
				 * 
				 * ObjectOutputStream out = new ObjectOutputStream(stream);
				 * 
				 * out.writeObject(new Date());
				 */

				new ObjectOutputStream(socket.getOutputStream()).writeObject(new Date());

				System.out.println("[정보]클라이언트[" + socket.getInetAddress().getHostAddress() + "]에게 날짜와 시간을 제공하였습니다.");

				socket.close();

			}
		} catch (IOException e) {
			System.out.println("[에러]서버 네트워크의 문제가 발생하였습니다.");
		}
	}
}
