package xyz.itwill.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//EMP 테이블에 저장된 모든 사원 정보의 사원 번호, 사원 이름, 급여를 급여로 내림차순 정렬되도록
//검색하여 출력하는 JDBC 프로그램 작성
public class SelectEmpApp {
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "scott";
			String password = "tiger";

			con = DriverManager.getConnection(url, user, password);

			stmt = con.createStatement();

			String sql = "select empno, ename, sal from emp order by sal desc";

			rs = stmt.executeQuery(sql);
			/*
			if (rs.next()) {
				do {
					int empno = rs.getInt("empno");
					String ename = rs.getString("ename");
					int sal = rs.getInt("sal"); //한번만 쓰이니까 변수로 설정할 필요 X

					System.out.println("사원 번호 = " + empno + ", 사원 이름 = " + ename + ", 급여 = " + sal);

				} while (rs.next());
			} else {
				System.out.println("[메세지]검색된 사원 정보가 없습니다.");
			}
			*/
			
			while(rs.next()) {
				System.out.println("사원 번호 = "+rs.getString("empno")+", 사원 이름 = "+rs.getString("ename")+", 급여 = "+rs.getInt("sal"));
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("OracleDriver 를 찾을 수 없음");
		} catch (SQLException e) {
			System.out.println("JDBC 관련 오류 = " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
