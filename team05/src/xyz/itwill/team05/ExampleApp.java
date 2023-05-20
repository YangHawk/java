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

		System.out.println("로그인을 해주세요!");
		login(); // 로그인 메소드 호출

		if (student.getName().equals("김교사")) { // 로그인 메소드 실행 후 조건문에 따라 선생 메소드 또는 학생 메소드 호출
			runTeacher();
		} else {
			runStudent();
		}
	}

	public static void main(String[] args) {
		new ExampleApp();
	}

	public void login() { // 로그인 과정을 수행하기 위한 메소드

		try {
			String email;
			String phone;

			while (true) {
				System.out.println("아이디를 입력하세요.[이메일]");
				email = in.readLine();

				if (email == null || email.equals("")) {
					System.out.println("아이디를 입력하세요!");
					continue;
				}

				break;
			}

			while (true) {
				System.out.println("비밀번호를 입력하세요.[전화번호 4자리]");
				phone = in.readLine();

				if (phone == null || phone.equals("")) {
					System.out.println("비밀번호를 입력하세요!");
					continue;
				}

				break;
			}

			student = AccessDAOImpl.getDAO().login(email, phone); // DAO 클래스의 로그인 메소드 호출(이메일(ID) / 전화번호(비밀번호)) 전달

			if (student == null) {
				System.out.println("로그인 실패!");
				return;
			}

			System.out.println("==============================================================");

			if (student.getNo() == 0) {
				System.out.println("[처리 결과]교사 " + student.getName() + "님이 입장하였습니다.");
			} else {
				System.out.println("[처리 결과]학생 " + student.getName() + "님이 입장하였습니다.");
			}
			System.out.println("==============================================================");
			System.out.println(student);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertALog() { // 학생의 입실 처리를 위한 메소드

		System.out.println("입실");

		try {

			int rows = AccessDAOImpl.getDAO().insertALog(student);
			if (rows > 0) {
				System.out.println("[처리 결과]학생 " + student.getName() + "님이 입실하였습니다.");
			} else {
				System.out.println("[에러]입실 처리 중 오류가 발생하였습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateALog() { // 학생의 퇴실 처리를 위한 메소드

		System.out.println("퇴실 버튼!");

		try {
			int rows = AccessDAOImpl.getDAO().updateALog(student);
			if (rows > 0) {
				System.out.println("[처리 결과]학생" + student.getName() + "님이 퇴실하였습니다.");
			} else {
				System.out.println("[에러]퇴실 처리 중 오류가 발생하였습니다.");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showALog() { // 학생이 본인의 출결 기록을 열람하기 위한 메소드
		System.out.println("내 출결 기록 보기");
		List<ALogDTO> alog = AccessDAOImpl.getDAO().showALog(student);

		if (alog.isEmpty()) {
			System.out.println("[처리 결과]출결 정보가 없습니다.");
			return;
		}

		System.out.println("==============================================================");
		for (ALogDTO log : alog) {
			System.out.println(log);
		}
	}

	public void checkIn() { // 입실 관련 메소드
		try {
			LocalDate currentDate = LocalDate.now();
			checkInOut = AccessDAOImpl.getDAO().checkIn(student, currentDate);
			if (checkInOut) {
				System.out.println("[처리 결과] 학생 " + student.getName() + "님은 이미 입실 처리되었습니다.");
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkOut() { // 퇴실 관련 메소드

		try {
			LocalDate currentDate = LocalDate.now();
			checkInOut = AccessDAOImpl.getDAO().checkOut(student, currentDate);
			if (checkInOut) {
				System.out.println("[처리 결과]" + student.getName() + "님은 이미 퇴실 처리되었습니다.");
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void runTeacher() {
		while (true) {
			System.out.println("1. 학생 정보 관리 / 2. 학생 출결 관리 / 3. 종료");

			int choice;
			try {
				System.out.print("메뉴 선택[1 / 2 / 3] → ");
				choice = Integer.parseInt(in.readLine());
				if (choice < 1 || choice > 3)
					throw new Exception();
			} catch (Exception e) {
				System.out.println("[에러]메뉴를 잘못 선택하였습니다.");
				System.out.println();
				continue;
			}
			System.out.println();

			if (choice == 3) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}

			switch (choice) {
			case 1:
				System.out.println("==============================================================");
				System.out.println("학생 정보 관리 기능 실행");
				System.out.println("==============================================================");
				// 여기에 선생의 DAO 메소드가 호출되어야 함

			case 2:
				System.out.println("==============================================================");
				System.out.println("학생 출결 관리 기능 실행");
				System.out.println("==============================================================");

				// 여기에 선생의 DAO 메소드가 호출되어야 함
			}
		}
	}

	public void runStudent() {
		while (true) {
			System.out.println("1. 입실 / 2. 퇴실 / 3. 마이 페이지 / 4. 종료");

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

			if (choice == 4) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}

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

}
