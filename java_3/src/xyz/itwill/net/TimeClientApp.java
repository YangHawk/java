package xyz.itwill.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeClientApp {
	public static void main(String[] args) throws ClassNotFoundException {
		try {
			Socket socket = new Socket("192.168.13.14", 2000);

			//System.out.println("socket = " + socket);

			InputStream stream = socket.getInputStream();

			ObjectInputStream in = new ObjectInputStream(stream);

			Date date = (Date) in.readObject();

			System.out
					.println("[결과]서버에서 보낸 날짜 및 시간 = " + new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초").format(date));

			socket.close();
		} catch (UnknownHostException e) {
			System.out.println("[에러]서버를 찾을 수 없습니다.");
		} catch (IOException e) {
			System.out.println("[에러]서버에 접속할 수 없습니다.");
		}
	}
}
