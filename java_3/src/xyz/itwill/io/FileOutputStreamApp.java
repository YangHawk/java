package xyz.itwill.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamApp {
	public static void main(String[] args) throws IOException {
		System.out.println("[메세지]키보드를 눌러 값을 이용해 주세요. [프로그램 종료: Ctrl+Z]");

		//FileOutputStream out = new FileOutputStream("C:/data/byte.txt");
		FileOutputStream out = new FileOutputStream("C:/data/byte.txt", true);

		int readByte;

		while (true) {
			readByte = System.in.read();

			if (readByte == -1)
				break;

			out.write(readByte);

		}
		out.close();

		System.out.println("c:\\data\\byte.txt 파일을 확인해 보세요.");
	}
}
