package xyz.itwill.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileWriterApp {
	public static void main(String[] args) throws IOException {
		System.out.println("[메세지]키보드를 눌러 값을 입력해 주세요.[프로그램 종료: Ctrl+Z]");

		InputStreamReader in = new InputStreamReader(System.in);

		// FileWriter out = new FileWriter("c:/data/char.txt");
		FileWriter out = new FileWriter("c:/data/char.txt", true);

		int readByte;

		while (true) {
			readByte = in.read();

			if (readByte == -1)
				break;

			out.write(readByte);
		}

		out.close();

		System.out.println("c:\\data\\char.txt 파일을 확인해 보세요.");
	}
}
