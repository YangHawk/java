package xyz.itwill.team05;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccessDAOImpl extends JdbcDAO implements AccessDAO, StudentDAO {
	private static AccessDAOImpl _dao;

	public AccessDAOImpl() {
	}

	static {
		_dao = new AccessDAOImpl();
	}

	public static AccessDAOImpl getDAO() {
		return _dao;
	}

	@Override
	public StudentDTO login(String email, String phone) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StudentDTO student = null;

		try {
			con = getConnection();

			String sql = "select * from student where email = ? and substr(phone,10,4) = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, phone);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				student = new StudentDTO();

				student.setNo(rs.getInt("no"));
				student.setName(rs.getString("name"));
				student.setEmail(rs.getString("email"));
				student.setPhone(rs.getString("phone"));
				student.setAddress(rs.getString("address"));
			} else {
				System.out.println("아이디/비밀번호가 올바르지 않습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return student;
	}

	// 학생 입실
	@Override
	public int insertALog(StudentDTO student) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			con = getConnection();

			String sql = "insert into alog(logno, sno, logtype, logintime) values(logno_seq.nextval ,?, '입실', sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student.getNo());

			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]insertALog() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt);

		}
		return rows;
	}

	// 학생 퇴실
	@Override
	public int updateALog(StudentDTO student) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;

		try {
			con = getConnection();

			String sql = "update alog set logtype = '퇴실', logouttime = sysdate WHERE sno = ? and trunc(logintime, 'DD') = trunc(sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student.getNo());

			rows = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("[에러]updateALog() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt);

		}
		return rows;
	}

	// 저장된 객체의 학번을 전달받아
	@Override
	public List<ALogDTO> showALog(StudentDTO student) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ALogDTO> aLog = new ArrayList<>();
		try {
			con = getConnection();

			String sql = "select * from alog where sno =? order by logintime";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student.getNo());

			rs = pstmt.executeQuery();

			while (rs.next()) {

				ALogDTO myALog = new ALogDTO();
				myALog.setLogNo(rs.getInt("logNo"));
				myALog.setsNo(rs.getInt("sNo"));
				myALog.setLogType(rs.getString("logType"));
				myALog.setLogInTime(rs.getString("logInTime"));
				myALog.setLogOutTime(rs.getString("logOutTime"));
				myALog.setStatus(rs.getString("status"));
				myALog.setsName(rs.getString("sName"));

				aLog.add(myALog);
			}

		} catch (SQLException e) {
			System.out.println("[에러]showALog() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return aLog;
	}

	@Override
	public boolean checkIn(StudentDTO student, LocalDate currentDate) {

		boolean checkIn = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();

			String sql = "select count(*) from alog where sno = ? and trunc(logInTime) = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student.getNo());
			pstmt.setDate(2, Date.valueOf(currentDate));

			rs = pstmt.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				checkIn = count > 0;
			}

		} catch (SQLException e) {
			System.out.println("[에러]checkIn() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}

		return checkIn;

	}

	@Override
	public boolean checkOut(StudentDTO student, LocalDate currentDate) {
		boolean checkOut = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();

			String sql = "select count(*) from alog where sno = ? and logType = '퇴실' and trunc(logInTime) = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student.getNo());
			pstmt.setDate(2, Date.valueOf(currentDate));

			rs = pstmt.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				checkOut = count > 0;
			}

		} catch (SQLException e) {
			System.out.println("[에러]checkOut() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}

		return checkOut;
	}

	@Override
	public int updateStatus(StudentDTO student) {

		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			con = getConnection();
			String sql = "update alog set status = '퇴실', logouttime = sysdate WHERE sno = ? and trunc(logintime, 'DD') = trunc(sysdate)";

		} catch (SQLException e) {
			System.out.println("[에러]updateStatus() 메소드의 SQL 오류 = " + e.getMessage());

		} finally {

		}
		return 0;
	}
}
