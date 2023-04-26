package xyz.itwill.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MemberSave {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("c:/data/member.txt"));

		out.writeObject(new Member("abc123", "홍길동", "010-3174-1234"));
		out.writeObject(new Member("def456", "홍길동", "010-2145-8741"));
		out.writeObject(new Member("ghi789", "전우치", "010-9813-6547"));

		out.close();

		System.out.println("c:\\data\\member.txt 파일에 회원정보를 저장 하였습니다.");
	}
}
