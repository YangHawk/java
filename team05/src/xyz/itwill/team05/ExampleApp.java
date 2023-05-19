package xyz.itwill.team05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;

public class ExampleApp {

	private BufferedReader in;
	StudentDTO student = new StudentDTO();
	private boolean checkInOut = false;

	public ExampleApp() {
		in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("로그인 예시 프로그램");
		login();

		while (true) {
			System.out.println("1. 입실 / 2. 퇴실 / 3. 출결 기록 확인 / 4. 종료");

			int choice;
			try {
				System.out.print("메뉴 선택[1 / 2 / 3 / 4] → ");
				choice = Integer.parseInt(in.readLine());
				if (choice < 1 || choice > 4)
					throw new Exception();
			} catch (Exception e) {
				System.out.println("[에러]메뉴를 잘못 선택하였습니다.");
				System.out.println();
				continue;
			}
			System.out.println();

			if (choice == 4)
				break;

			switch (choice) {
			case 1:
				checkIn();
				if (checkInOut) {
					break;
				} else {
					insertALog();
					break;
				}
			case 2:
				checkOut();
				if (checkInOut) {
					break;
				} else {
					updateALog();
					break;
				}
			case 3:
				showALog();
				break;
			}

			System.out.println();
		}
	}

	public static void main(String[] args) {
		new ExampleApp();
	}

	public void login() {

		try {
			String email;
			String phone;

			while (true) {
				System.out.println("아이디 입력(이메일)");
				email = in.readLine();

				if (email == null || email.equals("")) {
					System.out.println("이메일을 입력하세요!");
					continue;
				}

				break;
			}

			while (true) {
				System.out.println("비밀번호 입력(전화번호 뒤)");
				phone = in.readLine();

				if (phone == null || phone.equals("")) {
					System.out.println("비밀번호를 입력하세요!");
					continue;
				}

				break;
			}

			student = AccessDAOImpl.getDAO().login(email, phone);
			if (student == null) {
				System.out.println("로그인 실패!");
				return;
			}

			System.out.println("============");

			if (student.getName().equals("김교사")) {

				System.out.println("교사 " + student.getName() + "가 입장하였습니다.");
			} else {

				System.out.println("학생 " + student.getName() + "가 입장하였습니다.");
			}
			System.out.println("============");
			System.out.println(student);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertALog() {

		System.out.println("입실 버튼 눌렀다!");

		try {

			int rows = AccessDAOImpl.getDAO().insertALog(student);
			if (rows > 0) {
				System.out.println("[처리 결과]" + rows + "명의 학생이 입실하였습니다.");
			} else {
				System.out.println("[에러]입실 처리 중 오류가 발생하였습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateALog() {

		System.out.println("퇴실 버튼!");

		try {
			int rows = AccessDAOImpl.getDAO().updateALog(student);
			if (rows > 0) {
				System.out.println("[처리 결과]" + rows + "명의 학생이 퇴실하였습니다.");
			} else {
				System.out.println("[에러]퇴실 처리 중 오류가 발생하였습니다.");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showALog() {
		System.out.println("내 출결 기록 보기");
		List<ALogDTO> alog = AccessDAOImpl.getDAO().showALog(student);

		if (alog.isEmpty()) {
			System.out.println("[처리 결과]출결 정보가 없습니다.");
			return;
		}

		System.out.println("==========================================================================");
		for (ALogDTO log : alog) {
			System.out.println(log);
		}
	}

	public void checkIn() {
		try {
			LocalDate currentDate = LocalDate.now();
			checkInOut = AccessDAOImpl.getDAO().checkIn(student, currentDate);
			if (checkInOut) {
				System.out.println("입실 이미 했음!");
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkOut() {

		try {
			LocalDate currentDate = LocalDate.now();
			checkInOut = AccessDAOImpl.getDAO().checkOut(student, currentDate);
			if (checkInOut) {
				System.out.println("퇴실 이미 했음!");
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
