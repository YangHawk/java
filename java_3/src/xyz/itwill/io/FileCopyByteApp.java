package xyz.itwill.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyByteApp {
	public static void main(String[] args) throws IOException {
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		
		try {
			in = new BufferedInputStream(new FileInputStream("c:/data/bandizip.exe"));

			out = new BufferedOutputStream(new FileOutputStream("c:/data/bandizip_byte.exe"));

			int readByte;

			while (true) {
				readByte = in.read();
				if (readByte == -1)
					break;
				out.write(readByte);
			}

			System.out.println("[메세지]파일을 성공적으로 복사!");

		} catch (FileNotFoundException e) {
			System.out.println("[에러]원본 파일을 찾을 수 없습니다.");
		} finally {
			in.close();
			out.close();
		}
	}
}
