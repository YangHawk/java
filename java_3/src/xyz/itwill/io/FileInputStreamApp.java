package xyz.itwill.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputStreamApp {
	public static void main(String[] args) throws IOException {
		FileInputStream in = null;

		try {
			in = new FileInputStream("c:/data/byte.txt");
		} catch (FileNotFoundException e) {
			System.out.println("[에러]c:\\data\\byte.txt 파일을 찾을 수 없습니다.");
		}

		System.out.println("[메세지]c:\\data\\byte.txt 파일에 저장된 내용입니다.");

		int readByte;

		while (true) {
			readByte = in.read();

			if (readByte == -1)
				break;

			System.out.write(readByte);
		}

		in.close();
	}
}
