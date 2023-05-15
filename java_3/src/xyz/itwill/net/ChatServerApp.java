package xyz.itwill.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServerApp {
	private List<SocketThread> clientList;

	public ChatServerApp() {
		ServerSocket chatServer = null;

		try {
			chatServer = new ServerSocket(5000);
			System.out.println("[메세지]채팅 서버 동작 중...");

			clientList = new ArrayList<>();

			while (true) {
				try {
					Socket socket = chatServer.accept();

					System.out.println("[접속로그]" + socket.getInetAddress().getHostAddress()+"의 컴퓨터");

					SocketThread socketThread = new SocketThread(socket);

					socketThread.start();

					clientList.add(socketThread);
					
				} catch (IOException e) {
					System.out.println("[에러로그]클라이언트의 접속 관련 문제 발생");
				}

			}
		} catch (IOException e) {
			System.out.println("[에러로그]서버가 정상적으로 동작되지 않습니다.");
		}
	}

	public static void main(String[] args) {
		new ChatServerApp();
	}

	public void sendMessage(String message) {
		for (SocketThread socketThread : clientList) {
			socketThread.out.println();
		}
	}

	public class SocketThread extends Thread {
		
		private Socket client;

		private BufferedReader in;

		private PrintWriter out;

		public SocketThread(Socket client) {
			this.client = client;

		}

		@Override
		public void run() {

			String aliasName = "";
			
			try {
				in = new BufferedReader(new InputStreamReader(client.getInputStream()));

				out = new PrintWriter(client.getOutputStream(), true);

				aliasName = in.readLine();

				sendMessage("[" + aliasName + "]님이 입장하였습니다.");

				while (true) {
					sendMessage("[" + aliasName + "]" + in.readLine());
				}

			} catch (IOException e) {
				clientList.remove(this);
				
				sendMessage("["+aliasName+"]님이 퇴장 하였습니다.");

				System.out.println("[해제로그]"+client.getInetAddress().getHostAddress()
						+"의 컴퓨터에서 서버 접속을 종료 하였습니다.");
			}

		}
	}
}
