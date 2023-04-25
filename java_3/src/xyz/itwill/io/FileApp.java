package xyz.itwill.io;

import java.io.File;
import java.io.IOException;

public class FileApp {
	public static void main(String[] args) throws IOException {
		File fileOne = new File("c:\\data");

		if (fileOne.exists()) {
			System.out.println("c:\\data 폴더가 이미 존재합니다.");
		} else {
			fileOne.mkdir();
			System.out.println("c:\\data 폴더를 생성하였습니다..");
		}
		System.out.println("==============================================================");
		File fileTwo = new File("c:\\data\\itwill.txt");

		if (fileTwo.exists()) {
			System.out.println("c:\\data\\itwill.txt 파일이 이미 존재합니다.");
		} else {
			fileTwo.createNewFile();
			System.out.println("c:\\data\\itwill.txt 파일을 생성하였습니다.");
		}
		System.out.println("==============================================================");
		File fileThree = new File("c:/data", "itwill.txt");

		if (fileThree.exists()) {
			fileThree.delete();
			System.out.println("c:\\data\\itwill.txt 파일을 삭제 하였습니다.");
		} else {
			System.out.println("c:\\data\\itwill.txt 파일이 존재하지 않습니다.");
		}
		System.out.println("==============================================================");
		File fileFour = new File("src");

		if (fileFour.exists()) {
			// System.out.println("파일경로 = "+fileFour.toString());
			System.out.println("파일경로 = " + fileFour);

			System.out.println("파일경로 = " + fileFour.getAbsolutePath());
		} else {
			System.out.println("작업 디렉토리에 [src] 디렉토리가 없습니다.");
		}
		System.out.println("==============================================================");
		File fileFive = new File("c:/");

		if (fileFive.isDirectory()) {
			File[] subFiles = fileFive.listFiles();

			System.out.println(fileFive + " 작업 폴더에 자식 목록 >>");
			for (File file : subFiles) {
				if (file.isFile()) {
					System.out.println("파일 = " + file);
				} else {
					System.out.println("폴더 = " + file);
				}
			}
		}

	}
}
