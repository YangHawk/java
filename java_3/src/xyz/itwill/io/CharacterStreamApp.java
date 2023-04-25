package xyz.itwill.io;

import java.io.IOException;
import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class CharacterStreamApp {
	public static void main(String[] args) throws IOException {
		System.out.println("[메세지]키보드를 눌러 값을 입력해 주세요.[프로그램 종료: Ctrl+Z]");

		InputStreamReader in = new InputStreamReader(System.in);
		//OutputStreamWriter out = new OutputStreamWriter(System.out);
		PrintWriter out = new PrintWriter(System.out);

		int readByte;

		while (true) {
			readByte = in.read();

			if (readByte == -1)
				break;

			//System.out.write(readByte);
			out.write(readByte);
		
			out.flush();
		}

		System.out.println("[메세지]프로그램을 종료합니다.");

	}
}
