package xyz.itwill.io;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MemberLoadApp {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("c:/data/member.txt"));

		System.out.println("『회원목록』");
		while (true) {
			try {
				Member member = (Member) in.readObject();
				if (member == null)
					System.out.println(member);
			} catch (EOFException e) {
				break;
			}
		}

		in.close();
	}
}
