package xyz.itwill.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClientApp {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("전달 메세지 입력 >> ");
		String message = in.readLine();

		try {

			Socket socket = new Socket("192.168.13.14", 3000);

			/*
			 * BufferedWriter out = new BufferedWriter(new
			 * OutputStreamWriter(socket.getOutputStream()));
			 * 
			 * out.write(message);
			 * 
			 * out.flush();
			 */

			PrintWriter out = new PrintWriter(socket.getOutputStream());

			out.println(message);

			out.flush();

			socket.close();

		} catch (IOException e) {
			System.out.println("[에러]서버에 접속할 수 없습니다.");
		}
	}
}
