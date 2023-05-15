package xyz.itwill.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

// 키보드로 SQL 명령을 입력받아 DBMS 서버에 전달하여 실행하고 실행 결과를 출력하는 JDBC 프로그램 작성
// → 키보드로 입력 가능한 SQL 명령은 INSERT, UPDATE, DELETE, SELECT 명령만 입력받아 실행되도록 작성
// → SQL 명령은 [EXIT] 명령을 입력하기 전까지 반복적으로 입력받아 실행
// → 입력받은 SQL 명령이 잘못된 경우 에러 메세지 출력
public class SqlMinusApp {
	public static void main(String[] args) throws Exception {
		// 키보드로 SQL 명령을 입력받기 위한 입력 스트림 생성
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		Connection con = ConnectionFactory.getConnection();

		Statement stmt = con.createStatement();

		ResultSet rs = null;

		System.out.println("SQLMinus 프로그램을 실행합니다. (종료: EXIT)");

		while (true) {
			// 키보드로 SQL 명령을 입력받아 저장
			System.out.print("SQL> ");
			// 키보드로 입력받은 문자열의 앞과 뒤에 존재하는 모든 공백을 제거한 후 변수에 저장
			String sql = in.readLine().trim();

			// 키보드 입력값이 없는 경우 반복문을 처음부터 다시 실행
			if (sql == null || sql.equals(""))
				continue;
			// 키보드 입력값이 [EXIT]인 경우 반복문 종료 - 프로그램 종료
			if (sql.equalsIgnoreCase("exit"))
				break;

			// 입력받은 SQL 명령을 전달하여 실행하고 실행 결과를 반환받아 출력
			// 1.select / 2. update / 3. insert / 4. delete / 5. error

			boolean result = stmt.execute(sql);

			if (result) {
				rs = stmt.getResultSet();

				while (rs.next()) {
					System.out.println(rs.getRow());
				}
				ConnectionFactory.close(con, stmt, rs);

			} else {
				if (sql.toLowerCase().indexOf("update") != -1) {

					int rows = stmt.getUpdateCount();

					System.out.println("[메세지]" + rows + "명의 학생 정보를 변경하였습니다.");

					ConnectionFactory.close(con, stmt, rs);
				} else if (sql.toLowerCase().indexOf("delete") != -1) {
					int rows = stmt.getUpdateCount();

					System.out.println("[메세지]" + rows + "명의 학생 정보를 삭제하였습니다.");

					ConnectionFactory.close(con, stmt, rs);

				} else if (sql.toLowerCase().indexOf("insert") != -1) {
					int rows = stmt.getUpdateCount();

					System.out.println("[메세지]" + rows + "명의 학생 정보를 삽입하였습니다.");

					ConnectionFactory.close(con, stmt, rs);
				}
			}

		}

		ConnectionFactory.close(con, stmt, rs);
		System.out.println("SQLMinus 프로그램을 종료합니다.");
	}
}
