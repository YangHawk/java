package xyz.itwill.team05;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl extends JdbcDAO implements TeacherDAO {
	private static TeacherDAOImpl _dao;

	private TeacherDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	static {
		_dao = new TeacherDAOImpl();
	}

	public static TeacherDAOImpl getDAO() {
		return _dao;
	}

	// 학생정보를 전달받아 STUDENT 테이블에 삽입하고 삽입행의 갯수를 반환하는 메소드
	@Override
	public int insertStudent(StudentDTO student) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			con = getConnection();

			String sql = "insert into student values(?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student.getNo());
			pstmt.setString(2, student.getName());
			pstmt.setString(3, student.getEmail());
			pstmt.setString(4, student.getPhone());
			pstmt.setString(5, student.getAddress());

			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]insertStudent() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}

	// 학생정보를 전달받아 STUDENT 테이블에 저장된 학생정보를 변경하고 변경행의 갯수를 반환하는 메소드
	@Override
	public int updateStudent(StudentDTO student) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		int rows = 0;
		try {
			con = getConnection();

			String sql1 = "update student set name=?,email=?,phone=?,address=? where no=?";
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, student.getName());
			pstmt.setString(2, student.getEmail());
			pstmt.setString(3, student.getPhone());
			pstmt.setString(4, student.getAddress());
			pstmt.setInt(5, student.getNo());

			rows = pstmt.executeUpdate();

			String sql2 = "update alog set sname=? where sno=?";
			pstmt2 = con.prepareStatement(sql2); // 새로운 PreparedStatement 객체 생성
			pstmt2.setString(1, student.getName());
			pstmt2.setInt(2, student.getNo());

			rows += pstmt2.executeUpdate();

		} catch (SQLException e) {
			System.out.println("[에러]updateStudent() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}

	// 학번을 전달받아 STUDENT 테이블에 저장된 학생정보를 삭제하고 삭제행의 갯수를 반환하는 메소드
	@Override
	public int deleteStudent(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;

		int rows = 0;
		try {
			con = getConnection();

			String sql1 = "delete from student where no=?";
			pstmt = con.prepareStatement(sql1);
			pstmt.setInt(1, no);

			rows = pstmt.executeUpdate();

			String sql2 = "delete from alog where sno=?";
			pstmt2 = con.prepareStatement(sql2);
			pstmt2.setInt(1, no);

			rows += pstmt2.executeUpdate();

		} catch (SQLException e) {
			System.out.println("[에러]deleteStudent() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}

	// 학번을 전달받아 STUDENT 테이블에 저장된 해당 학번의 학생정보를 검색하여 반환하는 메소드
	@Override
	public StudentDTO selectStudent(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StudentDTO student = null;
		try {
			con = getConnection();

			String sql = "select * from student where no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();

			// ResultSet 객체에 저장된 검색행을 Java 객체(값)로 매핑 처리
			// 검색행이 0 또는 1인 경우 선택문 사용
			if (rs.next()) {// 검색행이 있는 경우
				student = new StudentDTO();
				// 처리행의 컬럼값을 반환받아 DTO 객체의 필드값으로 변경
				student.setNo(rs.getInt("no"));
				student.setName(rs.getString("name"));
				student.setEmail(rs.getString("email"));
				student.setPhone(rs.getString("phone"));
				student.setAddress(rs.getString("address"));
			}
		} catch (SQLException e) {
			System.out.println("[에러]selectStudent() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		// 검색행이 없는 경우 [null]를 반환하고 검색행이 있으면 DTO 객체 반환
		return student;
	}

	// 이름을 전달받아 STUDENT 테이블에 저장된 해당 이름의 학생정보를 검색하여 반환하는 메소드
	@Override
	public List<StudentDTO> selectNameStudentList(String name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<StudentDTO> studentList = new ArrayList<>();
		try {
			con = getConnection();

			String sql = "select * from student where name=? order by no";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);

			rs = pstmt.executeQuery();

			// 검색행이 0개 이상인 경우 반복문 사용
			while (rs.next()) {
				// 하나의 검색행을 DTO 객체로 매핑 처리
				StudentDTO student = new StudentDTO();
				student.setNo(rs.getInt("no"));
				student.setName(rs.getString("name"));
				student.setEmail(rs.getString("email"));
				student.setPhone(rs.getString("phone"));
				student.setAddress(rs.getString("address"));

				// DTO 객체를 List 객체의 요소로 추가
				studentList.add(student);
			}
		} catch (SQLException e) {
			System.out.println("[에러]selectNameStudentList() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return studentList;
	}

	// STUDENT 테이블에 저장된 모든 학생정보를 검색하여 반환하는 메소드
	@Override
	public List<StudentDTO> selectAllStudentList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<StudentDTO> studentList = new ArrayList<>();
		try {
			con = getConnection();

			String sql = "select * from student order by no";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				StudentDTO student = new StudentDTO();
				student.setNo(rs.getInt("no"));
				student.setName(rs.getString("name"));
				student.setEmail(rs.getString("email"));
				student.setPhone(rs.getString("phone"));
				student.setAddress(rs.getString("address"));

				studentList.add(student);
			}
		} catch (SQLException e) {
			System.out.println("[에러]selectStudent() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return studentList;
	}

	@Override
	public List<ALogDTO> selectNameAlogList(String name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ALogDTO> studentLog = new ArrayList<>();
		try {
			con = getConnection();

			String sql = "select * from alog where sname = ? order by logintime";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ALogDTO student = new ALogDTO();
				student.setsNo(rs.getInt("sno"));
				student.setLogType(rs.getString("logtype"));
				student.setsName(rs.getString("sname"));
				student.setLogInTime(rs.getString("logintime"));
				student.setLogOutTime(rs.getString("logouttime"));
				student.setStatus(rs.getString("status"));

				studentLog.add(student);
			}
		} catch (SQLException e) {
			System.out.println("[에러]selectStudent() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return studentLog;
	}

	@Override
	public List<ALogDTO> selectDateALogList(String logInTime) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ALogDTO> dateLog = new ArrayList<>();
		try {
			con = getConnection();

			String sql = "select * from alog where trunc(logintime) = to_date(?, 'YYYY-MM-DD') order by logintime";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, logInTime);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ALogDTO student = new ALogDTO();
				student.setsNo(rs.getInt("sno"));
				student.setLogType(rs.getString("logtype"));
				student.setsName(rs.getString("sname"));
				student.setLogInTime(rs.getString("logintime"));
				student.setLogOutTime(rs.getString("logouttime"));
				student.setStatus(rs.getString("status"));

				dateLog.add(student);
			}
		} catch (SQLException e) {
			System.out.println("[에러]selectStudent() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return dateLog;
	}

}