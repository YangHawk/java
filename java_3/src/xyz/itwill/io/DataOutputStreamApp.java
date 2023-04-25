package xyz.itwill.io;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataOutputStreamApp {
	public static void main(String[] args) throws IOException {
		DataOutputStream out = new DataOutputStream(new FileOutputStream("c:/data/data.txt"));

		out.writeInt(100);

		out.writeBoolean(true);

		out.writeUTF("홍길동");

		out.close();

		System.out.println("c:\\data\\data.txt 파일을 확인해 보세요!");
	}
}
