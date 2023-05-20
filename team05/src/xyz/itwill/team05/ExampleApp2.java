package xyz.itwill.team05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Pattern;

import xyz.itwill.team05.TeacherDAOImpl;
import xyz.itwill.team05.StudentDTO;

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
        new ExampleApp2();
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
                        studentInfApp();
                    } else {
                        insertALog();
                    }
                    break;
                case 2:
                    if (student.getName().equals("김교사")) {
                        // TODO: 학생 출결 관리 메소드 호출
                        studentAttApp();
                    } else {
                        updateALog();
                    }
                    break;
                case 3:
                    if (student.getName().equals("김교사")) {
                        System.out.println("뒤로 가기");
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
    
    public void studentInfApp() {
    	in=new BufferedReader(new InputStreamReader(System.in));
    
    	String[] menu={"1.학생 정보 삽입","2.학생 정보 변경","3.학생 정보 삭제"
    			,"4.학생 정보 검색","5.전체 학생 목록 출력","6.뒤로 가기"};
    		
    		System.out.println("<< 학생 정보 관리 >>");
    		
    		while(true) {
    			for(String item : menu) {
    				System.out.println(item);
    			}
    			
    			int choice;
    			try {
    				System.out.print("메뉴 선택[1~6] >> ");
    				choice=Integer.parseInt(in.readLine());
    				if(choice < 1 || choice > 6) throw new Exception();
    			} catch (Exception e) {
    				System.out.println("[에러]메뉴를 잘못 선택 하였습니다.");
    				System.out.println();
    				continue;
    			}
    			System.out.println();

    			if(choice == 6) break;
    			
    			switch(choice) {
    			case 1: addStudent(); break;
    			case 2: modifyStudent(); break;
    			case 3: removeStudent(); break;
    			case 4: searchStudent(); break;
    			case 5: displayAllStudent(); break;
    			case 6: return;
    			}
    			System.out.println();
    		}
    	}
    
    
    public void addStudent() {
    	System.out.println("===============");
    	System.out.println(" 학생정보 삽입 ");
    	System.out.println("===============");
    	
    	try {
			
			int no;
			while(true) {
				System.out.print("학번 입력 >> ");
				String noTemp=in.readLine();
				
				if(noTemp == null || noTemp.equals("")) {
					System.out.println("[입력오류]학번을 반드시 입력해 주세요.");
					continue;
				}
				
				
				String noReg="^[1-9][0-9]{3}$";
				if(!Pattern.matches(noReg, noTemp)) {
					System.out.println("[입력오류]학번은 4자리 숫자로만 입력해 주세요.");
					continue;	
				}
				
				no=Integer.parseInt(noTemp);
				
				
				StudentDTO student=TeacherDAOImpl.getDAO().selectStudent(no);
				
				if(student != null) {
					System.out.println("[입력오류]이미 사용중인 학번을 입력 하였습니다.");
					continue;	
				}
				
				break;
			}
			
			String name;
			while(true) {
				System.out.print("이름 입력 >> ");
				name=in.readLine();
				
				if(name == null || name.equals("")) {
					System.out.println("[입력오류]이름을 반드시 입력해 주세요.");
					continue;
				}
				
				String nameReg="^[가-힣]{2,5}$";
				if(!Pattern.matches(nameReg, name)) {
					System.out.println("[입력오류]이름은 2~5 범위의 한글로만 입력해 주세요.");
					continue;	
				}
				
				break;
			}
			
			String email;
			while(true) {
				System.out.print("이메일 입력 >> ");
				email=in.readLine();

				if(email == null || email.equals("")) {
					System.out.println("[입력오류]이메일을 반드시 입력해 주세요.");
					continue;	
				}
				
				break;
			}
			
			String phone;
			while(true) {
				System.out.print("전화번호 입력 >> ");
				phone=in.readLine();
				
				if(phone == null || phone.equals("")) {
					System.out.println("[입력오류]전화번호를 반드시 입력해 주세요.");
					continue;
				}
				
				String phoneReg="(01[016789])-\\d{3,4}-\\d{4}";
				if(!Pattern.matches(phoneReg, phone)) {
					System.out.println("[입력오류]전화번호를 형식에 맞게 입력해 주세요.");
					continue;	
				}
				
				break;
			}
			
			String address;
			while(true) {
				System.out.print("주소 입력 >> ");
				address=in.readLine();
				
				if(address == null || address.equals("")) {
					System.out.println("[입력오류]주소를 반드시 입력해 주세요.");
					continue;
				}
				
				break;
			}
			
			StudentDTO student=new StudentDTO();
			student.setNo(no);
			student.setName(name);
			student.setEmail(email);
			student.setPhone(phone);
			student.setAddress(address);
			
			int rows=TeacherDAOImpl.getDAO().insertStudent(student);
			
			System.out.println("[처리결과]"+rows+"명의 학생정보를 삽입 하였습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public void modifyStudent() {
    	System.out.println("===============");
		System.out.println(" 학생정보 변경 ");
    	System.out.println("===============");

    	try {
			int no;
			while(true) {
				System.out.print("학번 입력 >> ");
				String noTemp=in.readLine();
					
				if(noTemp == null || noTemp.equals("")) {
					System.out.println("[입력오류]학번을 반드시 입력해 주세요.");
					continue;
				}
				
				String noReg="^[1-9][0-9]{3}$";
				if(!Pattern.matches(noReg, noTemp)) {
					System.out.println("[입력오류]학번은 4자리 숫자로만 입력해 주세요.");
					continue;	
				}
				
				no=Integer.parseInt(noTemp);
				
				break;
			}
			
			StudentDTO student=TeacherDAOImpl.getDAO().selectStudent(no);
			
			if(student == null) {//검색된 학생정보가 없는 경우
				System.out.println("[처리결과]변경할 학번의 학생정보가 없습니다.");
				return;
			}
			
			//검색된 학생정보 출력
			System.out.println("========================================================================");
			System.out.println("학번\t이름\t이메일\t\t\t전화번호\t주소");
			System.out.println("========================================================================");
			System.out.println(student);
			System.out.println("========================================================================");
			
			//키보드로 학번를 제외한 값을 입력받아 저장 - 입력값 검증
			System.out.println("[메세지]변경값 입력 >> 변경하지 않을 경우 엔터만 입력해 주세요.");

			String name;
			while(true) {
				System.out.print("이름 입력 >> ");
				name=in.readLine();
				
				String nameReg="^[가-힣]{2,5}$";
				if(name != null && !name.equals("") && !Pattern.matches(nameReg, name)) {
					System.out.println("[입력오류]이름은 2~5 범위의 한글로만 입력해 주세요.");
					continue;	
				}
				
				break;
			}
			
			String email;
			while(true) {
				System.out.print("이메일 입력 >> ");
				email=in.readLine();

				if(email != null && !email.equals("")) {
					System.out.println("[입력오류]이메일을 형식에 맞게 입력해 주세요.");
					continue;	
				}
				
				break;
			}
			
			String phone;
			while(true) {
				System.out.print("전화번호 입력 >> ");
				phone=in.readLine();
				
				String phoneReg="(01[016789])-\\d{3,4}-\\d{4}";
				if(phone != null && !phone.equals("") && !Pattern.matches(phoneReg, phone)) {
					System.out.println("[입력오류]전화번호를 형식에 맞게 입력해 주세요.");
					continue;	
				}
				
				break;
			}
			
			System.out.print("주소 입력 >> ");
			String address=in.readLine();
			
			
			
			if(name != null && !name.equals("")) student.setName(name);
			if(email != null && !email.equals("")) student.setEmail(email);
			if(phone != null && !phone.equals("")) student.setPhone(phone);
			if(address != null && !address.equals("")) student.setAddress(address);
			
			int rows=TeacherDAOImpl.getDAO().updateStudent(student);
			
			System.out.println("[처리결과]"+rows+"명의 학생정보를 변경 하였습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public void removeStudent() {
    	System.out.println("===============");
    	System.out.println(" 학생정보 삭제 ");
    	System.out.println("===============");
    	
    	try {
			int no;
			while(true) {
				System.out.print("학번 입력 >> ");
				String noTemp=in.readLine();
				
				if(noTemp == null || noTemp.equals("")) {//입력값이 없는 경우
					System.out.println("[입력오류]학번을 반드시 입력해 주세요.");
					continue;
				}
				
				String noReg="^[1-9][0-9]{3}$";
				if(!Pattern.matches(noReg, noTemp)) {
					System.out.println("[입력오류]학번은 4자리 숫자로만 입력해 주세요.");
					continue;	
				}
				
				no=Integer.parseInt(noTemp);
				
				break;
			}
			
			int rows=TeacherDAOImpl.getDAO().deleteStudent(no);
			
			if(rows > 0) {
				System.out.println("[처리결과]"+rows+"명의 학생정보를 삭제 하였습니다.");
			} else {
				System.out.println("[처리결과]삭제할 학번의 학생정보가 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    	
    public void searchStudent() {
    	System.out.println("===============");
    	System.out.println(" 학생정보 검색 ");
    	System.out.println("===============");

    	try {
			String name;
			while(true) {
				System.out.print("이름 입력 >> ");
				name=in.readLine();
				
				if(name == null || name.equals("")) {
					System.out.println("[입력오류]이름을 반드시 입력해 주세요.");
					continue;
				}
				
				String nameReg="^[가-힣]{2,5}$";
				if(!Pattern.matches(nameReg, name)) {
					System.out.println("[입력오류]이름은 2~5 범위의 한글로만 입력해 주세요.");
					continue;	
				}
				
				break;
			}
			
			List<StudentDTO> studentList=TeacherDAOImpl.getDAO().selectNameStudentList(name);
			
			if(studentList.isEmpty()) {
				System.out.println("[처리결과]검색된 학생정보가 없습니다.");
				return;
			}
			
			System.out.println("========================================================================");
			System.out.println("학번\t이름\t이메일\t\t\t전화번호\t주소");
			System.out.println("========================================================================");
			for(StudentDTO student : studentList) {
				System.out.println(student);
			}
			System.out.println("========================================================================");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public void displayAllStudent() {
    	System.out.println("===============");
		System.out.println(" 학생목록 출력 ");
    	System.out.println("===============");
		
    	List<StudentDTO> studentList=TeacherDAOImpl.getDAO().selectAllStudentList();
		
		if(studentList.isEmpty()) {//검색된 학생정보가 없는 경우
			System.out.println("[처리결과]저장된 학생정보가 없습니다.");
			return;
		}
		
		System.out.println("========================================================================");
		System.out.println("학번\t이름\t이메일\t\t\t전화번호\t주소");
		System.out.println("========================================================================");
		for(StudentDTO student : studentList) {
			System.out.println(student);
		}
		System.out.println("========================================================================");
	}

    
    public void studentAttApp() {
    	in=new BufferedReader(new InputStreamReader(System.in));
        
    	String[] menu={"1.학생 별 출결 조회","2.출결 현황 날짜별 조회","3.뒤로 가기"};
    		
    		System.out.println("<< 학생 출결 관리 및 조회 >>");
    		
    		while(true) {
    			//메뉴 출력
    			for(String item : menu) {
    				System.out.println(item);
    			}
    			
    			int choice;
    			try {
    				System.out.print("메뉴 선택[1~3] >> ");
    				//키보드로 문자열을 입력받아 정수값으로 변환하여 변수에 저장
    				choice=Integer.parseInt(in.readLine());
    				//메뉴 선택을 잘못한 경우 인위적 예외 발생 
    				if(choice < 1 || choice > 3) throw new Exception();
    			} catch (Exception e) {
    				System.out.println("[에러]메뉴를 잘못 선택 하였습니다.");
    				System.out.println();
    				continue;//반복문(while) 재실행
    			}
    			System.out.println();

    			if(choice == 3) break;//반복문(while) 종료
    			
    			//메뉴 선택에 따른 기능 구현 - 메소드 호출
    			switch(choice) {
    			case 1: studentNoAtt(); break;
    			case 2: studentdateAtt(); break;
    			case 3: return;
    			}
    			System.out.println();
    		}
    	}
    
    
	public void studentNoAtt() {
    	System.out.println("===================");
		System.out.println(" 학생 별 출결 조회 ");
    	System.out.println("===================");

	}
	
	public void studentdateAtt() {
    	System.out.println("=======================");
		System.out.println(" 출결 현황 날짜별 조회 ");
    	System.out.println("=======================");

    	
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
                    int rows = AccessDAOImpl.getDAO(). (student, status);
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