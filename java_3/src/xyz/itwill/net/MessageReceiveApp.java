package xyz.itwill.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class MessageReceiveApp {
	public static void main(String[] args) throws IOException {
		DatagramSocket socket = new DatagramSocket(4000);

		byte[] data = new byte[1024];

		DatagramPacket packet = new DatagramPacket(data, data.length);

		System.out.println("메세지 수신 중...");

		socket.receive(packet);

		String message = new String(data);

		System.out.println("[결과]메세지 >> " + message);

		socket.close();

	}
}
