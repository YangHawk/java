package xyz.itwill.student;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Pattern;

// 학생 정보를 관리하는 프로그램 작성
// ▶ 메뉴 선택에 따른 학생 정보 삽입, 변경, 삭제, 검색 기능 제공
// ▶ 입력과 출력은 프로그램에서 구현하고 데이터 처리는 DAO 클래스의 메소드를 호출하여 처리
public class StudentCUIApp {
	// 키보드 입력 스트림을 저장하기 위한 필드
	private BufferedReader in;

	public StudentCUIApp() { // 기본 생성자
		// 키보드로부터 문자열을 입력받기 위한 입력 스트림을 생성하여 필드에 저장
		in = new BufferedReader(new InputStreamReader(System.in));

		String[] menu = { "1. 학생 정보 삽입", "2. 학생 정보 변경", "3. 학생 정보 삭제", "4. 학생 정보 검색", "5. 학생 목록 출력", "6. 프로그램 종료" };

		System.out.println("『학생 관리 프로그램』");

		while (true) {
			// 메뉴 출력
			for (String item : menu) {
				System.out.println(item);
			}

			int choice;
			try {
				System.out.print("메뉴 선택[1~6] → ");
				// 키보드로 문자열을 입력받아 정수값으로 변환하여 변수에 저장
				choice = Integer.parseInt(in.readLine());
				// 메뉴 선택을 잘못한 경우 인위적 예외 발생
				if (choice < 1 || choice > 6)
					throw new Exception();
			} catch (Exception e) {
				System.out.println("[에러]메뉴를 잘못 선택하였습니다.");
				System.out.println();
				continue; // while 구문 재 실행
			}
			System.out.println();

			if (choice == 6)
				break; // 반복분(while) 종료

			// 메뉴 선택에 따른 기능 구현 - 메소드 호출
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
			}
			System.out.println();
		}
		System.out.println("[메세지]학생 관리 프로그램을 종료합니다.");
	}

	public static void main(String[] args) {
		new StudentCUIApp();
	}

	// [1. 학생 정보 삽입] 메뉴를 선택한 경우 호출되는 메소드
	// ▶ 키보드로 학생 정보를 입력받아 STUDENT 테이블에 삽입하고 처리 결과를 출력하는 메소드
	public void addStudent() {
		System.out.println("※학생 정보 삽입※");

		try {
			// 키보드로 학생 정보를 입력받아 저장 - 입력값 검증
			// ▶ 입력값 검증이 실패한 경우 재입력하도록 처리
			// 1. 학번을 입력해야 한다.
			// 2. 학번은 4자리 숫자로만 입력해야 한다.
			// 3. 학번은 PK 제약조건을 위배할 수 없다.(중복될 수 없다)
			// [1-1. 학번 입력]
			int no; // 학번을 입력받아 저장하기 위한 변수
			while (true) { // 학번 입력값을 검증하기 위한 반복문
				System.out.print("학번 입력 → ");
				String noTemp = in.readLine(); // 키보드로 입력받은 값을 noTemp에 문자열로 저장

				if (noTemp == null || noTemp.equals("")) { // 입력값이 없는 경우
					System.out.println("[입력 오류]학번을 반드시 입력하여 주십시오.");
					continue; // 반복문(while) 재실행 - 재입력
				}

				// 학번에 대한 입력 패턴이 저장된 정규 표현식
				String noReg = "^[1-9][0-9]{3}$";
				// 정규 표현식: 1~9라는 문자들로 시작되고, 0~9라는 문자가 3번 반복되고 끝나는($)
				if (!Pattern.matches(noReg, noTemp)) { // 정규 표현식과 입력값의 입력 패턴이 다른 경우
					System.out.println("[입력 오류]학번은 4자리 숫자로만 입력하여 주십시오.");
					continue; // 반복문(while) 재실행 - 재입력
				}

				no = Integer.parseInt(noTemp); // 문자열을 정수값으로 변환하여 변수에 저장

				// 입력된 학번이 STUDENT 테이블에 저장된 기존 학생 정보의 학번과 비교하여
				// 중복된 경우 비정상적인 값이므로 재입력 처리
				// 학번을 전달받아 STUDENT 테이블에 저장된 해당 학번의 학생 정보를 검색하여 반환하는 메소드
				// ▶ StudentDAOImpl 클래스의 selectStudent() 메소드 호출
				StudentDTO student = StudentDAOImpl.getDAO().selectStudent(no);
				// 싱글톤 클래스이므로 getDAO() 메소드를 호출하여 이미 생성된 객체를 호출
				// selectStudent() 메소드를 호출할 때는 null 반환: 학생 정보 미검색
				// OR StudentDTO 객체 반환: 학생 정보 검색 - StudentDTO 클래스의 객체를 생성
				if (student != null) { // 키보드로 입력된 학번의 학생 정보가 검색된 경우 - 학번 중복
					System.out.println("[입력 오류]이미 사용 중인 학번을 입력하였습니다.");
					continue;
				}
				break;
			}

			// [1-2. 이름 입력]
			String name; // 이름을 저장하기 위한 변수
			while (true) {
				System.out.print("이름 입력 → ");
				name = in.readLine();

				if (name == null || name.equals("")) {
					System.out.println("[입력 오류]이름을 반드시 입력하여 주십시오.");
					continue;

				}

				String nameReg = "^[가-힣]{2,5}$";
				if (!Pattern.matches(nameReg, name)) {
					System.out.println("[입력 오류]이름은 2~5 범위의 한글로만 입력하여 주십시오.");
					continue;
				}
				break;
			}

			// [1-3. 전화번호 입력]
			String phone;
			while (true) {
				System.out.print("전화번호 입력 → ");
				phone = in.readLine();

				if (phone == null || phone.equals("")) {
					System.out.println("[입력 오류]전화번호를 반드시 입력하여 주십시오.");
					continue;

				}

				String phoneReg = "(01[016789])-\\d{3,4}-\\d{4}";
				if (!Pattern.matches(phoneReg, phone)) {
					System.out.println("[입력 오류]전화번호를 형식에 맞게 입력하여 주십시오.");
					continue;
				}
				break;
			}

			// [1-4. 주소 입력]
			String address;
			while (true) {
				System.out.print("주소 입력 → ");
				address = in.readLine();

				if (address == null || address.equals("")) {
					System.out.println("[입력 오류]주소를 반드시 입력하여 주십시오.");
					continue;

				}
				break;
			}

			// [1-5. 생년월일 입력]
			String birthday;
			while (true) {
				System.out.print("생년월일 입력 → ");
				birthday = in.readLine();

				if (birthday == null || birthday.equals("")) {
					System.out.println("[입력 오류]생년월일을 반드시 입력하여 주십시오.");
					continue;
				}

				String birthdayReg = "(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])";
				if (!Pattern.matches(birthdayReg, birthday)) {
					System.out.println("[입력 오류]생년월일을 형식에 맞게 입력하여 주십시오.");
					continue;
				}
				break;
			}

			// 키보드로 입력받은 학생 정보를 STUDENT 테이블에 삽입 - DAO 클래스의 메소드를 호출

			// 학생 정보를 저장하기 위한StudentDTO 객체 생성
			StudentDTO student = new StudentDTO();
			// 키보드로 입력받은 값으로 StudentDTO 객체의 필드값을 변경
			student.setNo(no);
			student.setName(name);
			student.setPhone(phone);
			student.setAddress(address);
			student.setBirthday(birthday);

			// 학생 정보를 전달받아 STUDENT 테이블에 삽입하는 DAO 클래스의 메소드를 호출
			int rows = StudentDAOImpl.getDAO().insertStudent(student);
			// 객체를 반환받아 직접 호출할 수 있도록!
			// DTO 객체를 매개변수로 insertStudent() 메소드에 전달해야 삽입 처리가 된다.
			System.out.println("[처리 결과]" + rows + "명의 학생 정보를 삽입하였습니다.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// [2. 학생 정보 변경] 메뉴를 선택한 경우 호출되는 메소드
	// ▶ 키보드로 학번을 입력받아 STUDENT 테이블에 저장된 해당 학번의 학생정보를 검색하여 출력
	// ▶ 키보드로 학번을 제외한 나머지 값을 입력받아 STUDENT 테이블에 저장된 학생 정보를 변경하고 그
	// 처리결과를 출력하는 메소드
	public void modifyStudent() {
		System.out.println("※학생 정보 변경※");

		try {
			// 키보드로 학생 정보를 입력받아 저장 - 입력값 검증

			int no;
			while (true) {
				System.out.print("학번 입력 → ");
				String noTemp = in.readLine();

				if (noTemp == null || noTemp.equals("")) { // 입력값이 없는 경우
					System.out.println("[입력 오류]학번을 반드시 입력하여 주십시오.");
					continue;
				}

				String noReg = "^[1-9][0-9]{3}$";

				if (!Pattern.matches(noReg, noTemp)) {
					System.out.println("[입력 오류]학번은 4자리 숫자로만 입력하여 주십시오.");
					continue;
				}

				no = Integer.parseInt(noTemp); // 문자열을 정수값으로 변환하여 변수에 저장

				break;
			}

			// 학번을 전달받아 STUDENT 테이블에 저장된 해당 학번의 학생 정보를 검색하여 반환하는
			// DAO 클래스의 메소드 호출
			StudentDTO student = StudentDAOImpl.getDAO().selectStudent(no);

			if (student == null) { // 검색된 학생 정보가 없는 경우
				System.out.println("[처리 결과]변경할 학번의 학생 정보가 없습니다.");
				return;
			}

			System.out.println("==========================================================================");
			System.out.println("학번\t이름\t전화번호\t주소\t\t생년월일");
			System.out.println("==========================================================================");
			System.out.println(student);
			System.out.println("==========================================================================");

			// 키보드로 학번을 제외한 값을 입력받아 - 입력값 검증
			System.out.println("[메세지]변경값 입력 → 변경하지 않을 경우 ENTER를 입력하여 주세요");

			// [1-2. 이름 입력]
			String name; // 이름을 저장하기 위한 변수
			while (true) {
				System.out.print("이름 입력 → ");
				name = in.readLine();

				String nameReg = "^[가-힣]{2,5}$";
				if (name != null && !name.equals("") && !Pattern.matches(nameReg, name)) {
					System.out.println("[입력 오류]이름은 2~5 범위의 한글로만 입력하여 주십시오.");
					continue;
				}
				break;
			}

			// [1-3. 전화번호 입력]
			String phone;
			while (true) {
				System.out.print("전화번호 입력 → ");
				phone = in.readLine();

				String phoneReg = "(01[016789])-\\d{3,4}-\\d{4}";
				if (phone != null && !phone.equals("") && !Pattern.matches(phoneReg, phone)) {
					System.out.println("[입력 오류]전화번호를 형식에 맞게 입력하여 주십시오.");
					continue;
				}
				break;
			}

			// [1-4. 주소 입력]
			String address;

			System.out.print("주소 입력 → ");
			address = in.readLine();

			// [1-5. 생년월일 입력]
			String birthday;
			while (true) {
				System.out.print("생년월일 입력 → ");
				birthday = in.readLine();

				String birthdayReg = "(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])";
				if (birthday != null && !birthday.equals("") && !Pattern.matches(birthdayReg, birthday)) {
					System.out.println("[입력 오류]생년월일을 형식에 맞게 입력하여 주십시오.");
					continue;
				}
				break;
			}

			// 입력받은 변경값을 이용하여 학번으로 검색되어 반환받은 StudentDTO 객체의 필드값 변경
			// ▶ 변경값이 있는 경우에만 필드값 변경
			if (name != null && name.equals(""))
				student.setName(name);
			if (phone != null && phone.equals(""))
				student.setPhone(phone);
			if (address != null && address.equals(""))
				student.setAddress(address);
			if (birthday != null && birthday.equals(""))
				student.setBirthday(birthday);

			// 학생정보를 전달받아 STUDENT 테이블에 저장된 학생정보를 변경하는 DAO 클래스의 메소드 호출
			int rows = StudentDAOImpl.getDAO().updateStudent(student);

			System.out.println("[처리 결과]" + rows + "명의 학생 정보를 변경하였습니다.");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// [3. 학생 정보 삭제] 메뉴를 선택한 경우 호출되는 메소드
	// ▶ 키보드로 학번을 입력받아 STUDENT 테이블에 저장된 해당 학번의 학생 정보를 삭제하고
	// 처리결과를 출력하는 메소드

	public void removeStudent() {
		System.out.println("※학생 정보 삭제※");

		try {

			int no;
			while (true) {
				System.out.print("학번 입력 → ");
				String noTemp = in.readLine();

				if (noTemp == null || noTemp.equals("")) {
					System.out.println("[입력 오류]학번을 반드시 입력하여 주십시오.");
					continue;
				}

				String noReg = "^[1-9][0-9]{3}$";

				if (!Pattern.matches(noReg, noTemp)) {
					System.out.println("[입력 오류]학번은 4자리 숫자로만 입력하여 주십시오.");
					continue;
				}

				no = Integer.parseInt(noTemp);

				break;
			}

			// 학번을 전달받아 STUDENT 테이블에 저장된 해당 학번의 학생 정보를 삭제하는
			// DAO 클래스의 메소드 호출
			int rows = StudentDAOImpl.getDAO().deleteStudent(no);

			if (rows > 0) { // 삭제행이 있는 경우
				System.out.println("[처리 결과]" + rows + "명의 학생 정보를 삭제하였습니다.");
			} else { // 삭제행이 없는 경우 - 입력받은 학번의 학생 정보가 없는 경우
				System.out.println("[처리 결과]삭제할 학번의 학생 정보가 없습니다.");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// [4. 학생 정보 검색] 메뉴를 선택한 경우 호출되는 메소드
	// ▶ 키보드로 이름을 입력받아 STUDENT 테이블에 저장된 해당 이름의 학생 정보를 검색하여
	// 출력하는 메소드
	public void searchStudent() {
		System.out.println("※학생 정보 검색※");

		try {

			String name;
			while (true) {
				System.out.print("이름 입력 → ");
				name = in.readLine();

				if (name == null || name.equals("")) {
					System.out.println("[입력 오류]이름을 반드시 입력하여 주십시오.");
					continue;
				}

				String nameReg = "^[가-힣]{2,5}$";
				if (!Pattern.matches(nameReg, name)) {
					System.out.println("[입력 오류]이름은 2~5 범위의 한글로만 입력하여 주십시오.");
					continue;
				}
				break;
			}

			// 이름을 전달받아 STUDENT 테이블에 저장된 해당 이름의 학생 정보를 검색하여 반환하는
			// DAO 클래스의 메소드 호출
			List<StudentDTO> studentList = StudentDAOImpl.getDAO().selectNameStudentList(name);
			if (studentList.isEmpty()) {
				System.out.println("[처리 결과]검색된 학생 정보가 없습니다");
				return;
			}

			System.out.println("==========================================================================");
			System.out.println("학번\t이름\t전화번호\t주소\t\t생년월일");
			System.out.println("==========================================================================");
			for (StudentDTO student : studentList) {
				System.out.println(student);
			}
			System.out.println("==========================================================================");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// [5. 학생 목록 출력] 메뉴를 선택한 경우 호출되는 메소드
	// ▶ STUDENT 테이블에 저장된 모든 학생 정보를 검색하여 출력하는 메소드
	public void displayAllStudent() {
		System.out.println("※학생 목록 출력※");

		// STUDENT 테이블에 저장된 모든 학생 정보를 검색하고 반환하는 DAO 클래스의 메소드 호출
		List<StudentDTO> allStudentList = StudentDAOImpl.getDAO().selectAllStudentList();
		// List 객체로 반환하는 것이므로 studentList라는 참조변수에 저장

		if (allStudentList.isEmpty()) { // 검색된 학생 정보가 없는 경우 List 객체에 요소가 하나도 없다면!
			System.out.println("[처리 결과]저장된 학생 정보가 없습니다.");
			return;
		}

		System.out.println("==========================================================================");
		System.out.println("학번\t이름\t전화번호\t주소\t\t생년월일");
		System.out.println("==========================================================================");

		// List 객체의 요소를 제공받아 반복 처리
		for (StudentDTO student : allStudentList) {
			// StudentDTO 클래스의 toString 메소드(오버라이드된 toString() 메소드) 자동 호출
			// StudentDTO 객체의 필드값을 반환받아 출력
			System.out.println(student);
		}
		System.out.println("==========================================================================");

	}

}
