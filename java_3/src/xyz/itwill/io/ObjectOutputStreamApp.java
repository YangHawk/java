package xyz.itwill.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Date;

public class ObjectOutputStreamApp {
	public static void main(String[] args) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("c:/data/object.txt"));

		out.writeObject("홍길동");
		out.writeObject(new Date());
		out.writeObject(Arrays.asList("임꺽", "전우", "일지", "장길"));

		out.close();
	
		System.out.println("c:\\data\\object.txt 파일을 확인해 보세요.");
	}
}
