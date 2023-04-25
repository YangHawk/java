package xyz.itwill.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class FileReaderApp {
	public static void main(String[] args) throws IOException {
		System.out.println("[메세지]c:\\data\\char.txt 파일에 저장된 내용입니다.");

		FileReader in = null;
		try {
			in = new FileReader("c:/data/char.txt");
		} catch (FileNotFoundException e) {
			System.out.println("[에러]c:\\data\\char.txt 파일을 찾을 수 없습니다.");
			System.exit(0);
		}

		// OutputStreamWriter out = new OutputStreamWriter(System.out);
		PrintWriter out = new PrintWriter(System.out);

		int readByte;

		while (true) {
			readByte = in.read();

			if (readByte == -1)
				break;

			out.write(readByte);
			out.flush();
		}

		in.close();
	}
}
