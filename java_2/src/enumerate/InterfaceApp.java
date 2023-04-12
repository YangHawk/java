package enumerate;

public class InterfaceApp {
	public static void main(String[] args) {
		System.out.println("삽입 = " + InterfaceOne.INSERT);
		System.out.println("변경 = " + InterfaceOne.UPDATE);
		System.out.println("삭제 = " + InterfaceOne.DELETE);
		System.out.println("검색 = " + InterfaceOne.SELECT);
		System.out.println("====================================================");
		System.out.println("삽입 = " + InterfaceTwo.ADD);
		System.out.println("변경 = " + InterfaceTwo.MODIFY);
		System.out.println("삭제 = " + InterfaceTwo.REMOVE);
		System.out.println("검색 = " + InterfaceTwo.SEARCH);
		System.out.println("====================================================");
		int choice = InterfaceOne.INSERT;
		System.out.println("choice = " + choice);
		System.out.println("====================================================");
		switch (choice) {
		case InterfaceOne.INSERT:
			System.out.println("# 학생정보를 삽입합니다. #");
			break;
		case InterfaceOne.UPDATE:
			System.out.println("# 학생정보를 변경합니다. #");
			break;
		case InterfaceOne.DELETE:
			System.out.println("# 학생정보를 삭제합니다. #");
			break;
		case InterfaceOne.SELECT:
			System.out.println("# 학생정보를 검색합니다. #");
			break;
		}
	}
}
