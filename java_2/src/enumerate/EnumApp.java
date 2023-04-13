package enumerate;

public class EnumApp {
	public static void main(String[] args) {
		System.out.println("삽입 = " + EnumOne.INSERT);
		System.out.println("변경 = " + EnumOne.UPDATE);
		System.out.println("삭제 = " + EnumOne.DELETE);
		System.out.println("검색 = " + EnumOne.SELECT);
		System.out.println("====================================================");
		System.out.println("삽입 = " + EnumTwo.ADD);
		System.out.println("변경 = " + EnumTwo.MODIFY);
		System.out.println("삭제 = " + EnumTwo.REMOVE);
		System.out.println("검색 = " + EnumTwo.SEARCH);
		System.out.println("====================================================");
		// int choice = EnumOne.INSERT;
		//String choice2 = EnumOne.INSERT;
		EnumOne choice = EnumOne.INSERT;
		System.out.println("choice = " + choice);
		System.out.println("====================================================");
		switch (choice) {
		case INSERT:
			System.out.println("# 학생정보를 삽입합니다. #");
			break;
		case UPDATE:
			System.out.println("# 학생정보를 변경합니다. #");
			break;
		case DELETE:
			System.out.println("# 학생정보를 삭제합니다. #");
			break;
		case SELECT:
			System.out.println("# 학생정보를 검색합니다. #");
			break;
		}

	}
}
