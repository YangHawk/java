package xyz.itwill.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileCopyCharApp {
	public static void main(String[] args) throws IOException {
		BufferedReader in = null;
		BufferedWriter out = null;

		try {
			in = new BufferedReader(new FileReader("c:/data/bandizip.exe"));

			out = new BufferedWriter(new FileWriter("c:/data/bandizip_char.exe"));

			int readByte;

			while (true) {
				readByte = in.read();
				if (readByte == -1)
					break;
				out.write(readByte);
			}

			System.out.println("[메세지]파일을 성공적으로 복사 하였습니다.");
		} catch (FileNotFoundException e) {
			System.out.println("[에러]원본 파일을 찾을 수 없습니다.");
		} finally {
			in.close();
			out.close();
		}

	}
}
