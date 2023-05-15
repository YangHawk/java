package xyz.itwill.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//JDBC 프로그램은 기본적으로 AutoCommit 기능이 활성화되어있어 SQL 명령(DML)이 전달되면
//실행되어 자동으로 커밋 처리
// → 프로그램 실행 시 예외가 발생된 경우 예외 발생 전 전달되어 실행된 SQL 명령에 대한 롤백 처리
// JDBC 프로그램에서 AutoCommit 기능을 비활성화 처리하고 예외 발생 없이 프로그램이 정상적으로
//실행되면 커밋 처리하고 예외가 발생된 경우 롤백 처리하는 것을 권장
//STUDENT 테이블에 저장된 학생 정보 중 학번이 [2000]인 학생의 이름을 [임꺽인좆]으로 변경하는 JDBC 프로그램 작성
public class TransactionControlApp {
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "scott";
			String password = "tiger";
			con = DriverManager.getConnection(url, user, password);

			// Connection.setAutoCommit(boolean autoCommit): AutoCommit 기능의 사용 유무를 변경
			// → false: AutoCommit 기능 비활성화 / true: AutoCommit 기능 활성화 - 기본값
			con.setAutoCommit(false);

			stmt = con.createStatement();

			String sql = "update student set name = '임꺾인좆' where no=2000";
			int rows = stmt.executeUpdate(sql);

			if (rows > 0) {// 조작행이 있는 경우
				System.out.println("[메세지]" + rows + "명의 학생 정보를 변경 하였습니다.");
			} else {// 조작행이 없는 경우
				System.out.println("[메세지]변경 처리할 학변의 학생 정보를 찾을 수 없습니다.");
			}

			// Conncection.commit(): 커밋 처리하는 메소드
			con.commit();

		} catch (ClassNotFoundException e) {
			System.out.println("[에러]OracleDriver 클래스를 찾을 수 없습니다.");
		} catch (SQLException e) {
			System.out.println("[에러]JDBC 관련 오류 = " + e.getMessage());
		} catch (Exception e) {
			System.out.println("[에러]프로그램에 예기치 못한 오류가 발생되었습니다.");
			try {
				// Connection.rollback(): 롤백 처리하는 메소드 - 전달된 SQL 명령의 실행을 취소
				con.rollback();
			} catch (SQLException e1) {
			}
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();

			} catch (SQLException e) {
			}
		}
	}
}
