package xyz.itwill.io;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class DataInputStreamApp {
	public static void main(String[] args) throws IOException {
		DataInputStream in = new DataInputStream(new FileInputStream("c:/data/data.txt"));

		int value1 = in.readInt();
		boolean value2 = in.readBoolean();
		String value3 = in.readUTF();

		System.out.println("value1 = " + value1);
		System.out.println("value2 = " + value2);
		System.out.println("value3 = " + value3);

		in.close();
	}
}
