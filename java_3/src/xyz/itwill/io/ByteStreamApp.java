package xyz.itwill.io;

import java.io.IOException;

public class ByteStreamApp {
	public static void main(String[] args) throws IOException {
		System.out.println("[메세지]키보드를 눌러 값을 입력해 주세요.[프로그램 종료: Ctrl+Z]");

		int readByte;

		while (true) {
			readByte = System.in.read();

			if (readByte == -1)
				break;

			System.out.write(readByte);
		}

		System.out.println("[메세지]프로그램을 종료합니다.");
	}
}
