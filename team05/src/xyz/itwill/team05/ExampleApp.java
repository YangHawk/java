package xyz.itwill.team05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.regex.Pattern;

public class ExampleApp {

	private BufferedReader in;
	StudentDTO student = new StudentDTO();
	private boolean checkInOut = false;

	public ExampleApp() {
		in = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("===================================");
		System.out.println("『교사 또는 학생으로 로그인하세요』");
		System.out.println("===================================");

		login(); // 로그인 메소드 호출

		if (student.getNo() == 1000) { // 로그인 메소드 실행 후 조건문에 따라 선생 메소드 또는 학생 메소드 호출
			runTeacher();
		} else {
			runStudent();
		}
	}

	public static void main(String[] args) {

		new ExampleApp(); // 생성자로 객체 생성
	}

	public StudentDTO login() { // 로그인 과정을 수행하기 위한 메소드

		try {
			String email;
			String phone;

			while (true) {

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
					System.out.println("==============================================================");
					continue;
				}
				break;
			}

			System.out.println("==========================================");

			if (student.getNo() == 1000) { // 학번 1000 = 교사 || 학번 1001~ = 학생
				System.out.println("[처리 결과]교사 " + student.getName() + "님이 입장하였습니다.");
			} else {
				System.out.println("[처리 결과]학생 " + student.getName() + "님이 입장하였습니다.");
			}
			System.out.println("==========================================");

			return student;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void insertALog() { // 학생 입실 처리 메소드
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

	public void updateALog() { // 학생 퇴실 처리 메소드
		System.out.println("퇴실");

		try {
			int rows = AccessDAOImpl.getDAO().updateALog(student);
			if (rows > 0) {
				System.out.println("[처리 결과]학생  " + student.getName() + "님이 퇴실하였습니다.");
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

		System.out.println("====================================================================================");
		System.out.println("학번\t출결\t이름\t입실시간\t\t퇴실시간\t\t상태");
		System.out.println("====================================================================================");
		for (ALogDTO log : alog) {
			System.out.println(log);
		}
		System.out.println("====================================================================================");
	}

	public void checkIn() { // 입실한 상태에서 재입실을 방지하는 메소드
		try {
			LocalDate currentDate = LocalDate.now();
			checkInOut = AccessDAOImpl.getDAO().checkIn(student, currentDate);
			if (checkInOut) {
				System.out.println("[처리 결과]학생 " + student.getName() + "님은 이미 입실 처리되었습니다.");
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkOut() { // 퇴실 상태에서 재퇴실을 방지하는 메소드

		try {
			LocalDate currentDate = LocalDate.now();
			checkInOut = AccessDAOImpl.getDAO().checkOut(student, currentDate);
			if (checkInOut) {
				System.out.println("[처리 결과]학생 " + student.getName() + "님은 이미 퇴실 처리되었습니다.");
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void runTeacher() { // 선생으로 로그인 할 시 메소드 호출
		while (true) {
			System.out.println("==============================================================");
			System.out.println(" 1. 학생 정보 관리 / 2. 학생 출결 관리 / 3. 종료 ");
			System.out.println("==============================================================");

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
				System.out.println("[메세지]프로그램을 종료합니다.");
				break;
			}

			switch (choice) {
			case 1:
				studentInfApp();
				break;

			case 2:
				studentAttApp();
				break;
			}

		}
	}

	public void runStudent() { // 학생으로 로그인할 시 메소드 호출

		LocalDateTime currentTime = LocalDateTime.now();
		LocalDateTime normalEnterStartTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0));
		LocalDateTime normalEnterEndTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 30));
		LocalDateTime normalLeaveStartTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(18, 30));
		LocalDateTime normalLeaveEndTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 0));

		int hour = currentTime.getHour();
		int minute = currentTime.getMinute();

		String timeString = "지금 시각은 " + hour + "시 " + minute + "분입니다.";

		while (true) {
			System.out.println("==================");
			System.out.println("『학생 메인 메뉴』");
			System.out.println("==================");
			System.out.println();
			System.out.println(" 1. 입실 / 2. 퇴실 / 3. 마이 페이지 / 4. 종료 ");
			System.out.println();
			System.out.println(timeString);
			System.out.println();

			int choice;
			try {
				System.out.print("메뉴를 선택하세요.[1 / 2 / 3 / 4] → ");
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
				System.out.println("[메세지]프로그램을 종료합니다.");
				break;
			}

			switch (choice) {
			case 1:
				if (currentTime.isBefore(normalEnterStartTime) || currentTime.isAfter(normalLeaveStartTime)) {
					// 9시 이전 OR 18시 30분 이후 입실 버튼을 누른 경우
					System.out.println("입실 가능 시간이 아닙니다!");
					System.out.println();
					break;
				} else if (currentTime.isAfter(normalEnterStartTime) && currentTime.isBefore(normalEnterEndTime)) {
					// 9시 이후 9시 30분 이전 입실 버튼을 누른 경우
					System.out.println("정상 시간입니다.");
					System.out.println();
					checkIn();
					if (checkInOut) {
						break;
					} else {
						studentStatus();
						insertALog();
						break;
					}
				} else {
					// 9시 30분 ~ 18시 30분 에 입실 버튼을 누른 경우
					System.out.println("지각 시간입니다.");
					System.out.println();
					checkIn();
					if (checkInOut) {
						break;
					} else {
						studentStatus();
						insertALog();
						break;
					}
				}
			case 2:
				if (currentTime.isBefore(normalLeaveStartTime) || currentTime.isAfter(normalLeaveEndTime)) {
					// 19시 이후 OR 9시 30분 이전 퇴실 버튼을 누른 경우
					System.out.println("퇴실 가능 시간이 아닙니다!");
					System.out.println();
					break;
				} else if (currentTime.isAfter(normalLeaveStartTime) && currentTime.isBefore(normalLeaveEndTime)) {
					// 18시 30분 ~ 19시 퇴실 버튼을 누른 경우
					System.out.println("정상 시간입니다.");
					System.out.println();
					checkOut();
					if (checkInOut) {
						break;
					} else {
						updateALog();
						studentStatus();
						break;
					}
				} else {
					// 9시 30분 ~ 18시 30분 에 퇴실 버튼을 누른 경우
					System.out.println("조퇴 시간입니다.");
					System.out.println();
					checkOut();
					if (checkInOut) {
						break;
					} else {
						updateALog();
						studentStatus();
						break;
					}
				}

			case 3:
				studentMyPage();
				break;
			}

			System.out.println();
		}

	}

	public void studentStatus() {
		try {
			AccessDAOImpl.getDAO().updateStatusNormal(student);
			AccessDAOImpl.getDAO().updateStatusLate(student);
			AccessDAOImpl.getDAO().updateStatusEarlyLeave(student);
			AccessDAOImpl.getDAO().updateStatusAbsent(student);
			AccessDAOImpl.getDAO().updateStatusAbsent2(student);
			int rows = AccessDAOImpl.getDAO().insertStatusAbsent(student);

			if (rows > 0) {
				System.out.println("[처리 결과] 학생 " + student.getName() + "님의 결석이 업데이트되었습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void studentMyPage() {

		String[] menu = { "1. 내 정보 보기", "2. 출결 현황", "3. 뒤로 가기" };

		System.out.println("===============");
		System.out.println("『마이 페이지』");
		System.out.println("===============");
		System.out.println();

		while (true) {
			for (String item : menu) {
				System.out.println(item);
				System.out.println();
			}

			int choice;
			try {
				System.out.print("메뉴를 선택하세요.[1 / 2 / 3] → ");
				choice = Integer.parseInt(in.readLine());
				if (choice < 1 || choice > 3)
					throw new Exception();
			} catch (Exception e) {
				System.out.println("[에러]메뉴를 잘못 선택하였습니다.");
				System.out.println();
				continue;
			}
			System.out.println();

			if (choice == 3)
				break;

			switch (choice) {
			case 1:
				System.out.println(student);
				break;
			case 2:
				showALog();
				break;
			case 3:
				return;
			}
			System.out.println();
		}
	}

	public void studentInfApp() { // 선생으로 로그인할 시 학생 정보를 C.R.U.D 하기 위한 메소드
		in = new BufferedReader(new InputStreamReader(System.in));

		String[] menu = { "1.학생 정보 삽입", "2.학생 정보 변경", "3.학생 정보 삭제", "4.학생 정보 검색", "5.전체 학생 목록 출력", "6.뒤로 가기" };

		System.out.println("====================");
		System.out.println("『 학생 정보 관리 』");
		System.out.println("====================");
		System.out.println();

		while (true) {
			for (String item : menu) {
				System.out.println(item);
				System.out.println();
			}

			int choice;
			try {
				System.out.print("메뉴 선택[1 ~ 6] → ");
				choice = Integer.parseInt(in.readLine());
				if (choice < 1 || choice > 6)
					throw new Exception();
			} catch (Exception e) {
				System.out.println("[에러]메뉴를 잘못 선택하였습니다.");
				System.out.println();
				continue;
			}
			System.out.println();

			if (choice == 6)
				break;

			switch (choice) {
			case 1:
				addStudent();
				break;
			case 2:
				modifyStudent();
				break;
			case 3:
				removeStudent();
				break;
			case 4:
				searchStudent();
				break;
			case 5:
				displayAllStudent();
				break;
			case 6:
				return;
			}
			System.out.println();
		}
	}

	public void addStudent() { // STUDENT 테이블에 학생 정보를 삽입하는 메소드
		System.out.println("====================");
		System.out.println("『 학생 정보 삽입 』");
		System.out.println("====================");
		System.out.println();

		try {

			int no;
			while (true) {
				System.out.print("학번 입력 → ");
				String noTemp = in.readLine();

				if (noTemp == null || noTemp.equals("")) {
					System.out.println("[입력오류]학번을 반드시 입력해 주세요.");
					continue;
				}

				String noReg = "^[1-9][0-9]{3}$";
				if (!Pattern.matches(noReg, noTemp)) {
					System.out.println("[입력오류]학번은 4자리 숫자로만 입력해 주세요.");
					System.out.println("『 학생 정보 삽입 』으로 돌아갑니다.");

					return;
				}

				no = Integer.parseInt(noTemp);

				StudentDTO student = TeacherDAOImpl.getDAO().selectStudent(no);

				if (student != null) {
					System.out.println("[입력오류]이미 사용중인 학번을 입력 하였습니다.");
					continue;
				}

				break;
			}

			String name;
			while (true) {
				System.out.print("이름 입력 → ");
				name = in.readLine();

				if (name == null || name.equals("")) {
					System.out.println("[입력오류]이름을 반드시 입력해 주세요.");
					continue;
				}

				String nameReg = "^[가-힣]{2,5}$";
				if (!Pattern.matches(nameReg, name)) {
					System.out.println("[입력오류]이름은 2~5 범위의 한글로만 입력해 주세요.");
					System.out.println("『 학생 정보 삽입 』으로 돌아갑니다.");

					return;
				}

				break;
			}

			String email;
			while (true) {
				System.out.print("이메일 입력 → ");
				email = in.readLine();

				String emailReg = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";

				if (email != null && !email.equals("") && !Pattern.matches(emailReg, email)) {
					System.out.println("[입력오류]이메일을 형식에 맞게 입력해 주세요.");
					System.out.println("『 학생 정보 삽입 』으로 돌아갑니다.");

					return;
				}

				break;
			}

			String phone;
			while (true) {
				System.out.print("전화번호 입력 → ");
				phone = in.readLine();

				if (phone == null || phone.equals("")) {
					System.out.println("[입력오류]전화번호를 반드시 입력해 주세요.");
					continue;
				}

				String phoneReg = "(01[016789])-\\d{3,4}-\\d{4}";
				if (!Pattern.matches(phoneReg, phone)) {
					System.out.println("[입력오류]전화번호를 형식에 맞게 입력해 주세요.");
					System.out.println("『 학생 정보 삽입 』으로 돌아갑니다.");

					return;
				}

				break;
			}

			String address;
			while (true) {
				System.out.print("주소 입력 → ");
				address = in.readLine();

				if (address == null || address.equals("")) {
					System.out.println("[입력오류]주소를 반드시 입력해 주세요.");
					System.out.println("『 학생 정보 삽입 』으로 돌아갑니다.");

					return;
				}

				break;
			}

			StudentDTO student = new StudentDTO();
			student.setNo(no);
			student.setName(name);
			student.setEmail(email);
			student.setPhone(phone);
			student.setAddress(address);

			int rows = TeacherDAOImpl.getDAO().insertStudent(student);

			System.out.println("[처리결과]" + rows + "명의 학생정보를 삽입 하였습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void modifyStudent() { // STUDENT 테이블에 학생 정보를 변경하는 메소드
		System.out.println("====================");
		System.out.println("『 학생 정보 변경 』");
		System.out.println("====================");
		System.out.println();
		try {
			int no;
			while (true) {
				System.out.print("학번 입력 → ");
				String noTemp = in.readLine();

				if (noTemp == null || noTemp.equals("")) {
					System.out.println("[입력오류]학번을 반드시 입력해 주세요.");
					continue;
				}

				String noReg = "^[1-9][0-9]{3}$";
				if (!Pattern.matches(noReg, noTemp)) {
					System.out.println("[입력오류]학번은 4자리 숫자로만 입력해 주세요.");
					System.out.println("『 학생 정보 삽입 』으로 돌아갑니다.");

					return;
				}

				no = Integer.parseInt(noTemp);

				break;
			}

			StudentDTO student = TeacherDAOImpl.getDAO().selectStudent(no);

			if (student == null) {// 검색된 학생정보가 없는 경우
				System.out.println("[처리결과]변경할 학번의 학생정보가 없습니다.");
				return;
			}

			// 검색된 학생정보 출력
			System.out.println("========================================================================");
			System.out.println("학번\t이름\t이메일\t\t\t전화번호\t주소");
			System.out.println("========================================================================");
			System.out.println(student);
			System.out.println("========================================================================");

			// 키보드로 학번를 제외한 값을 입력받아 저장 - 입력값 검증
			System.out.println("[메세지]변경값 입력 → 변경하지 않을 경우 엔터만 입력해 주세요.");

			String name;
			while (true) {
				System.out.print("이름 입력 → ");
				name = in.readLine();

				String nameReg = "^[가-힣]{2,5}$";
				if (name != null && !name.equals("") && !Pattern.matches(nameReg, name)) {
					System.out.println("[입력오류]이름은 2~5 범위의 한글로만 입력해 주세요.");
					System.out.println("『 학생 정보 삽입 』으로 돌아갑니다.");

					return;
				}

				break;
			}

			String email;
			while (true) {
				System.out.print("이메일 입력 → ");
				email = in.readLine();

				String emailReg = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";

				if (email != null && !email.equals("") && !Pattern.matches(emailReg, email)) {
					System.out.println("[입력오류]이메일을 형식에 맞게 입력해 주세요.");
					System.out.println("『 학생 정보 삽입 』으로 돌아갑니다.");

					return;
				}

				break;
			}

			String phone;
			while (true) {
				System.out.print("전화번호 입력 → ");
				phone = in.readLine();

				String phoneReg = "(01[016789])-\\d{3,4}-\\d{4}";
				if (phone != null && !phone.equals("") && !Pattern.matches(phoneReg, phone)) {
					System.out.println("[입력오류]전화번호를 형식에 맞게 입력해 주세요.");
					System.out.println("『 학생 정보 삽입 』으로 돌아갑니다.");

					return;
				}

				break;
			}

			System.out.print("주소 입력 → ");
			String address = in.readLine();

			if (name != null && !name.equals(""))
				student.setName(name);
			if (email != null && !email.equals(""))
				student.setEmail(email);
			if (phone != null && !phone.equals(""))
				student.setPhone(phone);
			if (address != null && !address.equals(""))
				student.setAddress(address);

			TeacherDAOImpl.getDAO().updateStudent(student);

			System.out.println("[처리결과]학번이 " + student.getNo() + "인 학생의 정보를 변경 하였습니다.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeStudent() { // STUDENT 테이블에 학생 정보를 삭제하는 메소드
		System.out.println("====================");
		System.out.println("『 학생 정보 삭제 』");
		System.out.println("====================");
		System.out.println();
		try {
			int no;
			while (true) {
				System.out.print("학번 입력 → ");
				String noTemp = in.readLine();

				if (noTemp == null || noTemp.equals("")) {// 입력값이 없는 경우
					System.out.println("[입력오류]학번을 반드시 입력해 주세요.");
					continue;
				}

				String noReg = "^[1-9][0-9]{3}$";
				if (!Pattern.matches(noReg, noTemp)) {
					System.out.println("[입력오류]학번은 4자리 숫자로만 입력해 주세요.");
					System.out.println("『 학생 정보 삽입 』으로 돌아갑니다.");

					return;
				}

				no = Integer.parseInt(noTemp);

				break;
			}

			int rows = TeacherDAOImpl.getDAO().deleteStudent(no);

			if (rows > 0) {
				System.out.println("[처리결과]학번이 " + no + "인 학생의 정보를 삭제 하였습니다.");
			} else {
				System.out.println("[처리결과]삭제할 학번의 학생정보가 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchStudent() { // 이름을 전달받아 STUDENT 테이블에 해당 학생 정보를 검색하여 반환하는 메소드
		System.out.println("====================");
		System.out.println("『 학생 정보 검색 』");
		System.out.println("====================");
		System.out.println();
		try {
			String name;
			while (true) {
				System.out.print("이름 입력 → ");
				name = in.readLine();

				if (name == null || name.equals("")) {
					System.out.println("[입력오류]이름을 반드시 입력해 주세요.");
					continue;
				}

				String nameReg = "^[가-힣]{2,5}$";
				if (!Pattern.matches(nameReg, name)) {
					System.out.println("[입력오류]이름은 2~5 범위의 한글로만 입력해 주세요.");
					System.out.println("『 학생 정보 삽입 』으로 돌아갑니다.");

					return;
				}

				break;
			}

			List<StudentDTO> studentList = TeacherDAOImpl.getDAO().selectNameStudentList(name);

			if (studentList.isEmpty()) {
				System.out.println("[처리결과]검색된 학생정보가 없습니다.");
				return;
			}

			System.out.println("========================================================================");
			System.out.println("학번\t이름\t이메일\t\t\t전화번호\t주소");
			System.out.println("========================================================================");
			for (StudentDTO student : studentList) {
				System.out.println(student);
			}
			System.out.println("========================================================================");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void displayAllStudent() { // STUDENT 테이블에 모든 학생 정보를 검색하여 반환하는 메소드
		System.out.println("===================");
		System.out.println("『 학생목록 출력 』");
		System.out.println("===================");
		System.out.println();
		List<StudentDTO> studentList = TeacherDAOImpl.getDAO().selectAllStudentList();

		if (studentList.isEmpty()) {// 검색된 학생정보가 없는 경우
			System.out.println("[처리결과]저장된 학생정보가 없습니다.");
			return;
		}

		System.out.println("========================================================================");
		System.out.println("학번\t이름\t이메일\t\t\t전화번호\t주소");
		System.out.println("========================================================================");
		for (StudentDTO student : studentList) {
			System.out.println(student);
		}
		System.out.println("========================================================================");
	}

	public void studentAttApp() { // 선생으로 로그인할 시 ALOG 테이블에서 학생의 출결 기록을 검색하기 위한 메소드
		in = new BufferedReader(new InputStreamReader(System.in));

		String[] menu = { "1.학생 별 출결 조회", "2.출결 현황 날짜별 조회", "3.뒤로 가기", "4.종료" };

		System.out.println("============================");
		System.out.println("『 학생 출결 관리 및 조회 』");
		System.out.println("============================");
		System.out.println();

		while (true) {
			// 메뉴 출력
			for (String item : menu) {
				System.out.println(item);
				System.out.println();
			}

			int choice;
			try {
				System.out.print("메뉴 선택[1~4] → ");
				// 키보드로 문자열을 입력받아 정수값으로 변환하여 변수에 저장
				choice = Integer.parseInt(in.readLine());
				// 메뉴 선택을 잘못한 경우 인위적 예외 발생
				if (choice < 1 || choice > 4)
					throw new Exception();
			} catch (Exception e) {
				System.out.println("[에러]메뉴를 잘못 선택 하였습니다.");
				System.out.println();
				continue;// 반복문(while) 재실행
			}
			System.out.println();

			if (choice == 4) {
				System.out.println("[메세지]프로그램을 종료합니다.");
				System.exit(0);// 시스템 종료
			}

			// 메뉴 선택에 따른 기능 구현 - 메소드 호출
			switch (choice) {
			case 1:
				studentNoAtt();
				break;
			case 2:
				studentdateAtt();
				break;
			case 3:
				return;
			}
		}
	}

	public void studentNoAtt() { // 학생 이름을 전달받아 ALOG 테이블에 저장된 해당 이름의 출결 기록을 검색하여 반환하는 메소드

		System.out.println("===========================");
		System.out.println("『 학생별 출결 현황 조회 』");
		System.out.println("===========================");

		try {
			String name;
			while (true) {
				System.out.print("이름 입력 → ");
				name = in.readLine();

				if (name == null || name.equals("")) {
					System.out.println("[입력오류]이름을 반드시 입력해 주세요.");
					continue;
				}

				String nameReg = "^[가-힣]{2,5}$";
				if (!Pattern.matches(nameReg, name)) {
					System.out.println("[입력오류]이름은 2~5 범위의 한글로만 입력해 주세요.");
					System.out.println("『 학생별 출결 현황 조회 』로 돌아갑니다.");

					return;
				}

				break;
			}

			List<ALogDTO> studentALogList = TeacherDAOImpl.getDAO().selectNameAlogList(name);

			if (studentALogList.isEmpty()) {
				System.out.println("[처리결과]검색된 학생정보가 없습니다.");
				return;
			}

			System.out.println("====================================================================================");
			System.out.println("학번\t출결\t이름\t입실시간\t\t퇴실시간\t\t상태");
			System.out.println("====================================================================================");
			for (ALogDTO student : studentALogList) {
				System.out.println(student);
			}
			System.out.println("=================================================================================");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void studentdateAtt() { // 특정 날짜를 전달받아 ALOG 테이블에 저장된 해당 날짜의 출결 기록을 검색하여 반환하는 메소드

		System.out.println("===========================");
		System.out.println("『 날짜별 출결 현황 조회 』");
		System.out.println("===========================");

		try {
			String logInTime;
			while (true) {
				System.out.print("날짜 입력 → ");
				logInTime = in.readLine();

				if (logInTime == null || logInTime.equals("")) {
					System.out.println("[입력오류]날짜를 반드시 입력해 주세요.");
					continue;
				}

				String logInTimeReg = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";
				if (!Pattern.matches(logInTimeReg, logInTime)) {
					System.out.println("[입력오류]날짜는 YYYY-MM-DD 형식으로만 입력해 주세요.");

					continue;
				}

				break;
			}

			List<ALogDTO> dateALogList = TeacherDAOImpl.getDAO().selectDateALogList(logInTime);

			if (dateALogList.isEmpty()) {
				System.out.println("[처리결과]검색된 날짜의 출결 정보가 없습니다.");
				return;
			}

			System.out.println("====================================================================================");
			System.out.println("학번\t출결\t이름\t입실시간\t\t퇴실시간\t\t상태");
			System.out.println("====================================================================================");
			for (ALogDTO date : dateALogList) {
				System.out.println(date);
			}
			System.out.println("====================================================================================");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}