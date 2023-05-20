package xyz.itwill.team05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.time.LocalTime;

public class ExampleApp2 {

    private BufferedReader in;
    private StudentDTO student;

    public ExampleApp2() {
        in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("로그인 예시 프로그램");

        while (true) {
            student = login();

            if (student != null) {
                System.out.println("============");

                if (student.getName().equals("김교사")) {
                    System.out.println("교사 " + student.getName() + "가 입장하였습니다.");
                } else {
                    System.out.println("학생 " + student.getName() + "가 입장하였습니다.");
                }

                System.out.println("============");
                System.out.println(student);

                runMenu();
                break;
            } else {
                System.out.println("로그인 실패!");
                System.out.println();
                while (true) {
                    System.out.print("다시 로그인하시겠습니까? (Y/N) ");
                    String answer;
                    try {
                        answer = in.readLine();
                        if (answer.equalsIgnoreCase("Y")) {
                            break;  // 다시 로그인 시도
                        } else if (answer.equalsIgnoreCase("N")) {
                            System.out.println("프로그램을 종료합니다.");
                            return;  // 프로그램 종료
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("Y 또는 N을 입력해주세요.");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        new ExampleApp();
    }

    public StudentDTO login() {
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

            StudentDTO student = AccessDAOImpl.getDAO().login(email, phone);
            return student;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void runMenu() {
        while (true) {
            if (student.getName().equals("김교사")) {
                System.out.println("1. 학생 정보 관리");
                System.out.println("2. 학생 출결 관리");
                System.out.println("3. 종료");
            } else {
                System.out.println("1. 입실");
                System.out.println("2. 퇴실");
                System.out.println("3. 마이 페이지");
                System.out.println("4. 종료");
            }

            int choice;
            try {
                System.out.print("메뉴 선택 → ");
                choice = Integer.parseInt(in.readLine());
                if (student.getName().equals("김교사") && (choice < 1 || choice > 3))
                    throw new Exception();
                else if (!student.getName().equals("김교사") && (choice < 1 || choice > 4))
                    throw new Exception();
            } catch (Exception e) {
                System.out.println("[에러]메뉴를 잘못 선택하였습니다.");
                System.out.println();
                continue;
            }
            System.out.println();

            switch (choice) {
                case 1:
                    if (student.getName().equals("김교사")) {
                        // 학생 정보 관리 기능 실행
                        // TODO: 학생 정보 관리 메소드 호출
                        System.out.println("학생 정보 관리 기능 실행");
                    } else {
                        insertALog();
                    }
                    break;
                case 2:
                    if (student.getName().equals("김교사")) {
                        // 학생 출결 관리 기능 실행
                        // TODO: 학생 출결 관리 메소드 호출
                        System.out.println("학생 출결 관리 기능 실행");
                    } else {
                        updateALog();
                    }
                    break;
                case 3:
                    if (student.getName().equals("김교사")) {
                        System.out.println("종료");
                        return;
                    } else {
                        showMyPage();
                    }
                    break;
                case 4:
                    System.out.println("프로그램을 종료합니다.");
                    return;
            }

            System.out.println();
        }
    }
    public void insertALog() {
        System.out.println("입실 버튼!");

        try {
            int rows = AccessDAOImpl.getDAO().insertALog(student);
            System.out.println("[처리 결과]" + rows + "명의 학생이 입실하였습니다.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateALog() {
        System.out.println("퇴실 버튼!");

        try {
            int status;
            while (true) {
                System.out.println("퇴실 상태 선택");
                System.out.println("1. 정상 / 2. 지각 / 3. 조퇴");
                System.out.print("퇴실 상태 입력 → ");

                String input = in.readLine();
                if (input.isEmpty()) {
                    System.out.println("유효한 퇴실 상태를 입력하세요!");
                    continue;
                }

                status = Integer.parseInt(input);
                if (status < 1 || status > 3) {
                    System.out.println("유효한 퇴실 상태를 입력하세요!");
                } else {
                    break;
                }
            }

            LocalTime currentTime = LocalTime.now();
            LocalTime normalStartTime = LocalTime.of(9, 0);      // 오전 9시
            LocalTime normalEndTime = LocalTime.of(9, 30);       // 오전 9시 30분
            LocalTime leaveStartTime = LocalTime.of(18, 20);     // 오후 6시 20분
            LocalTime leaveEndTime = LocalTime.of(19, 0);        // 오후 7시

            if (status == 1) {  // 정상 퇴실
                if (student.getEntryTime() != null && currentTime.isAfter(leaveStartTime) && currentTime.isBefore(leaveEndTime)) {
                    int rows = AccessDAOImpl.getDAO().updateALog(student, status);
                    System.out.println("[처리 결과]" + rows + "명의 학생이 퇴실하였습니다.");
                } else {
                    System.out.println("정상 퇴실은 " + leaveStartTime + "부터 " + leaveEndTime + "까지 가능합니다.");
                }
            } else if (status == 2) {  // 지각
                if (student.getEntryTime() != null && student.getEntryTime().isAfter(normalEndTime) && currentTime.isAfter(leaveStartTime) && currentTime.isBefore(leaveEndTime)) {
                    int rows = AccessDAOImpl.getDAO().updateALog(student, status);
                    System.out.println("[처리 결과]" + rows + "명의 학생이 퇴실하였습니다.");
                } else {
                    System.out.println("지각은 " + leaveStartTime + "부터 " + leaveEndTime + "까지 퇴실 체크 가능합니다.");
                }
            } else if (status == 3) {  // 조퇴
                if (student.getEntryTime() != null && student.getEntryTime().isAfter(normalStartTime) && student.getEntryTime().isBefore(normalEndTime) && currentTime.isBefore(leaveStartTime)) {
                    int rows = AccessDAOImpl.getDAO().updateALog(student, status);
                    System.out.println("[처리 결과]" + rows + "명의 학생이 퇴실하였습니다.");
                } else {
                    System.out.println("조퇴는 " + normalStartTime + "부터 " + normalEndTime + "까지 입실 체크한 사람중, 퇴실은 " + leaveStartTime + " 이전에 가능합니다.");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("유효한 퇴실 상태를 입력하세요!");
        }
    }
    
    public void showMyPage() {
        while (true) {
            System.out.println("마이 페이지");
            System.out.println("1. 내 정보 보기 / 2. 내 출결 확인 / 3. 뒤로 가기");

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

            switch (choice) {
                case 1:
                    showMyInfo();
                    break;
                case 2:
                    showMyAttendance();
                    break;
                case 3:
                    return;
            }

            System.out.println();
        }
    }

    public void showMyInfo() {
        System.out.println("내 정보 보기");
        System.out.println(student);
    }

    public void showMyAttendance() {
        while (true) {
            System.out.println("내 출결 확인");
            System.out.println("1. 전체 출결 조회 / 2. 월별 출결 조회 / 3. 뒤로 가기");

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

            switch (choice) {
                case 1:
                    showAllAttendance();
                    break;
                case 2:
                    showMonthlyAttendance();
                    break;
                case 3:
                    return;
            }

            System.out.println();
        }
    }

    public void showAllAttendance() {
        System.out.println("전체 출결 조회");
        List<StudentDTO> attendanceList = AccessDAOImpl.getDAO().showALog(student);
        System.out.println(attendanceList);
    }

    public void showMonthlyAttendance() {
        try {
            System.out.print("조회할 월을 입력하세요 (예: 2023-05): ");
            String month = in.readLine();
            List<StudentDTO> attendanceList = AccessDAOImpl.getDAO().showMonthlyALog(student, month);
            System.out.println("월별 출결 조회 (" + month + ")");
            System.out.println(attendanceList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}